package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/10 11:41
 * 平台医疗机构采购量汇总
 */
@Data
public class PPurchaseGoodsSumEntity {

    //采购金额
    private Double totlePurchaseAmount;

    //年
    private String year;

    //月
    private String month;
}
