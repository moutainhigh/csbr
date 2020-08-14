package com.csbr.cloud.flinkserver.constant;

/**
 * @author zhangheng
 * @date 2019/12/17 11:28
 */
public class PropertiesConstants {
    public static final String STREAM_PARALLELISM = "stream.parallelism";
    public static final String STREAM_SINK_PARALLELISM = "stream.sink.parallelism";
    public static final String STREAM_DEFAULT_PARALLELISM = "stream.default.parallelism";
    public static final String STREAM_CHECKPOINT_ENABLE = "stream.checkpoint.enable";
    public static final String STREAM_CHECKPOINT_DIR = "stream.checkpoint.dir";
    public static final String STREAM_CHECKPOINT_TYPE = "stream.checkpoint.type";
    public static final String STREAM_CHECKPOINT_INTERVAL = "stream.checkpoint.interval";
    public static final String PROPERTIES_FILE_NAME = "/application.properties";
    public static final String CHECKPOINT_MEMORY = "memory";
    public static final String CHECKPOINT_FS = "fs";
    public static final String CHECKPOINT_ROCKETSDB = "rocksdb";

    //es config
    public static final String ELASTICSEARCH_BULK_FLUSH_MAX_ACTIONS = "elasticsearch.bulk.flush.max.actions";
    public static final String ELASTICSEARCH_HOSTS = "elasticsearch.hosts";

    //ms path
    public static final String FLINK_API_SERVER_IP = "http://49.4.23.228:";
    public static final String FLINK_API_SERVER_PORT = "8501";
    /**检查用户是否已存在**/
    public static final String FLINK_SUPPORT_CUS_CHECK_CUS_EXIST = "/flink-api-service/flink-support/cus/check-cus-exist";
    /**根据用户的名称返回用户GUID。查找不到用户，返回空字符串，并有提示**/
    public static final String FLINK_SUPPORT_CUS_GET_CUS_GUID = "/flink-api-service/flink-support/cus/get-cus-guid";
    /**根据数据源用户GUID返回用户GUID。查找不到GUID，返回空字符串，并有提示**/
    public static final String FLINK_SUPPORT_CUS_GET_GUID_BY_SOURCE = "/flink-api-service/flink-support/cus/get-guid-by-source";
    /**关联查询：药链云 检查和 Trtplbb 表的关联情况**/
    public static final String FLINK_SUPPORT_JOIN_ME_CHECK_TRTPLBB = "/flink-api-service/flink-support/join/me-check-trtplbb";
    /**关联查询：药链云 检查 Trtplbb 表 isMedOrder 是否为 Y**/
    public static final String FLINK_SUPPORT_JOIN_ME_CHECK_TRTPLBB_ISMEDORDER = "/flink-api-service/flink-support/join/me-check-trtplbb-ismedorder";
    /**关联查询：药链云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false**/
    public static final String FLINK_SUPPORT_JOIN_ME_CHECK_VEHICLE_TYPE = "/flink-api-service/flink-support/join/me-check-vehicle-type";
    /**关联查询：四方云 检查和 Trtplbb 表的关联情况**/
    public static final String FLINK_SUPPORT_JOIN_TR_CHECK_TRTPLBB = "/flink-api-service/flink-support/join/tr-check-trtplbb";
    /**关联查询：四方云 检查 Trtplbb 表 isMedOrder 是否为 Y**/
    public static final String FLINK_SUPPORT_JOIN_TR_CHECK_TRTPLBB_ISMEDORDER = "/flink-api-service/flink-support/join/tr-check-trtplbb-ismedorder";
    /**关联查询：四方云 mfvehicle 表关联 mfvehicletype 是否存在数据。存在数据：true，不存在：false**/
    public static final String FLINK_SUPPORT_JOIN_TR_CHECK_VEHICLE_TYPE = "/flink-api-service/flink-support/join/tr-check-vehicle-type";

}
