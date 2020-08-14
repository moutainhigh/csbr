package com.csbr.qingcloud.common.admin.platform_auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.AppSideVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfAppSide;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfAppSideService;
import com.csbr.qingcloud.common.admin.platform_auth.service.AppSideService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端服务实现
 * @author: yio
 * @create: 2020-07-21 10:09
 **/
@Service
public class AppSideServiceImpl implements AppSideService {
    @Autowired
    private MfAppSideService mfAppSideService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;


    private void checkDuplicate(String appSideName,String guid)
    {
        //查看账号是否重复
        LambdaQueryWrapper<MfAppSide> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfAppSide::getAppSideName,appSideName)
                .select(MfAppSide::getGuid);
        MfAppSide ent=mfAppSideService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("应用端(%s)已存在。",appSideName));
        }

    }

    /**
     * 新增应用端
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addAppSide(@Valid AppSideAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
        //查看账号是否重复
        checkDuplicate(dto.getAppSideName(),null);
        MfAppSide appSide = csbrBeanUtil.convert(dto, MfAppSide.class);
        mfAppSideService.csbrAddEntity(appSide);
        boolean flag = mfAppSideService.save(appSide);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("应用端(%s)新增失败。", dto.getAppSideName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改应用端
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateAppSide(AppSideUpdateDTO dto) {

        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }
        //查找更新的数据是否存在
        if (!mfAppSideService.isExistsData(Arrays.asList(dto.getGuid()),MfAppSide.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("应用端(%s)不存在，无法更新。", dto.getAppSideName()));
        }
        //查看账号是否重复
        checkDuplicate(dto.getAppSideName(),null);

        //更新数据
        LambdaUpdateWrapper<MfAppSide> updateWrapper = mfAppSideService.csbrUpdateWrapper(MfAppSide.class);
        updateWrapper.eq(MfAppSide::getGuid,dto.getGuid());

        MfAppSide appSide = csbrBeanUtil.convert(dto, MfAppSide.class);
        boolean flag = mfAppSideService.update(appSide, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("应用端(%s)更新失败。", dto.getAppSideName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除应用端
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeAppSide(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfAppSideService.isExistsData(guids,MfAppSide.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfAppSide> updateWrapper =mfAppSideService.csbrUpdateWrapper(MfAppSide.class);
        updateWrapper.in(MfAppSide::getGuid, guids);
        boolean flag = mfAppSideService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 获取应用端
     * @param dto
     * @return
     */
    @Override
    public PageListVO<AppSideVO> getAppSide(AppSideQueryDTO dto) {

        PageListVO<MfAppSide> lst = mfAppSideService.csbrPageList(dto
                ,mfAppSideService.csbrQueryWrapper(dto,MfAppSide.class));
        return csbrBeanUtil.convert(lst, PageListVO.class);

    }

}
