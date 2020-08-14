package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/8 16:55
 */
@Data
public class CusRelationEntity {

    //用户GUID
    private String guid;

    //源数据GUID
    private String sourceGuid;

    //1.医链云、2.药链云、3.四方云
    private String sourceData;
}
