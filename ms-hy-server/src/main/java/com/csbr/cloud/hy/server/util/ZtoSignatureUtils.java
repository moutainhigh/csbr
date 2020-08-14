package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

/**
 * @author zhangheng
 * @date 2020/6/22 16:46
 * 中通快递
 */
public class ZtoSignatureUtils {

    private ZtoSignatureUtils() {
        throw new IllegalStateException("ZtoSignatureUtils class");
    }
    public static String getSignature(String waybillNo) {
        String companyId = "e6e081dfa9f14693a6d8f261765c4193";
        String key = "ffeb624a5b46";
        Map<String, String> parameters = new HashMap<>();
        List<String> waybillNoList = new ArrayList<>();
        waybillNoList.add(waybillNo);
        parameters.put("data", waybillNoList.toString());
        parameters.put("company_id", companyId);
        parameters.put("msg_type", "NEW_TRACES");
        String strToDigest = paramsToQueryString(parameters) + key;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){

        }

        md.update(strToDigest.getBytes(Charset.forName("UTF-8")));

        String dataDigest = Base64.getEncoder().encodeToString(md.digest());

        return dataDigest;
    }

    public static String paramsToQueryString(Map<String, String> params) {
        return params.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
    }

    public static void main(String[] args) {
//        String waybillNo = "680000000001";
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.set("x-companyId","e6e081dfa9f14693a6d8f261765c4193");
//        headers.set("x-dataDigest",getSignature(waybillNo));
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        List<String> waybillNoList = new ArrayList<>();
//        waybillNoList.add(waybillNo);
//        map.set("data", waybillNoList.toString());
//        map.set("company_id","e6e081dfa9f14693a6d8f261765c4193");
//        map.set("msg_type","NEW_TRACES");
//        HttpEntity request = new HttpEntity<>(map, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        //测试环境
//        String url = "https://japi.zto.com/traceInterfaceNewTraces";
//        String s = restTemplate.postForObject(url, request, String.class);
//        System.out.println(s);
//        JSONObject json = JSONObject.parseObject(s);
//        JSONArray data = json.getJSONArray("data");
//        if(data.size() > 0 && data != null){
//            JSONObject jsonObject = data.getJSONObject(0);
//            if(jsonObject != null){
//                //物流单号
//                System.out.println(jsonObject.get("billCode"));
//                JSONArray traces = jsonObject.getJSONArray("traces");
//                if(traces.size() > 0 && traces != null){
//                    for (int i = 0; i <traces.size() ; i++) {
//                        JSONObject tracesJSONObject = traces.getJSONObject(i);
//                        //物流信息
//                        System.out.println(tracesJSONObject.get("desc"));
//                        //时间
//                        tracesJSONObject.get("scanDate");
//                    }
//                }
//            }
//        }

    }

}
