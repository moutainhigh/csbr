package com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import lombok.Data;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向主明细
 * @author: yio
 * @create: 2020-08-13 15:06
 **/
@Data
public class SalesFlowWithDetailPOJO extends TrSalesFlow {


    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 批号 */
    private String lot;

    /** 规格 */
    private String goodsSpec;
    /** 数量 */
    private Double qty;

    /** 核算单价 */
    private Double accountingPrice;

    /** 出库单价 */
    private Double outPrice;

    /** 核算金额 */
    private Double accountingAmount;

    /** 出库金额 */
    private Double outAmount;

}
