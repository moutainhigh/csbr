package com.csbr.cloud.flinkserver.config;

/**
 * @author zhangheng
 * @date 2019/12/12 15:35
 * 参数设置
 */
public class KafkaConstant {
    public static final String BROKER_LIST = "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094";
    //flink自由数据源
    public static final String TOPIC1 = "canal";
    //药链云数据源
    public static final String TOPIC2 = "CSBR20_ZS";
    //医链云数据源
    public static final String TOPIC3 = "drugcloud_prod";
    //四方云数据源
    public static final String TOPIC4 = "csbr_sf";
    public static final String GROUP_ID = "";
}
