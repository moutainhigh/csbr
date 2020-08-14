package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/10 11:18
 * 平台货量区域分布
 */
@Data
public class PGoodsRegionSumEntity {

    //省份
    private String province;

    //城市
    private String city;

    //区/县/乡
    private String district;

    //总发货量
    private Double totleDeliverGoods;

    //总收货量
    private Double totleReceivingGoods;

    //发货量（医药）
    private Double medicineDeliverGoods;

    //收货量
    private Double medicineReceivingGoods;

    //年
    private String year;

    //月
    private String month;

}
