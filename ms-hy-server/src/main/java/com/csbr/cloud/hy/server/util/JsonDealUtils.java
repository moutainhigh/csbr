package com.csbr.cloud.hy.server.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhangheng
 * @date 2020/7/29 10:22
 */
@Slf4j
public class JsonDealUtils {

    public static JSONObject getNoNullValue(String json) {
        JSONObject objTem= JSON.parseObject(json);
        JSONObject objRel=JSON.parseObject(json);
        return deal(objTem,objRel);
    }
    //去除空串
    private static JSONObject deal(JSONObject objTem,JSONObject objRel) {
        Set<String> keySet = objTem.keySet();
        Iterator<String> iterator = keySet.iterator();
        ArrayList<String> list = new ArrayList<String>();
        while(iterator.hasNext()) {
            String temp =  iterator.next();
            Object objR = objTem.get(temp);
//            list.add(temp);
            if(temp==null||"".equals(temp)||"null".equals(temp)) {
                objRel.remove(temp);
                continue;
            }
            if(objR==null||"".equals(objR.toString())||"null".equals(objR.toString())||"[]".equals(objR.toString())||"{}".equals(objR.toString())) {
                objRel.remove(temp);
                continue;
            }
            if(objR instanceof JSONObject) {
                JSONObject j=(JSONObject)objR;
                JSONObject object2 = (JSONObject)objRel.get(temp);
                deal(j,object2);
                continue;
            }
            if(objR instanceof JSONArray) {
                JSONArray jsonArray = objTem.getJSONArray(temp);
                JSONArray jsonArray2 = objRel.getJSONArray(temp);
                for(int i=0;i<jsonArray.size();i++) {
                    deal(jsonArray.getJSONObject(i),jsonArray2.getJSONObject(i));
                }
            }
        }
//        Collections.sort(list);
//        for (String key:list) {
//            log.info(key);
//        }
        return objRel;
    }

    /**
     * 按照字母顺序排序
     */
    public static JSONArray sort(JSONArray data){
        List<JSONObject> list = JSONObject.parseArray(data.toJSONString(), JSONObject.class);
        Collections.sort(list, (JSONObject o1, JSONObject o2) -> {
            //转成JSON对象中保存的值类型
            double a = Double.parseDouble(o1.getString("value"));
            double b = Double.parseDouble(o2.getString("value"));
            // 如果a, b数据类型为int，可直接 return a - b ;(升序，降序为 return b - a;)
            if (a < b) {  //降序排列，升序改成a>b
                return 1;
            } else if(a == b) {
                return 0;
            } else {
                return -1;
            }
        });
        data = JSONArray.parseArray(JSON.toJSONString(list));

        return data;
    }

}
