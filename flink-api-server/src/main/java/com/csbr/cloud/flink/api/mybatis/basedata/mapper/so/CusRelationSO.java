package com.csbr.cloud.flink.api.mybatis.basedata.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 查询实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
@ApiModel(value = "查询实体")
public class CusRelationSO {
    /** 源数据GUID */
    @ApiModelProperty(value = "源数据GUID")
    private String sourceGuid;

    /** 医链云、药链云、四方云 */
    @ApiModelProperty(value = "1.医链云、2.药链云、3.四方云")
    private String sourceData;
}
