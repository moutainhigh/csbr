package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标明细
 * @author: yio
 * @create: 2020-08-13 18:21
 **/
@Data
@ApiModel("人员销售指标明细")
public class StaffSalesIndicatorsDetailVO {
    /** 商品唯一码 */
    @ApiModelProperty("商品唯一码")
    private String goodsGuid;


    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;


    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /** 销售指标 */
    @ApiModelProperty("销售指标")
    private Double salesIndicators;

}
