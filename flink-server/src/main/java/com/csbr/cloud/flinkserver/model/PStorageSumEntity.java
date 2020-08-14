package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/9 12:21
 * 平台仓储能力
 */
@Data
public class PStorageSumEntity {

    //GSP仓库总数量
    private Long totleGSP;

    //仓库面积
    private Double totleArea;

    //阴凉库面积
    private Double shadeArea;

    //三方仓库总数量
    private Long threeGSPCount;

    //三方仓库面积
    private Double threeArea;

    //冷库体积
    private Double chillArea;

    //三方仓辐射省
    private Long proCount;

    //三方仓辐射市
    private Long cityCount;
}
