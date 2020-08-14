package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标汇总
 * @author: yio
 * @create: 2020-08-07 09:25
 **/
@Data
@ApiModel("人员销售指标汇总")
public class StaffSalesIndicatorsSummaryVO {

    /**
     * 人数
     */
    @ApiModelProperty("人数")
    private int peopleNum;

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
     * 人均指标金额
     */
    @ApiModelProperty("人均指标金额")
    private BigDecimal perCapitaSalesIndicators;
}
