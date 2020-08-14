package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 人员销售指标明细查询实体.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Data
@ApiModel(value = "人员销售指标明细查询实体")
public class TrStaffSalesIndicatorsDetailSO extends BasePageDTO {

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

}
