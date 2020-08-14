package com.csbr.cloud.flinkserver.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangheng
 * @date 2020/1/7 14:13
 */
@Data
public class PCustomerTypeSumEntity {

    //生产企业
    private Long totlePro;

    //流通企业
    private Long totleDelivery;

    //医疗机构
    private Long totleMedIns;

    private Date createDate;

    private Date updateDate;
}
