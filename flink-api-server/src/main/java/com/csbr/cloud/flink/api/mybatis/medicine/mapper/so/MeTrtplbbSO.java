package com.csbr.cloud.flink.api.mybatis.medicine.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 物流业务单据汇总查询实体.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Data
@ApiModel(value = "物流业务单据汇总查询实体")
public class MeTrtplbbSO {

    /* 物流订单主表GUID(与物流订单主表的GUID一致) */
    @ApiModelProperty("物流订单主表GUID(与物流订单主表的GUID一致)")
    private String mainPoGuid;

    /* 医药订单(Y 是；N 否) */
    @ApiModelProperty("医药订单(Y 是；N 否)")
    private String isMedOrder;

}
