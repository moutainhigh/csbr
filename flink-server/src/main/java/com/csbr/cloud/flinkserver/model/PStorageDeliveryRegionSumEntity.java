package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/9 10:12
 */
@Data
public class PStorageDeliveryRegionSumEntity {

    //省
    private String province;

    //市
    private String city;

    //区
    private String district;

    //GSP仓库总数量
    private Long totleGSP;

    //仓库面积
    private Double totleArea;

    //三方仓库总数量
    private Long threeGSPCount;

    //三方仓库面积
    private Double threeArea;

    //平台运力
    private Long totleDelivery;
}
