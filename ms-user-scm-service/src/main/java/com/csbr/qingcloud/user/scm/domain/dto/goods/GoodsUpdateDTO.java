package com.csbr.qingcloud.user.scm.domain.dto.goods;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: ms-user-scm-service
 * @description: 产品修改参数
 * @author: yio
 * @create: 2020-08-11 13:33
 **/
@Data
@ApiModel("产品修改参数")
public class GoodsUpdateDTO extends GoodsAddDTO{

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState;

}
