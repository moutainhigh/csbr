package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 客户销售指标查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "客户销售指标查询实体")
public class TrCustomerSalesIndicatorSO extends BasePageDTO {

    /** 客户Guid */
    @ApiModelProperty("客户Guid")
    private String customerGuid;

    /** 商品GUID */
    @ApiModelProperty("商品GUID")
    private String goodsGuid;

}
