package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 应用端角色显示对象
 * @author: yio
 * @create: 2020-07-29 13:41
 **/
@Data
public class AppSideRoleVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 应用端名称 */
    @ApiModelProperty("应用端名称")
    private String appSideName;

    /** 应用端角色列表 */
    @ApiModelProperty("应用端角色列表")
    List<RoleVO> roleVOs;
}


