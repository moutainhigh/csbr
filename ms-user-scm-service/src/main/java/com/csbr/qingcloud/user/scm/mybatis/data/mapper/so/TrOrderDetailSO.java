package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 订单明细查询实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@ApiModel(value = "订单明细查询实体")
public class TrOrderDetailSO extends BasePageDTO {

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String goodsCode;

}
