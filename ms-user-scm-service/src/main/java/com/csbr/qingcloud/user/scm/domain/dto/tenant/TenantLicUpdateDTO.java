package com.csbr.qingcloud.user.scm.domain.dto.tenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 企业证照修改参数
 * @author: yio
 * @create: 2020-07-29 12:00
 **/
@Data
@ApiModel("企业证照修改参数")
public class TenantLicUpdateDTO extends TenantLicAddDTO{
    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

}
