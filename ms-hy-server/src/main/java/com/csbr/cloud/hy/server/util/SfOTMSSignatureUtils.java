package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.csbr.cloud.hy.server.domain.dto.ExeOrderList;
import com.csbr.cloud.hy.server.domain.dto.OTMSOrder;
import com.csbr.cloud.hy.server.domain.dto.SkuList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/29 10:07
 * 顺丰OTMS接口
 */
@Slf4j
public class SfOTMSSignatureUtils {
    private SfOTMSSignatureUtils() {
        throw new IllegalStateException("SfOTMSSignatureUtils class");
    }

    public static String getCommonSignByMd5(JSONObject jsonObject) throws Exception{

//        jsonObject.put("customer_order_code","000000");
//        jsonObject.put("as_need_check","100000");
//        jsonObject.put("is_need_check","100000");
        jsonObject.put("_secret","123456");
//        JSONArray jsonArray = (JSONArray) JSON.parse("[{\"customer_order_code\":\"\",\"project_code\":\"P00000\",\"start_station_code\":\"xxxxx\",\"start_station_info\":{\"station_name\":\"站点名称\",\"contact_name\":\"联系人姓名\",\"contact_phone\":\"联系人电话\",\"station_province\":\"江苏省\",\"station_city\":\"无锡市\",\"station_district\":\"xx区\",\"station_address\":\"xxxx\"},\"end_station_code\":\"xxxxx\",\"end_station_info\":{\"station_name\":\"站点名称\",\"contact_name\":\"联系人姓名\",\"contact_phone\":\"联系人电话\",\"station_province\":\"江苏省\",\"station_city\":\"无锡市\",\"station_district\":\"xx区\",\"station_address\":\"xxxx\"},\"order_etd\":1555555555,\"order_eta\":1555555555,\"customer_order_time\":155555555,\"business_type\":1,\"product_type\":1,\"express_type\":1,\"pay_method\":1,\"order_total_volumn\":10,\"order_total_weight\":20,\"create_box_type\":1,\"total_box_count\":10,\"box_list\":[{\"customer_box_code\":\"xxxxxx\",\"box_length\":15,\"box_width\":20,\"box_height\":20,\"box_weight\":30,\"box_volume\":400,\"sku_list\":[{\"sku_id\":\"xxxxx\",\"carton_size\":\"CS\",\"sku_name\":\"sku名称\",\"sku_count\":2,\"sku_length\":20,\"sku_width\":20,\"sku_height\":20,\"sku_volume\":200}],\"userdef_xxx\":\"xxxxx\"}],\"remark\":\"备注\",\"userdef_xxx\":\"xxxxx\"}]",Feature.OrderedField);
//        jsonObject.put("order_list",jsonArray);


        JSONObject noNullValue = JsonDealUtils.getNoNullValue(jsonObject.toJSONString());
        log.info(JSON.toJSONString(noNullValue, SerializerFeature.MapSortField));
        log.info(md5(noNullValue.toJSONString()));
        return md5(noNullValue.toJSONString());

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

    public static void main(String[] args) {
        try {
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Content-type", "application/json;charset=utf-8");
            headers.set("customercode","CS0001");
            OTMSOrder otmsOrder = new OTMSOrder();
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
            String commonSignByMd5 = getCommonSignByMd5(o);
            o.put("_sign",commonSignByMd5);
            HttpEntity request = new HttpEntity<>(o, headers);
            log.info(o.toJSONString());
            RestTemplate restTemplate = new RestTemplate();
            //测试环境
            String url = "http://uat-ji.sftcwl.com.cn/open/externalapi/preorderimportapi";
            String s = restTemplate.postForObject(url, request, String.class);
            log.info(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
