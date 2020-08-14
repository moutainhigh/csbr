package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;


/**
 * 销售流量查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "销售流量查询实体")
public class TrSalesFlowSO extends BasePageDTO {

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

    /** 开始销售时间 */
    @ApiModelProperty("开始销售时间（yyyy-MM-dd）")
    private java.util.Date startSalesDate;

    /** 结束销售时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd）")
    private Date endSaleDate;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    private String customerName;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String goodsName;
}
