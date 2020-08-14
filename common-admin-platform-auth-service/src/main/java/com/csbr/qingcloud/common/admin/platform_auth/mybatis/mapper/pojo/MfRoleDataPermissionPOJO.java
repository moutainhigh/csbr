package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfDataPermission;
import lombok.Data;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色数据权限对象
 * @author: yio
 * @create: 2020-08-04 13:50
 **/
@Data
public class MfRoleDataPermissionPOJO extends MfDataPermission {

    /** 角色GUID */
    private String roleGuid;
    /** 自定义数据 */
    private String customData;
}
