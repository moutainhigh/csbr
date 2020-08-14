package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户角色对象
 * @author: yio
 * @create: 2020-08-03 17:50
 **/
@Data
public class MfUserRolePOJO {

    /** 用户GUID */
    private String userGuid;

    /** 角色GUID */
    private String roleGuid;

    /** 角色名称 */
    private String roleName;

}
