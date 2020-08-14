package com.csbr.cloud.flink.api.model.vo.visualdata;

import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 13:45
 **/
@Data
public class SFTransportCapacityVO {
    private Long chillCar = 0L;
    private Long chillCarSum = 0L;
    private Long normalCar = 0L;
    private Long normalCarSum = 0L;
    private Long proCount = 0L;
    private Double proRatio = 0.00;
    private Long totleCar = 0L;
    private Long totleCarSum = 0L;
    private Long totleDelivery = 0L;
    private Double totleGoods = 0.00;
}
