package com.csbr.qingcloud.common.admin.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.PlatformVO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.PlatfromDetailVO;
import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfAdminUser;
import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfPlatform;
import com.csbr.qingcloud.common.admin.cloud.mybatis.service.MfAdminUserService;
import com.csbr.qingcloud.common.admin.cloud.mybatis.service.MfPlatformService;
import com.csbr.qingcloud.common.admin.cloud.service.AdminUserService;
import com.csbr.qingcloud.common.admin.cloud.service.PlatformInfoService;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: auth
 * @description: 平台资料信息
 * @author: yio
 * @create: 2020-07-09 10:53
 **/
@Service
@Transactional
public class PlatformInfoServiceImpl implements PlatformInfoService {

    @Autowired
    private MfPlatformService platformService;
    @Autowired
    private MfAdminUserService mfadminUserService;

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;

    private void checkDuplicate(String platformName,String guid){
        //查看平台名称是否重复
        LambdaQueryWrapper<MfPlatform> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfPlatform::getPlatformName, platformName)
                .select(MfPlatform::getGuid);
        MfPlatform ent=platformService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("平台名称(%s)已存在。", platformName));
        }

    }

    @Override
    public CommonRes<Boolean> addPlatformInfo(@Valid PlatformAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
        //查看平台名称是否重复
        checkDuplicate(dto.getPlatformName(),null);

        MfPlatform platform = csbrBeanUtil.convert(dto, MfPlatform.class);
        String platformGuid=CommonUtil.newGuid();
        platformService.csbrAddEntity(platform);
        //如果输入了平台账号，那么初始化平台账号
        if(StringUtils.isNotEmpty(dto.getLogonUser())){
            if(StringUtils.isEmpty(dto.getPwd())){
                throw new CsbrUserException(UserError.OBJECT_IS_NULL, String.format("平台(%s)对应的初始账号的密码不能为空。", dto.getPlatformName()));

            }
            addAdminUser(dto.getLogonUser(),dto.getPwd(),platformGuid);
        }

        boolean flag = platformService.save(platform);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("平台(%s)新增失败。", dto.getPlatformName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 更新平台资料信息
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updatePlatformInfo(PlatformUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }

        //查找更新的数据是否存在
        if (!platformService.isExistsData(Arrays.asList(dto.getGuid()), MfPlatform.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("平台(%s)不存在，无法更新。", dto.getPlatformName()));
        }
        //查看平台名称是否重复
        checkDuplicate(dto.getPlatformName(),dto.getGuid());

        //更新数据
        LambdaUpdateWrapper<MfPlatform> updateWrapper = platformService.csbrUpdateWrapper(MfPlatform.class);
        updateWrapper.eq(MfPlatform::getGuid, dto.getGuid());

        //查看初始账号是否存在，不存在添加，存在修改
        if(StringUtils.isNotEmpty(dto.getLogonUser()))
        {
            LambdaQueryWrapper<MfAdminUser> userQueryWrapper=Wrappers.lambdaQuery();
            userQueryWrapper.eq(MfAdminUser::getPlatformGuid,dto.getGuid());
            userQueryWrapper.eq(MfAdminUser::getLogonUser,dto.getLogonUser());
            MfAdminUser adminUser=mfadminUserService.getOne(userQueryWrapper);
            if(adminUser!=null){
                //账号存在
                AdminUserUpdateDTO updateDTO=new AdminUserUpdateDTO();
                updateDTO.setPlatformGuid(dto.getGuid());
                updateDTO.setLogonUser(dto.getLogonUser());
                updateDTO.setPwd(dto.getPwd());
                updateDTO.setGuid(adminUser.getGuid());
                adminUserService.updateAdminUser(updateDTO);

            }
            else{
                //账号不存在
                if(StringUtils.isEmpty(dto.getPwd())){
                    throw new CsbrUserException(UserError.OBJECT_IS_NULL, String.format("平台(%s)对应的初始账号的密码不能为空。", dto.getPlatformName()));

                }
                addAdminUser(dto.getLogonUser(),dto.getPwd(),dto.getGuid());

            }
        }
        else{
            //删除平台初始账号
            LambdaQueryWrapper<MfAdminUser> userQueryWrapper=Wrappers.lambdaQuery();
            userQueryWrapper.eq(MfAdminUser::getPlatformGuid,dto.getGuid());
            userQueryWrapper.eq(MfAdminUser::getIsInit,"Y");
            mfadminUserService.remove(userQueryWrapper);
        }
        MfPlatform platform = csbrBeanUtil.convert(dto, MfPlatform.class);
        boolean flag = platformService.update(platform, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("平台(%s)更新失败。", dto.getPlatformName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 删除平台资料信息
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removePlatformInfo(List<String> guids) {
        //查找更新的数据是否存在
        if (!platformService.isExistsData(guids, MfPlatform.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfPlatform> updateWrapper = platformService.csbrUpdateWrapper(MfPlatform.class);
        updateWrapper.in(MfPlatform::getGuid, guids);
        boolean flag = platformService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 查询平台资料信息
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<PlatformVO> getPlatformInfo(PlatformQueryDTO dto) {
        LambdaQueryWrapper<MfPlatform> queryWrapper = platformService.csbrQueryWrapper(dto, MfPlatform.class);

        PageListVO<MfPlatform> lst = platformService.csbrPageList(dto, queryWrapper);
        return csbrBeanUtil.convert(lst, PageListVO.class);

    }

    /**
     * 查询平台资料信息
     *
     * @param guid
     * @return
     */
    @Override
    public PlatfromDetailVO getPlatformInfoDetail(String guid) {

        MfPlatform platform=platformService.getById(guid);

        if(platform!=null){
            PlatfromDetailVO vo=csbrBeanUtil.convert(platform,PlatfromDetailVO.class);
            //获取初始平台账号
            LambdaQueryWrapper<MfAdminUser> userQueryWrapper=Wrappers.lambdaQuery();
            userQueryWrapper.eq(MfAdminUser::getPlatformGuid,guid);
            userQueryWrapper.eq(MfAdminUser::getIsInit,"Y");
            MfAdminUser user=mfadminUserService.getOne(userQueryWrapper);
            vo.setLogonUser(user.getLogonUser());
            return vo;
        }
        return null;
    }
    private void addAdminUser(String logonUser,String pwd,String platformGuid)
    {
        AdminUserAddDTO userDto=new AdminUserAddDTO();
        userDto.setLogonUser(logonUser);
        userDto.setPlatformGuid(platformGuid);
        userDto.setPwd(pwd);
        adminUserService.addAdminUser(userDto,true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes test() {
//        try {
        MfAdminUser adminUser = new MfAdminUser();
        adminUser.setGuid(CommonUtil.newGuid());
        adminUser.setLogonUser("test212");
        adminUser.setPwd(CommonUtil.md5ForPwd("123456"));
        mfadminUserService.csbrAddEntity(adminUser);
        mfadminUserService.save(adminUser);

        MfPlatform platform = new MfPlatform();
        platform.setPlatformName("test112345");
        platform.setDomainName("domain112345");
        platformService.save(platform);
//        } catch (Exception ex) {
//            throw new CsbrUserException(UserError.DATA_ADD_ERROR, "新增数据错误！", ex);
//        }
        return CommonRes.success();
    }

}
