package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/6/22 10:32
 * 申通快递签名
 */
public class StoSignatureUtils {

    private StoSignatureUtils() {
        throw new IllegalStateException("StoSignatureUtils class");
    }

    public static String getSignature(String content, String secretKey) {
        String text = content + secretKey;
        byte[] md5 = DigestUtils.md5(text);
        return Base64.encodeBase64String(md5);
    }

    public static void main(String[] args) {
        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("x-www-form-urlencoded; charset=UTF-8");
//        headers.setContentType(type);
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
        waybillNoList.add("773031309794393");
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
            JSONArray jsonArray = data.getJSONArray("773031309794393");
            if(jsonArray.size() > 0 && jsonArray != null){
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    System.out.println(object.getString("opTime"));
                    System.out.println(object.getString("memo"));
                }
            }
        }
        System.out.println(s);
    }
}
