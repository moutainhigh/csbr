package com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 产品修改的参数
 * @author: yio
 * @create: 2020-07-21 09:41
 **/
@Data
@ApiModel("产品修改参数")
public class ProductUpdateDTO extends ProductQueryDTO{

    /** 更新的条件 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;
}
