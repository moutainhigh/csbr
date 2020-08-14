package com.csbr.qingcloud.user.scm.domain.dto.order;

import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsAddDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: ms-user-scm-service
 * @description: 订单修改参数
 * @author: yio
 * @create: 2020-08-12 10:43
 **/
@Data
@ApiModel("订单修改参数")
public class OrderUpdateDTO  extends OrderAddDTO {

    /** 系统唯一标识 */
    @NotBlank(message = "Guid不能为空。")
    @ApiModelProperty("系统唯一标识")
    private String guid;

}


