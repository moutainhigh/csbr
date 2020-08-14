package com.csbr.cloud.flink.api.mybatis.transport.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 运输车辆类型查询实体.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Data
@ApiModel(value = "运输车辆类型查询实体")
public class TrMfvehicletypeSO {

    /* 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /* 车种 */
    @ApiModelProperty("车种")
    private String vehicleKind;

}
