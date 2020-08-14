package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/10 14:18
 * 发货量汇总
 */
@Data
public class CSGoodsRegionSumEntity {

    //省
    private String province;

    //市
    private String city;

    //区
    private String district;

    //总发货量
    private Double totleDeliverGoods;

    //发货量（医药）
    private Double medicineDeliverGoods;

    //年
    private String year;

    //月
    private String month;

}
