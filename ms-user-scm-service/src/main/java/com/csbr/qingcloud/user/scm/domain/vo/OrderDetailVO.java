package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 订单详情对象
 * @author: yio
 * @create: 2020-08-12 12:04
 **/
@Data
@ApiModel("订单详情对象")
public class OrderDetailVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 订单GUID */
    @ApiModelProperty("订单GUID")
    private String orderGuid;

    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    private String goodsGuid;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String goodsCode;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String goodsName;
    /** 规格 */
    @ApiModelProperty("规格")
    private String goodsSpec;

    /** 单位 */
    @ApiModelProperty("单位")
    private String unitStyle;

    /** 装箱量 */
    @ApiModelProperty("装箱量")
    private Double bigUnitQty;

    /** 生产厂商 */
    @ApiModelProperty("生产厂商")
    private String producer;

    /** 数量 */
    @ApiModelProperty("数量")
    private Double qty;

    /** 开票价 */
    @ApiModelProperty("开票价")
    private Double price;

    /** 金额 */
    @ApiModelProperty("金额")
    private Double amount;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Timestamp updateTime;
}
