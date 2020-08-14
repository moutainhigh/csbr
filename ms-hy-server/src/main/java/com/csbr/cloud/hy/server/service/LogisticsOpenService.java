package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.OTMSOrder;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/6/22 12:14
 */
public interface LogisticsOpenService {


    //申通快递
    CommonRes getStoLogisticsOpenInfo(String waybillNo);

    //圆通快递
    CommonRes getYtoLogisticsOpenInfo(String waybillNo);

    //中通快递
    CommonRes getZtoLogisticsOpenInfo(String waybillNo);

    //顺丰快递
    CommonRes getSfLogisticsOpenInfo(String waybillNo,String checkPhoneNo);

    //顺丰OTMS需求订单下单接口
    CommonRes createOTMSOrder(OTMSOrder otmsOrder) throws Exception;

    //需求订单取消
    CommonRes cancelOTMSOrder(String preOrderId) throws Exception;

    //需求订单路由查询
    CommonRes routeOTMSOrderList(String preOrderId) throws Exception;


}
