package com.csbr.qingcloud.user.scm.domain.dto.staff;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 人员修改参数
 * @author: yio
 * @create: 2020-07-31 13:59
 **/
@Data
@ApiModel("人员修改参数")
public class StaffUpdateDTO extends StaffAddDTO {
    /** 更新的条件 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;
}
