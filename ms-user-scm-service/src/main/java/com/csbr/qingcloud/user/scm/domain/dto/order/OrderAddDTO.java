package com.csbr.qingcloud.user.scm.domain.dto.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.sql.Date;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 订单新增参数
 * @author: yio
 * @create: 2020-08-11 15:09
 **/
@Data
@ApiModel("订单新增参数")
public class OrderAddDTO {

    /** 所属租户GUID */
    @ApiModelProperty("所属租户GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /** 制单时间 */
    @ApiModelProperty("制单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inputTime;

    /** 客户GUID */
    @ApiModelProperty("客户GUID")
    @NotBlank(message = "客户GUID不能为空。")
    private String customerGuid;


    /** 备注 */
    @ApiModelProperty("备注")
    private String memo;

    /** 订单详情 */
    @Valid
    @ApiModelProperty("订单详情")
    private List<OrderDetailAddDTO> orderDetails;

}
