package com.csbr.cloud.flinkserver.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/11 9:21
 * 数据库Binlog实体属性
 */
@Data
public class BinLogMsgEntity implements Serializable {
    /**  **/
    private static final long serialVersionUID = 1L;

    @JSONField
    private List<JSONObject> data;

    @JSONField
    private String database;

    @JSONField
    private Long es;

    @JSONField
    private Long id;

    @JSONField
    private Boolean isDdl;

    @JSONField
    private String table;

    @JSONField
    private long ts;

    @JSONField
    private String type;

    @JSONField
    private List<JSONObject> mysqlType;

    @JSONField
    private List<JSONObject> old;

    @JSONField
    private JSONObject sqlType;

    @JSONField
    private String sql;

    @JSONField
    private List<String> pkNames;

}
