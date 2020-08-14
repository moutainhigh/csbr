package com.csbr.qingcloud.common.admin.platform_auth.service;


import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.datapermission.DataPermissionQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.DataPermissionVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfDataPermission;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleDataPermissionPOJO;

import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 数据权限服务
 * @author: yio
 * @create: 2020-07-23 15:58
 **/
public interface DataPermissionService {


    /**
     * 查询数据权限
     * @param dto
     * @return
     */
    PageListVO<DataPermissionVO> getDataPermission(DataPermissionQueryDTO dto);

    /**
     * 通过角色获取数据权限
     * @param roleGuid
     * @return
     */
    List<MfRoleDataPermissionPOJO> getDataPermissionByRole(String roleGuid);

}
