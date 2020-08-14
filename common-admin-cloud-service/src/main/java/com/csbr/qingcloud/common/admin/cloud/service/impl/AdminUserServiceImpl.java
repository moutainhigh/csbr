package com.csbr.qingcloud.common.admin.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminLoginDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.AdminLoginVO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.AdminUserVO;
import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfAdminUser;
import com.csbr.qingcloud.common.admin.cloud.mybatis.mapper.so.MfAdminUserSO;
import com.csbr.qingcloud.common.admin.cloud.mybatis.service.MfAdminUserService;
import com.csbr.qingcloud.common.admin.cloud.service.AdminUserService;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 平台用户服务
 * @author: yio
 * @create: 2020-07-20 15:02
 **/
@Service
@Transactional
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private MfAdminUserService adminUserService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;

    /**
     * 新增平台用户账号
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addAdminUser(@Valid AdminUserAddDTO dto) {
       return addAdminUser(dto,false);
    }

    private void checkDuplicate(String logonUser,String platformGuid,String guid){
        //查看账号是否重复
        LambdaQueryWrapper<MfAdminUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfAdminUser::getLogonUser, logonUser);
        queryWrapper.eq(MfAdminUser::getPlatformGuid,platformGuid)
                .select(MfAdminUser::getGuid);
        MfAdminUser ent=adminUserService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("平台用户账号(%s)已存在。", logonUser));
        }

    }

    @Override
    public CommonRes<Boolean> addAdminUser(@Valid AdminUserAddDTO dto, boolean isInit) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
        //查看账号是否重复
        checkDuplicate(dto.getLogonUser(),dto.getPlatformGuid(),null);
        //查看手机号是否重复
        LambdaQueryWrapper<MfAdminUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfAdminUser::getMobileNo, dto.getMobileNo());
        queryWrapper.eq(MfAdminUser::getPlatformGuid,dto.getPlatformGuid());
        int cnt = adminUserService.count(queryWrapper);
        if (cnt > 0) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("平台用户手机号(%s)已存在。", dto.getMobileNo()));
        }
        MfAdminUser adminUser = csbrBeanUtil.convert(dto, MfAdminUser.class);
        adminUser.setPwd(CommonUtil.md5ForPwd(dto.getPwd()));
        if(isInit)
        {
            adminUser.setIsInit("Y");
        }
        else
        {
            adminUser.setIsInit("N");
        }
        adminUserService.csbrAddEntity(adminUser);
        boolean flag = adminUserService.save(adminUser);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("平台用户账号(%s)新增失败。", dto.getLogonUser()));
        }
        return CommonRes.success(flag);
    }
    /**
     * 修改平台用户账号
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateAdminUser(AdminUserUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }

        //查找更新的数据是否存在
        if (!adminUserService.isExistsData(Arrays.asList(dto.getGuid()), MfAdminUser.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("平台用户账号(%s)不存在，无法更新。", dto.getLogonUser()));
        }
        //查看账号是否重复
        checkDuplicate(dto.getLogonUser(),dto.getPlatformGuid(),dto.getGuid());
        //更新数据
        LambdaUpdateWrapper<MfAdminUser> updateWrapper = adminUserService.csbrUpdateWrapper(MfAdminUser.class);
        updateWrapper.eq(MfAdminUser::getGuid, dto.getGuid());

        MfAdminUser adminUser = csbrBeanUtil.convert(dto, MfAdminUser.class);
        adminUser.setPwd(CommonUtil.md5ForPwd(dto.getPwd()));
        boolean flag = adminUserService.update(adminUser, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("平台用户账号(%s)更新失败。", dto.getLogonUser()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除用户账号
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeAdminUser(List<String> guids) {
        //查找更新的数据是否存在
        if (!adminUserService.isExistsData(guids, MfAdminUser.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfAdminUser> updateWrapper = adminUserService.csbrUpdateWrapper(MfAdminUser.class);
        updateWrapper.in(MfAdminUser::getGuid, guids);
        boolean flag = adminUserService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 查询平台账号数据
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<AdminUserVO> getAdminUser(AdminUserQueryDTO dto) {
        MfAdminUserSO adminUserQuery = csbrBeanUtil.convert(dto, MfAdminUserSO.class);
        PageListVO<MfAdminUser> lst = adminUserService.getDataWithPlatformName(adminUserQuery);
        return csbrBeanUtil.convert(lst, PageListVO.class);

    }

    /**
     * 管理员登陆
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes adminLogin(AdminLoginDTO dto) {
        // 获取密码
        String pwd = CommonUtil.md5ForPwd(dto.getPwd());
        // 清理密码数据，不将密码作为查询条件
        dto.setPwd(null);
        LambdaQueryWrapper<MfAdminUser> query = adminUserService.csbrQueryWrapper(dto, MfAdminUser.class)
                .select(MfAdminUser::getGuid, MfAdminUser::getPwd, MfAdminUser::getName, MfAdminUser::getServiceSign,
                        MfAdminUser::getBizState, MfAdminUser::getIsLocked);
        MfAdminUser user = adminUserService.getOne(query);
        if (ObjectUtils.isEmpty(user)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, "用户不存在");
        }
        if (!pwd.equals(user.getPwd())) {
            throw new CsbrUserException(UserError.PASSWORD_ERROR, "密码错误");
        }
        if (!user.getBizState().equals("Y")) {
            // TODO: 需定义错误码
            throw new CsbrUserException(UserError.USER_LOGIN_ERROR, "用户非有效状态");
        }
        if (user.getIsLocked().equals("Y")) {
            // TODO: 需定义错误码
            throw new CsbrUserException(UserError.USER_LOGIN_ERROR, "用户已锁定");
        }

        AdminLoginVO vo = new AdminLoginVO();
        vo.setUserGuid(user.getGuid());
        vo.setUserName(user.getName());
        // 设置有权限的服务
        List<String> services = new ArrayList<>();
        services.add(user.getServiceSign());
        vo.setServiceList(services);

        return CommonRes.success(vo);
    }
}
