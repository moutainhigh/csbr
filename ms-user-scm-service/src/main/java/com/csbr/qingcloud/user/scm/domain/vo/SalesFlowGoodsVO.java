package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向商品显示对象
 * @author: yio
 * @create: 2020-08-13 10:18
 **/
public class SalesFlowGoodsVO {
    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 批号 */
    @ApiModelProperty("批号")
    private String lot;

    /** 出库单价 */
    @ApiModelProperty("出库单价")
    private Double outPrice;

    /** 核算单价 */
    @ApiModelProperty("核算单价")
    private Double accountingPrice;

    /** 数量 */
    @ApiModelProperty("数量")
    private Double qty;

    /** 核算金额 */
    @ApiModelProperty("核算金额")
    private Double accountingAmount;
}
