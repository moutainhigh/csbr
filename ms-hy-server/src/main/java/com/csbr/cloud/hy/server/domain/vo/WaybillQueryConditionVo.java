package com.csbr.cloud.hy.server.domain.vo;

import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/6/22 20:03
 */
@Data
public class WaybillQueryConditionVo {

    //商家编码
    private String customerCode;

    //运单号
    private String deliveryId;

    //用户pin
    private String userPin;
}
