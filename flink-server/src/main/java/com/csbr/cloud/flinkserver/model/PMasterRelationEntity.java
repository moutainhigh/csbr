package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/10 15:34
 * 医疗机构供应商关系
 */
@Data
public class PMasterRelationEntity {

    private String guid;

    //企业GUID
    private String enterpriseGUID;

    //关系企业GUID
    private String relationEntGUID;

    //关系类型
    private String relationType;

    //关系建立日期
    private String relationCreateTime;

    //状态
    private String state;
}
