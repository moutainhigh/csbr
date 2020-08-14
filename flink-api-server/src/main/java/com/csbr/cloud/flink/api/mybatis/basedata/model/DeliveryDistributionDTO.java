package com.csbr.cloud.flink.api.mybatis.basedata.model;

import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 16:01
 **/
@Data
public class DeliveryDistributionDTO {
    private String province;
    private Long totalCount;
    private Long totleDelivery;
}
