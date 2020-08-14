package com.csbr.qingcloud.user.scm.domain.dto.salesindicators;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 客户销售指标查询参数
 * @author: yio
 * @create: 2020-08-07 13:35
 **/
@Data
@ApiModel("客户销售指标查询参数")
public class CustomerSalesIndicatorsQueryDTO extends BasePageDTO {
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 年月 */
    @ApiModelProperty("年月（yyyyMM）")
    @DateTimeFormat(pattern = "yyyyMM")
    private String yearMonth;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    @LikeQuery
    private String goodsName;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @LikeQuery
    private String customerName;

}
