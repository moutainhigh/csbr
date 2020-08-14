package com.csbr.cloud.flinkserver.feign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.constant.PropertiesConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangheng
 * @date 2020/1/14 14:58
 */
@Slf4j
public class FlinkApiServer {

    static {


    }

    /**
     * 检查用户是否已经存在
     * @param chineseName
     * @return
     */
    public static Boolean checkCusExist(String chineseName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(chineseName, headers);
        RestTemplate checkCusExitsRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = checkCusExitsRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_CUS_CHECK_CUS_EXIST, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body.get("msg") != null && body.get("msg").equals("sucess")) {
            if (body.get("data") != null) {
                Boolean result = body.getBoolean("data");
                return result;
            }
        }
        return null;
    }

    /**
     * 根据用户的名称返回用户GUID。查找不到用户，返回空字符串，并有提示
     * @param chineseName
     * @return
     */
    public static String getCusGuid(String chineseName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(chineseName, headers);
        RestTemplate getCusGuidRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = getCusGuidRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_CUS_GET_CUS_GUID, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                return body.get("data").toString();
            }
        }
        return null;
    }

    /**
     * 根据数据源用户GUID返回用户GUID。查找不到GUID，返回空字符串，并有提示
     * @param jsonObject
     * @return
     */
    public static String getGuidBySource(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate getGuidBySourceRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = getGuidBySourceRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_CUS_GET_GUID_BY_SOURCE, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                return body.get("data").toString();
            }
        }
        return null;
    }

    /**
     * 关联查询：药链云 检查和 Trtplbb 表的关联情况
     * @param jsonObject
     * @return
     */
    public static JSONObject meCheckTrtplbb(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate meCheckTrtplbbRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = meCheckTrtplbbRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_ME_CHECK_TRTPLBB, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return (JSONObject) body.get("data");
                }
            }
        }
        return null;
    }

    /**
     * 关联查询：药链云 检查 Trtplbb 表 isMedOrder 是否为 Y
     * @param jsonObject
     * @return
     */
    public static Boolean meCheckTrtplbbIsmedorder(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate meCheckTrtplbbIsmedorderRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = meCheckTrtplbbIsmedorderRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_ME_CHECK_TRTPLBB_ISMEDORDER, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return body.getBoolean("data");
                }
            }
        }
        return null;
    }

    /**
     * 关联查询：药链云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false
     * @param jsonObject
     * @return
     */
    public static Boolean meCheckVehicleType(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate meCheckVehicleTypeRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = meCheckVehicleTypeRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_ME_CHECK_VEHICLE_TYPE, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return body.getBoolean("data");
                }
            }
        }
        return null;
    }

    /**
     * 关联查询：四方云 检查和 Trtplbb 表的关联情况
     * @param jsonObject
     * @return
     */
    public static JSONObject trCheckTrtplbb(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate trCheckTrtplbbRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = trCheckTrtplbbRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_TR_CHECK_TRTPLBB, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return (JSONObject) body.get("data");
                }
            }
        }
        return null;
    }

    /**
     * 关联查询：四方云 检查 Trtplbb 表 isMedOrder 是否为 Y
     * @param jsonObject
     * @return
     */
    public static Boolean trCheckTrtplbbIsmedorder(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate trCheckTrtplbbIsmedorderRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = trCheckTrtplbbIsmedorderRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_TR_CHECK_TRTPLBB_ISMEDORDER, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return body.getBoolean("data");
                }
            }
        }
        return null;
    }

    /**
     * 关联查询：四方云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false
     * @param jsonObject
     * @return
     */
    public static Boolean trCheckVehicleType(JSONObject jsonObject){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(jsonObject, headers);
        RestTemplate trCheckVehicleTypeRestTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = trCheckVehicleTypeRestTemplate.postForEntity(PropertiesConstants.FLINK_API_SERVER_IP+PropertiesConstants.FLINK_API_SERVER_PORT+PropertiesConstants.FLINK_SUPPORT_JOIN_TR_CHECK_VEHICLE_TYPE, request, String.class);
        log.info("通过feign获取:"+responseEntity.getBody());
        //转换jsonobject
        JSONObject body = (JSONObject) JSON.parse(responseEntity.getBody());
        if(body != null){
            if(body.get("msg") != null && body.get("msg").equals("sucess")){
                if(body.get("data") != null && StringUtils.isNotEmpty(body.get("data")+"")){
                    return body.getBoolean("data");
                }
            }
        }
        return null;
    }
}
