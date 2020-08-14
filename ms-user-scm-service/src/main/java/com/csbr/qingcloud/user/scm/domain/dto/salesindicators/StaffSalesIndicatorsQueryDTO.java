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
 * @description: 人员销售指标查询参数
 * @author: yio
 * @create: 2020-08-06 17:42
 **/
@Data
@ApiModel("人员销售指标查询参数")
public class StaffSalesIndicatorsQueryDTO extends BasePageDTO {

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

    /** 姓名 */
    @ApiModelProperty("姓名")
    @LikeQuery
    private String name;
}
