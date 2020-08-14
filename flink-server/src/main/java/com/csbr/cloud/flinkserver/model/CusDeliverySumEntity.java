package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/9 16:08
 * 用户在线运力统计
 */
@Data
public class CusDeliverySumEntity {

    //用户GUID
    private String guid;

    //在线运营车辆
    private Long totleCar;
}
