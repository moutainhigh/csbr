package com.csbr.qingcloud.user.scm.domain.dto.organisation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 组织资料修改参数
 * @author: yio
 * @create: 2020-07-30 16:15
 **/
@Data
@ApiModel("组织资料修改参数")
public class OrganisationUpdateDTO extends OrganisationAddDTO {

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;
}
