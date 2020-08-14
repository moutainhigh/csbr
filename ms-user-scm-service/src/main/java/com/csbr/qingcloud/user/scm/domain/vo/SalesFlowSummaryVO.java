package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向汇总数据
 * @author: yio
 * @create: 2020-08-07 15:06
 **/
@Data
@ApiModel("销售流向汇总数据")
public class SalesFlowSummaryVO {
    /**
     * 购入客户数
     */
    @ApiModelProperty("购入客户数")
    private int customerNum;

    /**
     * 产品数
     */
    @ApiModelProperty("产品数")
    private int goodsNum;

    /**
     * 核算金额
     */
    @ApiModelProperty("核算金额")
    private BigDecimal summaryAccountingAmount;


}
