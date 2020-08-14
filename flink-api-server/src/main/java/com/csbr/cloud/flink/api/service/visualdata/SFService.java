package com.csbr.cloud.flink.api.service.visualdata;

/**
 * @program: flink-api-service
 * @description: 四方云大屏服务
 * @author: Huanglh
 * @create: 2020-01-16 11:44
 **/
public interface SFService {
    /**
     * 依据大屏数据类型获取对应的json字符串
     *
     * @param boardDataType
     * @return
     */
    String getJson(String boardDataType);
}
