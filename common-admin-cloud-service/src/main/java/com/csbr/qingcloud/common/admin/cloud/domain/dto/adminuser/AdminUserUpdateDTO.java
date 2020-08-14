package com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 平台用户修改参数
 * @author: yio
 * @create: 2020-07-20 12:17
 **/
@Data
@ApiModel(value = "平台用户修改参数")
public class AdminUserUpdateDTO extends AdminUserAddDTO {
    /** 更新的条件 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;
}
