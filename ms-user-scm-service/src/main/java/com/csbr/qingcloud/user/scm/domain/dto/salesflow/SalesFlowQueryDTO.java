package com.csbr.qingcloud.user.scm.domain.dto.salesflow;

import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向查询参数
 * @author: yio
 * @create: 2020-08-07 14:29
 **/
@Data
@ApiModel("销售流向查询参数")
public class SalesFlowQueryDTO extends BasePageDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 开始销售时间 */
    @ApiModelProperty("开始销售时间（yyyy-MM-dd）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "salesDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "开始时间格式不正确。")
    private Date startSalesDate;

    /** 结束销售时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "salesDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "结束时间格式不正确。")
    private Date endSaleDate;

    /** 客户名称 */
    @ApiModelProperty("客户名称")
    @LikeQuery
    private String customerName;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    @LikeQuery
    private String goodsName;
}

