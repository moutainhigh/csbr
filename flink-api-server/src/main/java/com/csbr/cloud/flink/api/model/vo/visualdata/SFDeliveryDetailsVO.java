package com.csbr.cloud.flink.api.model.vo.visualdata;

import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 13:45
 **/
@Data
public class SFDeliveryDetailsVO {
    private String address;
    private String chineseName;
    private String guid;
    private Long num;
    private String province;
}
