package com.csbr.cloud.flink.api.model.vo.visualdata;

import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 13:45
 **/
@Data
public class SFWareHouseCapacityVO {
    private Double chillArea;
    private Double shadeArea;
    private Double threeArea;
    private Double totleArea;
    private Long totleGsp;
}
