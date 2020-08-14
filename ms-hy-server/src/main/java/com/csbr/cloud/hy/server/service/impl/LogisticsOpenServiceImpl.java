package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.ExeOrderList;
import com.csbr.cloud.hy.server.domain.dto.OTMSOrder;
import com.csbr.cloud.hy.server.domain.dto.SkuList;
import com.csbr.cloud.hy.server.entity.LogisticsOpenEntity;
import com.csbr.cloud.hy.server.service.LogisticsOpenService;
import com.csbr.cloud.hy.server.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/6/22 12:18
 * 第三方物流接口
 */
@Service
@Slf4j
public class LogisticsOpenServiceImpl implements LogisticsOpenService {


    //获取第三方物流接口信息(申通快递)
    @Override
    public CommonRes getStoLogisticsOpenInfo(String waybillNo) {
        //物流信息
        List<LogisticsOpenEntity> logisticsOpenEntities = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Object> paramsMap = new LinkedMultiValueMap<>();
        //订阅方appkey，即合作方应用key。
        paramsMap.set("from_appkey", "CAKDLtYLKsEjCRk");
        //订阅方资源code，即合作方应用的资源code
        paramsMap.set("from_code", "CAKDLtYLKsEjCRk");
        //注册方appkey，即提供该订阅接口服务的申通应用key
        paramsMap.set("to_appkey", "sto_trace_query");
        //注册方资源code，即上述申通应用的资源code，目前值等于to_appkey
        paramsMap.set("to_code", "sto_trace_query");
        //接口名称(物流轨迹接口查询)
        paramsMap.set("api_name", "STO_TRACE_QUERY_COMMON");
        //构化的业务报文体，具体内容由订阅的接口规定（API文档中的 请求参数 表格），可以是 JSON 或 XML 格式的字串。注意：content内容中不允许有特殊字符，例如\u001b
        //排序方式
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("order","asc");
        List<String> waybillNoList = new ArrayList<>();
        waybillNoList.add(waybillNo);
        jsonObject.put("waybillNoList",waybillNoList);
        paramsMap.set("content", jsonObject.toJSONString());
        //秘钥key
        String linkAppScretKey = "OjAxqWK3Uokp32GGq67mAtbLK2d1A9fz";
        //生成签名
        String dataDigest = StoSignatureUtils.getSignature(jsonObject.toJSONString(), linkAppScretKey);
        //报文签名，网关会统一校验
        paramsMap.set("data_digest", dataDigest);
        HttpEntity request = new HttpEntity<>(paramsMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        //测试环境
//        String url = "http://cloudinter-linkgatewaytest.sto.cn/gateway/link.do";
        //正式环境
        String url = "https://cloudinter-linkgatewayonline.sto.cn/gateway/link.do";
        String s = restTemplate.postForObject(url, request, String.class);
        JSONObject json = JSONObject.parseObject(s);
        JSONObject data = json.getJSONObject("data");
        if(data != null){
            JSONArray jsonArray = data.getJSONArray(waybillNo);
            if(jsonArray.size() > 0 && jsonArray != null){
                for (int i = 0; i < jsonArray.size(); i++) {
                    LogisticsOpenEntity logisticsOpenEntity = new LogisticsOpenEntity();
                    JSONObject object = jsonArray.getJSONObject(i);
//                    System.out.println(object.getString("opTime"));
//                    System.out.println(object.getString("memo"));
                    logisticsOpenEntity.setTime(object.getString("opTime"));
                    logisticsOpenEntity.setProcessInfo(object.getString("memo"));
                    logisticsOpenEntities.add(logisticsOpenEntity);
                }
            }
        }
        return CommonRes.ok(logisticsOpenEntities);
    }

    //圆通快递
    @Override
    public CommonRes getYtoLogisticsOpenInfo(String waybillNo) {
        //物流信息
        List<LogisticsOpenEntity> logisticsOpenEntities = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("sign", YtoSignatureUtils.getSignature());
        //测试app_key
//        map.set("app_key","sF1Jzn");
        //正式app_key
        map.set("app_key","6fOvdg");
        map.set("format","JSON");
        map.set("method","yto.Marketing.WaybillTrace");
        map.set("timestamp",YtoSignatureUtils.sysDateTime());
        //测试user_id
//        map.set("user_id","YTOTEST");
        //正式user_id
        map.set("user_id","cOsuQH");
        map.set("v","1.01");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("Number","1111111111");
        jsonObject.put("Number",waybillNo);
        jsonArray.add(jsonObject);
        map.set("param",jsonArray.toJSONString());
        HttpEntity request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        //测试环境
//        String url = "http://opentestapi.yto.net.cn/service/waybill_query/v1/cOsuQH";
        //正式环境
        String url = "http://openapi.yto.net.cn/service/waybill_query/v1/cOsuQH";
        String s = restTemplate.postForObject(url, request, String.class);
        JSONArray json = JSONArray.parseArray(s);
        if(json.size() > 0 && json != null){
            for (int i = 0; i < json.size() ; i++) {
                LogisticsOpenEntity logisticsOpenEntity = new LogisticsOpenEntity();
                JSONObject object = json.getJSONObject(i);
                //运单号
//                object.get("waybill_No");
                //时间
//                object.get("upload_Time");
                //物流信息
//                object.get("processInfo");
                logisticsOpenEntity.setTime(object.getString("upload_Time"));
                logisticsOpenEntity.setProcessInfo(object.getString("processInfo"));
                logisticsOpenEntities.add(logisticsOpenEntity);
            }

        }
        return CommonRes.ok(logisticsOpenEntities);
    }

    //中通快递
    @Override
    public CommonRes getZtoLogisticsOpenInfo(String waybillNo) {
        //物流信息
        List<LogisticsOpenEntity> logisticsOpenEntities = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("x-companyId","e6e081dfa9f14693a6d8f261765c4193");
        headers.set("x-dataDigest", ZtoSignatureUtils.getSignature(waybillNo));
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        List<String> waybillNoList = new ArrayList<>();
        waybillNoList.add(waybillNo);
        map.set("data", waybillNoList.toString());
        map.set("company_id","e6e081dfa9f14693a6d8f261765c4193");
        map.set("msg_type","NEW_TRACES");
        HttpEntity request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        //测试环境
//        String url = "http://162.14.131.175:9001/traceInterfaceNewTraces";
        //正式环境
        String url = "https://japi.zto.com/traceInterfaceNewTraces";
        String s = restTemplate.postForObject(url, request, String.class);
        JSONObject json = JSONObject.parseObject(s);
        JSONArray data = json.getJSONArray("data");
        if(data.size() > 0 && data != null){
            JSONObject jsonObject = data.getJSONObject(0);
            if(jsonObject != null){
                //物流单号
                System.out.println(jsonObject.get("billCode"));
                JSONArray traces = jsonObject.getJSONArray("traces");
                if(traces.size() > 0 && traces != null){
                    for (int i = 0; i <traces.size() ; i++) {
                        LogisticsOpenEntity logisticsOpenEntity = new LogisticsOpenEntity();
                        JSONObject tracesJSONObject = traces.getJSONObject(i);
                        //物流信息
//                        System.out.println(tracesJSONObject.get("desc"));
                        //时间
//                        tracesJSONObject.get("scanDate");
                        logisticsOpenEntity.setTime(tracesJSONObject.getString("scanDate"));
                        logisticsOpenEntity.setProcessInfo(tracesJSONObject.getString("desc"));
                        logisticsOpenEntities.add(logisticsOpenEntity);
                    }
                }
            }
        }
        return CommonRes.ok(logisticsOpenEntities);
    }

    //顺丰快递
    @Override
    public CommonRes getSfLogisticsOpenInfo(String waybillNo,String checkPhoneNo) {
        //物流信息
        List<LogisticsOpenEntity> logisticsOpenEntities = new ArrayList<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("language","0");
        jsonObject.put("trackingType","1");
        List<String> waybillNoList = new ArrayList<>();
        waybillNoList.add(waybillNo);
        jsonObject.put("trackingNumber",waybillNoList.toString());
        jsonObject.put("methodType","1");
        if(!StringUtils.isEmpty(checkPhoneNo)){
            jsonObject.put("checkPhoneNo",checkPhoneNo);
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("partnerID","YZtvYNdO_wLxKSi4V");
        map.set("requestID","HwUAHAuPEFSWziyCHQRaXMjBJMWIWDpp");
        List<String> waybillNoList1 = new ArrayList<>();
        waybillNoList1.add(waybillNo);
        map.set("serviceCode","EXP_RECE_SEARCH_ROUTES");
        map.set("timestamp", SfSignatureUtils.sysDateTime());
        map.set("msgDigest",SfSignatureUtils.getSignature(waybillNo,checkPhoneNo));
        map.set("msgData",jsonObject.toJSONString());
        HttpEntity request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        //测试环境
//        String url = "https://sfapi-sbox.sf-express.com/std/service";
        //正式环境
        String url = "https://sfapi.sf-express.com/std/service";
        String s = restTemplate.postForObject(url, request, String.class);
        JSONObject parseObject = JSONObject.parseObject(s);
        if(parseObject != null){
            if(!StringUtils.isEmpty(parseObject.getString("apiResultData"))){
                JSONObject data = JSONObject.parseObject(parseObject.getString("apiResultData"));
                JSONObject msgData = data.getJSONObject("msgData");
                if(msgData != null){
                    JSONArray routeResps = msgData.getJSONArray("routeResps");
                    if(routeResps.size() > 0 && routeResps != null){
                        JSONObject routeRespsJSONObject = routeResps.getJSONObject(0);
                        //运单号
                        routeRespsJSONObject.get("mailNo");
                        JSONArray routes = routeRespsJSONObject.getJSONArray("routes");
                        if(routes.size() > 0 && routes != null){
                            for (int i = 0; i < routes.size(); i++) {
                                LogisticsOpenEntity logisticsOpenEntity = new LogisticsOpenEntity();
                                JSONObject routesJSONObject = routes.getJSONObject(i);
                                //时间
//                                routesJSONObject.get("acceptTime");
                                //物流信息
//                                routesJSONObject.get("remark");
                                logisticsOpenEntity.setTime(routesJSONObject.getString("acceptTime"));
                                logisticsOpenEntity.setProcessInfo(routesJSONObject.getString("remark"));
                                logisticsOpenEntities.add(logisticsOpenEntity);
                            }
                        }
                    }
                }
            }
        }
        return CommonRes.ok(logisticsOpenEntities);
    }

    /**
     * 需求订单下单接口
     * @param otmsOrder
     * @return
     */
    @Override
    public CommonRes createOTMSOrder(OTMSOrder otmsOrder) throws Exception {

        HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Content-type", "application/json;charset=utf-8");
        headers.set("customercode","CS0001");
        List<ExeOrderList> exeOrderLists = new ArrayList<>();
        ExeOrderList exeOrderList = new ExeOrderList();
        exeOrderList.setAddress_fetching_logic(4);
        List<SkuList> skuLists = new ArrayList<>();
        SkuList skuList = new SkuList();
        skuList.setSku_name("货物名");
        skuList.setSku_no("xx43344");
        skuList.setSku_count(20);
        skuList.setSku_volume("15");
        skuList.setSku_weight("0.1");
        skuLists.add(skuList);
        exeOrderList.setSku_list(skuLists);
        exeOrderLists.add(exeOrderList);
        otmsOrder.setCustomer_order_code("CSCODE0001");
        otmsOrder.setProject_code("P000002");
        otmsOrder.setCustomer_code("CS0001");
        otmsOrder.setExe_order_list(exeOrderLists);
        JSONObject o = (JSONObject) JSON.toJSON(otmsOrder);
        String commonSignByMd5 = SfOTMSSignatureUtils.getCommonSignByMd5(o);
        o.put("_sign",commonSignByMd5);
        HttpEntity request = new HttpEntity<>(o, headers);
        log.info(o.toJSONString());
        RestTemplate restTemplate = new RestTemplate();
        //UAT环境
        String url = "http://uat-ji.sftcwl.com.cn/open/externalapi/preorderimportapi";
        String s = restTemplate.postForObject(url, request, String.class);
        return null;
    }

    /**
     * 需求订单取消接口
     * @param preOrderId
     * @return
     * @throws Exception
     */
    @Override
    public CommonRes cancelOTMSOrder(String preOrderId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json;charset=utf-8");
        headers.set("customercode","CS0001");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pre_order_id",preOrderId);
        String commonSignByMd5 = SfOTMSSignatureUtils.getCommonSignByMd5(jsonObject);
        jsonObject.put("_sign",commonSignByMd5);
        HttpEntity request = new HttpEntity<>(jsonObject, headers);
        RestTemplate restTemplate = new RestTemplate();
        //UAT环境
        String url = "http://uat-ji.sftcwl.com.cn/open/externalapi/preordercancelapi";
        String s = restTemplate.postForObject(url, request, String.class);
        return null;
    }

    /**
     * 需求订单路由查询
     * @param preOrderId
     * @return
     * @throws Exception
     */
    @Override
    public CommonRes routeOTMSOrderList(String preOrderId) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json;charset=utf-8");
        headers.set("customercode","CS0001");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pre_order_id",preOrderId);
        String commonSignByMd5 = SfOTMSSignatureUtils.getCommonSignByMd5(jsonObject);
        jsonObject.put("_sign",commonSignByMd5);
        HttpEntity request = new HttpEntity<>(jsonObject, headers);
        RestTemplate restTemplate = new RestTemplate();
        //UAT环境
        String url = "http://uat-ji.sftcwl.com.cn/open/externalapi/preorderroutelistapi";
        String s = restTemplate.postForObject(url, request, String.class);
        return null;
    }
}
