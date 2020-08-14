package com.csbr.cloud.hy.server.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 每日订单执行时效统计
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_daily_order_time_limit")
@ApiModel(value = "每日订单执行时效统计实体")
public class TrDailyOrderTimeLimitEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @TableId(value = "guid")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 所属租户GUID
     */
    @ApiModelProperty("所属租户GUID")
    private String tenantGuid;

    /**
     * 所属公司
     */
    @ApiModelProperty("所属公司")
    private String corpGuid;

    /**
     * 所属货主GUID
     */
    @ApiModelProperty("所属货主GUID")
    private String cargoOwnerGuid;

    /**
     * 所属货主名称
     */
    @ApiModelProperty("所属货主名称")
    private String cargoOwnerName;

    /**
     * 承运商GUID
     */
    @ApiModelProperty("承运商GUID")
    private String deliverGuid;

    /**
     * 承运商名称
     */
    @ApiModelProperty("承运商名称")
    private String deliverName;

    /**
     * 发货量
     */
    @ApiModelProperty("发货量")
    private BigDecimal totleDeliverGoods;

    /**
     * 提货超时
     */
    @ApiModelProperty("提货超时")
    private BigDecimal totleDeliveryTimeout;

    /**
     * 到站/签收超时
     */
    @ApiModelProperty("到站/签收超时")
    private BigDecimal totleSignTimeout;

    /**
     * 备用字符1
     */
    private String varchar1;

    /**
     * 备用字符2
     */
    private String varchar2;

    /**
     * 备用字符3
     */
    private String varchar3;

    /**
     * 备用字符4
     */
    private String varchar4;

    /**
     * 备用数字1
     */
    private BigDecimal num1;

    /**
     * 备用数字2
     */
    private BigDecimal num2;

    /**
     * 备用数字3
     */
    private BigDecimal num3;

    /**
     * 备用日期1
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date1;

    /**
     * 备用日期2
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date2;

    /**
     * 备用日期3
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date3;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


}
