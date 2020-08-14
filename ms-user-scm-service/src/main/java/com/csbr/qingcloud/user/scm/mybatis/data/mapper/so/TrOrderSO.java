package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 订单查询实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@ApiModel(value = "订单查询实体")
public class TrOrderSO extends BasePageDTO {

    /** 客户编号 */
    @ApiModelProperty("客户编号")
    private String customerCode;

}
