package com.csbr.qingcloud.user.scm.domain.dto.order;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 订单明细新增参数
 * @author: yio
 * @create: 2020-08-11 15:22
 **/
@Data
@ApiModel("订单明细新增参数")
public class OrderDetailAddDTO {



    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    @NotBlank(message = "产品GUID不能为空。")
    private String goodsGuid;


    /** 数量 */
    @ApiModelProperty("数量")
    @NotNull(message = "数量不能为空。")
    @DecimalMin("0")
    private Double qty;


}
