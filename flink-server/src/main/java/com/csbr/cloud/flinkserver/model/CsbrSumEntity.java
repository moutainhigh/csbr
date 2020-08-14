package com.csbr.cloud.flinkserver.model;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/12/18 16:42
 */
@Data
public class CsbrSumEntity {

    private Double chillArea;

    private Double shadeArea;

    private Double threeArea;

    private Double totleArea;

    private Long totleGsp;

    private Date createDate;

    private Date updateDate;

}
