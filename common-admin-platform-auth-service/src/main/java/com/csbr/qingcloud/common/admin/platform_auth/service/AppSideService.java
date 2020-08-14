package com.csbr.qingcloud.common.admin.platform_auth.service;

import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.AppSideVO;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端服务
 * @author: yio
 * @create: 2020-07-21 09:50
 **/
public interface AppSideService {
    /**
     * 新增应用端
     * @param dto
     * @return
     */
    CommonRes<Boolean> addAppSide(@Valid AppSideAddDTO dto);

    /**
     * 修改应用端
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateAppSide(AppSideUpdateDTO dto);

    /**
     * 删除应用端
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeAppSide(List<String> guids);

    /**
     * 查询应用端
     * @param dto
     * @return
     */
    PageListVO<AppSideVO> getAppSide(AppSideQueryDTO dto);


}
