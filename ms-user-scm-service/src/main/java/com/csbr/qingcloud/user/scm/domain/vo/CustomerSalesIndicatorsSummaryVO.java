package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: ms-user-scm-service
 * @description: 客户销售指标汇总
 * @author: yio
 * @create: 2020-08-07 09:25
 **/
@Data
@ApiModel("客户销售指标汇总")
public class CustomerSalesIndicatorsSummaryVO {

    /**
     * 客户数
     */
    @ApiModelProperty("客户数")
    private int CustomerNum;

    /**
     * 产品数
     */
    @ApiModelProperty("产品数")
    private int goodsNum;

    /**
     * 总指标金额
     */
    @ApiModelProperty("总指标金额")
    private BigDecimal summarySalesIndicators;

    /**
     * 平均均指标金额
     */
    @ApiModelProperty("平均指标金额")
    private BigDecimal avgSalesIndicators;
}
