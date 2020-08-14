package com.csbr.cloud.flink.api.mybatis.basedata.model;

import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 16:01
 **/
@Data
public class WarehouseDetailsDTO {
    private String address;
    private Double area;
    private String chineseName;
    private String guid;
    private Long num;
    private String province;
}
