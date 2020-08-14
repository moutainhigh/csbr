package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/7 15:14
 */
@Data
public class PDeliverySumEntity {

    //平台运力
    private Long totleDelivery;

    //物流公司总车辆
    private Long totleCarSum;

    //冷藏车总数
    private Long chillCarSum;

    //分拨中心数量
    private Long fenboNumber;

    //物流网点总数量
    private Long dotTotle;

    //在线运营车辆
    private Long totleCar;

    //在线运营冷藏车辆
    private Long chillCar;

    //在线物流网点数
    private Long dotNumber;
}
