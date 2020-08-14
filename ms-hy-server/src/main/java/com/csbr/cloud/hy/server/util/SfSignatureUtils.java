package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangheng
 * @date 2020/6/22 17:50
 * 顺丰
 */
public class SfSignatureUtils {

    private SfSignatureUtils() {
        throw new IllegalStateException("SfSignatureUtils class");
    }

    public static String getSignature(String waybillNo,String checkPhoneNo) {
        //客户校验码    使用顺丰分配的客户校验码
        String md5key = "HwUAHAuPEFSWziyCHQRaXMjBJMWIWDpp";
        //时间戳 取报文中的timestamp（调用接口时间戳）
        String timestamp = sysDateTime();
        //业务报文  去报文中的msgData（业务数据报文）
        //顺丰路由查询接口
        JSONObject map = new JSONObject();
        map.put("language","0");
        map.put("trackingType","1");
        JSONArray waybillNoList = new JSONArray();
        waybillNoList.add(waybillNo);
        map.put("trackingNumber",waybillNoList);
        map.put("methodType","1");
        if(!StringUtils.isEmpty(checkPhoneNo)){
            map.put("checkPhoneNo",checkPhoneNo);
        }


        //顺丰下订单接口
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("language","zh_CN");
//        jsonObject.put("orderId","OrderNum20200612224");
//        JSONArray cargoDetails = new JSONArray();
//        JSONObject cargoDetail = new JSONObject();
//        cargoDetail.put("count",2.365);
//        cargoDetail.put("unit","个");
//        cargoDetail.put("weight",6.1);
//        cargoDetail.put("amount",100.5111);
//        cargoDetail.put("currency","HKD");
//        cargoDetail.put("name","护肤品1");
//        cargoDetail.put("sourceArea","CHN");
//        cargoDetails.add(cargoDetail);
//        JSONArray contactInfoList = new JSONArray();
//        JSONObject contactInfo = new JSONObject();
//        contactInfo.put("address","广东省深圳市南山区软件产业基地11栋");
//        contactInfo.put("contact","小曾");
//        contactInfo.put("contactType",1);
//        contactInfo.put("country","CN");
//        contactInfo.put("postCode","580058");
//        contactInfo.put("tel","4006789888");
//        JSONObject contactInfo1 = new JSONObject();
//        contactInfo1.put("address","广东省广州市白云区湖北大厦");
//        contactInfo1.put("company","顺丰速运");
//        contactInfo1.put("contact","小邱");
//        contactInfo1.put("contactType",2);
//        contactInfo1.put("country","CN");
//        contactInfo1.put("postCode","580058");
//        contactInfo1.put("tel","18688806057");
//        contactInfoList.add(contactInfo);
//        contactInfoList.add(contactInfo1);
//        jsonObject.put("cargoDetails",cargoDetails);
//        jsonObject.put("contactInfoList",contactInfoList);
        String msgData = map.toJSONString();
        //将业务报文+时间戳+校验码组合成需加密的字符串(注意顺序)
        String toVerifyText = msgData+timestamp+md5key;
        MessageDigest  md5 = null;
        try {
            //因业务报文中可能包含加号、空格等特殊字符，需要urlEnCode处理
            toVerifyText = URLEncoder.encode(toVerifyText,"UTF-8");
            //进行Md5加密
            md5= MessageDigest.getInstance("MD5");
            md5.update(toVerifyText.getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException e){

        }catch (UnsupportedEncodingException e){

        }

        byte[] md = md5.digest();
        //通过BASE64生成数字签名
        String msgDigest = new String(new BASE64Encoder().encode(md));

        return  msgDigest;
    }

    /**
     * 获取时间戳
     * @return
     */
    public static String sysDateTime(){
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    public static void main(String[] args) {

        //顺丰路由查询接口
        String waybillNo = "SF7001201322409";
        //电话号码后四位
        String checkPhoneNo = "";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("language","0");
        jsonObject.put("trackingType","1");
//        List<String> waybillNoList = new ArrayList<>();
        JSONArray waybillNoList = new JSONArray();
        waybillNoList.add(waybillNo);
        jsonObject.put("trackingNumber",waybillNoList);
        jsonObject.put("methodType","1");
        if(!StringUtils.isEmpty(checkPhoneNo)){
            jsonObject.put("checkPhoneNo",checkPhoneNo);
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("partnerID","YZtvYNdO_wLxKSi4V");
        map.set("requestID","HwUAHAuPEFSWziyCHQRaXMjBJMWIWDpp");
//        List<String> waybillNoList1 = new ArrayList<>();
        JSONArray waybillNoList1 = new JSONArray();
        waybillNoList1.add(waybillNo);
        map.set("serviceCode","EXP_RECE_SEARCH_ROUTES");
        map.set("timestamp",sysDateTime());
        map.set("msgDigest",getSignature(waybillNo,checkPhoneNo));
        map.set("msgData",jsonObject.toJSONString());
        System.out.println(jsonObject.toJSONString());
        HttpEntity request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        //测试环境
//        String url = "https://sfapi-sbox.sf-express.com/std/service";
        //正式环境
        String url = "https://sfapi.sf-express.com/std/service";
        String s = restTemplate.postForObject(url, request, String.class);
        System.out.println(s);
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
                                JSONObject routesJSONObject = routes.getJSONObject(i);
                                //时间
                                routesJSONObject.get("acceptTime");
                                System.out.println(routesJSONObject.get("acceptTime"));
                                //物流信息
                                routesJSONObject.get("remark");
                            }
                        }
                    }
                }
            }
        }

        //顺丰下订单接口
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("language","zh_CN");
//        jsonObject.put("orderId","OrderNum20200612224");
//        JSONArray cargoDetails = new JSONArray();
//        JSONObject cargoDetail = new JSONObject();
//        cargoDetail.put("count",2.365);
//        cargoDetail.put("unit","个");
//        cargoDetail.put("weight",6.1);
//        cargoDetail.put("amount",100.5111);
//        cargoDetail.put("currency","HKD");
//        cargoDetail.put("name","护肤品1");
//        cargoDetail.put("sourceArea","CHN");
//        cargoDetails.add(cargoDetail);
//        JSONArray contactInfoList = new JSONArray();
//        JSONObject contactInfo = new JSONObject();
//        contactInfo.put("address","广东省深圳市南山区软件产业基地11栋");
//        contactInfo.put("contact","小曾");
//        contactInfo.put("contactType",1);
//        contactInfo.put("country","CN");
//        contactInfo.put("postCode","580058");
//        contactInfo.put("tel","4006789888");
//        JSONObject contactInfo1 = new JSONObject();
//        contactInfo1.put("address","广东省广州市白云区湖北大厦");
//        contactInfo1.put("company","顺丰速运");
//        contactInfo1.put("contact","小邱");
//        contactInfo1.put("contactType",2);
//        contactInfo1.put("country","CN");
//        contactInfo1.put("postCode","580058");
//        contactInfo1.put("tel","18688806057");
//        contactInfoList.add(contactInfo);
//        contactInfoList.add(contactInfo1);
//        jsonObject.put("cargoDetails",cargoDetails);
//        jsonObject.put("contactInfoList",contactInfoList);
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.set("partnerID","YZtvYNdO_wLxKSi4V");
//        map.set("requestID","HwUAHAuPEFSWziyCHQRaXMjBJMWIWDpp");
//        map.set("serviceCode","EXP_RECE_CREATE_ORDER");
//        map.set("timestamp",sysDateTime());
//        map.set("msgDigest",getSignature(""));
//        map.set("msgData",jsonObject.toJSONString());
//        System.out.println(jsonObject.toJSONString());
//        HttpEntity request = new HttpEntity<>(map, headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        //测试环境
//        String url = "https://sfapi-sbox.sf-express.com/std/service";
//        String s = restTemplate.postForObject(url, request, String.class);
//        System.out.println(s);

    }

    /**
     * 获取顺丰路由查询接口xml【请求体的xml文件】
     * @param wayBillNo
     * @return
     */
//    public static String getRouteServiceRequestXml(String wayBillNo){
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append("<Request service='EXP_RECE_SEARCH_ROUTES' lang='zh-CN'>");
//        strBuilder.append("<Head>" + "YZtvYNdO_wLxKSi4V" + "</Head>");
//        strBuilder.append("<Body>");
//        strBuilder.append("<RouteRequest").append(" ");
//        strBuilder.append("language='0'").append(" ");
//        strBuilder.append("trackingType='1'").append(" ");
//        strBuilder.append("methodType='1'").append(" ");
//        strBuilder.append("trackingNumber='" + wayBillNo.trim() + "" + "'").append(" />");
//        strBuilder.append("</Body>");
//        strBuilder.append("</Request>");
//        return strBuilder.toString();
//    }



}
