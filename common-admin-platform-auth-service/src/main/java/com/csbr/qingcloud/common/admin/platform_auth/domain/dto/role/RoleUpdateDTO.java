package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色修改参数
 * @author: yio
 * @create: 2020-07-22 15:23
 **/
@Data
@ApiModel("角色修改参数")
public class RoleUpdateDTO extends RoleAddDTO{
    /** 更新的条件 */
    @ApiModelProperty("系统唯一标识")
    @NotBlank(message = "Guid不能为空。")
    private String guid;
}
