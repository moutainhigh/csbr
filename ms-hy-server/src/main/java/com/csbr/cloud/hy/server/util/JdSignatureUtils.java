package com.csbr.cloud.hy.server.util;

import com.csbr.cloud.hy.server.domain.vo.WaybillQueryConditionVo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhangheng
 * @date 2020/6/22 19:54
 * 京东物流
 */
public class JdSignatureUtils {

    private JdSignatureUtils() {
        throw new IllegalStateException("JdSignatureUtils class");
    }

    private String buildSign(String paramJson , String accessToken ,
                             String appKey, String appSecret) throws Exception {

        //第一步，按照顺序填充参数

        Map<String, String> map = new TreeMap<>();

        map.put("timestamp", sysDateTime());

        map.put("v", "2.0");
        //运单详情查询
        map.put("method", "jingdong.ldop.oms.api.order.queryWaybillDetail");

        map.put("param_json", paramJson);

        map.put("access_token", accessToken);

        map.put("app_key", appKey);



        StringBuilder sb = new StringBuilder(appSecret);

        //按照规则拼成字符串

        for (Map.Entry entry : map.entrySet()) {

            String name = (String) entry.getKey();

            String value = (String) entry.getValue();

            sb.append(name).append(value);

        }

        sb.append(appSecret);
        //MD5
        return md5(sb.toString());

    }



    public static String md5(String source) throws Exception {

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] bytes = md.digest(source.getBytes("utf-8"));

        return byte2hex(bytes);

    }



    private static String byte2hex(byte[] bytes) {

        StringBuilder sign = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {

            String hex = Integer.toHexString(bytes[i] & 0xFF);

            if (hex.length() == 1) {

                sign.append("0");

            }

            sign.append(hex.toUpperCase());

        }

        return sign.toString();

    }

    /**
     * 获取时间戳
     * @return
     */
    public static String sysDateTime(){
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sysDatetime = fmt.format(rightNow.getTime());
        return sysDatetime;
    }

    public static void main(String[] args) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("method","jingdong.ql.trace.queryCTraceForOuterJD");
        map.set("access_token","采用OAuth授权方式为必填参数");
        map.set("app_key","应用的app_key");
        map.set("timestamp",sysDateTime());
        map.set("v","2.0");
        WaybillQueryConditionVo waybillQueryConditionDTO = new WaybillQueryConditionVo();
        waybillQueryConditionDTO.setDeliveryId("test");
        map.set("waybillQueryConditionVo",waybillQueryConditionDTO.toString());
        HttpEntity request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        //测试环境
        String url = "https://test-proxy.jdwl.com/routerjson";
        //正式环境 https://api.jdwl.com/routerjson
        String s = restTemplate.postForObject(url, request, String.class);
        System.out.println(s);


    }


}
