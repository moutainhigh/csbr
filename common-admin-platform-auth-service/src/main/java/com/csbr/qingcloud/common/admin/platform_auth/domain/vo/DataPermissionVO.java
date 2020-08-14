package com.csbr.qingcloud.common.admin.platform_auth.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: common-admin-platform-auth-service
 * @description: 数据权限
 * @author: yio
 * @create: 2020-07-23 16:05
 **/
@Data
@ApiModel("获取数据权限的返回值对象")
public class DataPermissionVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 数据权限名称 */
    @ApiModelProperty("数据权限名称")
    private String dataPermissionName;

}
