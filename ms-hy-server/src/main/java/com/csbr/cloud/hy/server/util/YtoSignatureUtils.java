package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangheng
 * @date 2020/6/22 12:53
 * 圆通签名生成规则
 */
public class YtoSignatureUtils {

    private YtoSignatureUtils() {
        throw new IllegalStateException("YtoSignatureUtils class");
    }

    public static String getSignature() {
        //生成A串
        StringBuilder sbA = new StringBuilder();
        //按字母顺序 将user_id、app_key、format、method、timestamp、v这六个字段按字母排序，再将字段对应的值放在字段后面，得到字符串A
        //测试签名
//        sbA.append("app_keysF1Jzn").append("formatJSON").append("methodyto.Marketing.WaybillTrace").append("timestamp"+YtoSignatureUtils.sysDateTime()).append("user_idYTOTEST").append("v1.01");
        //正式签名
        sbA.append("app_key6fOvdg").append("formatJSON").append("methodyto.Marketing.WaybillTrace").append("timestamp"+YtoSignatureUtils.sysDateTime()).append("user_idcOsuQH").append("v1.01");
        //生成串B
        StringBuilder sbB = new StringBuilder();
        //由开放平台分配给用户的Secret_Key，生成签名时使用
        //测试secret_key
//        String Secret_Key = "1QLlIZ";
        //正式secret_key
        String Secret_Key = "qUPsrd";
        sbB.append(Secret_Key).append(sbA);
        System.out.println(sbB);
        //生成串C 用32位MD5加密算法对字符串B进行加密，得到字符串C
        //然后将字符串C中的小写字母转成大写字母，得到字符串D
        String sign=MD5(sbB.toString()).toUpperCase();

        return sign;
    }

    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    /**
     *
     * 方法用途: 对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap   要排序的Map对象
     * @param urlEncode   是否需要URLENCODE
     * @param keyToLower    是否需要将Key转换为全大写
     *            true:key转化成大写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower)
    {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try
        {
            List<Map.Entry<String, String>> infoIds = new ArrayList<>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
            {

                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
                {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds)
            {
                if (StringUtils.isNotBlank(item.getKey())&&StringUtils.isNotBlank(item.getValue()))
                {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode)
                    {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower)
                    {
                        buf.append(key.toUpperCase() + "=" + val.toUpperCase());
                    } else
                    {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false)
            {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e)
        {
            return null;
        }
        return buff;
    }

    public static void main(String[] args) {
        System.out.println(getSignature());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.set("sign",getSignature());
        //测试
//        map.set("app_key","sF1Jzn");
        //正式
        map.set("app_key","6fOvdg");
        map.set("format","JSON");
        map.set("method","yto.Marketing.WaybillTrace");
        map.set("timestamp",sysDateTime());
        //测试
//        map.set("user_id","YTOTEST");
        //正式
        map.set("user_id","cOsuQH");
        map.set("v","1.01");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Number","1111111111");
        jsonArray.add(jsonObject);
        map.set("param",jsonArray.toJSONString());
        HttpEntity request = new HttpEntity<>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
//        //测试环境
//        String url = "http://opentestapi.yto.net.cn/service/waybill_query/v1/cOsuQH";
        //正式环境
        String url = "http://openapi.yto.net.cn/service/waybill_query/v1/cOsuQH";
//        String url = "https://oauth.jdwl.com/oauth/authorize?client_id=YOUR_APP_KEY&redirect_uri=urn:ietf:wg:oauth:2.0:oob&response_type=code";
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
//        URI uri = builder.queryParams(jsonObject).build().encode().toUri();
//        String s = restTemplate.getForObject(url, String.class);
        String s = restTemplate.postForObject(url, request, String.class);
        System.out.println(s);

    }



}
