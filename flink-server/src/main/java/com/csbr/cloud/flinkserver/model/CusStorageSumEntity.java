package com.csbr.cloud.flinkserver.model;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/1/8 11:03
 */
@Data
public class CusStorageSumEntity {

    private String guid;

    private Long totleGSP;

    private Double totleArea;

    private Long threeGSPCount;

    private Double threeArea;

}
