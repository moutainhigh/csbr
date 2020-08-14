package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;


/**
 * 人员销售指标查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "人员销售指标查询实体")
public class TrStaffSalesIndicatorSO extends BasePageDTO {

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /** 商品GUID */
    @ApiModelProperty("商品GUID")
    private String goodsGuid;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

    /** 年月 */
    @ApiModelProperty("年月（yyyyMMdd）")
    private Date date;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;

}
