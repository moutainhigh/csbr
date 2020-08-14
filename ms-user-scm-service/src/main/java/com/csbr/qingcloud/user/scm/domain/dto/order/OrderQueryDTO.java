package com.csbr.qingcloud.user.scm.domain.dto.order;

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
 * @description: 订单查询参数
 * @author: yio
 * @create: 2020-08-12 11:52
 **/
@Data
@ApiModel("订单查询参数")
public class OrderQueryDTO extends BasePageDTO {
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    @LikeQuery
    private String goodsName;

    /** 客户名称 */
    @ApiModelProperty("科室名称")
    @LikeQuery
    private String customerName;

    /** 显示的明细数，为空时显示所有*/
    @ApiModelProperty("显示的明细数，为空时显示所有")
    private Integer dispDetailCount;


    /** 开始下单时间 */
    @ApiModelProperty("开始创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "inputTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "开始时间格式不正确。")
    private Date startTime;

    /** 结束下单时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "inputTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "结束时间格式不正确。")
    private Date endTime;
}
