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
 * 
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_replenishment_bill")
@ApiModel(value = "补货单实体")
public class TrReplenishmentBillEntity implements Serializable {

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
     * 所属公司GUID
     */
    @ApiModelProperty("所属公司GUID")
    private String corpGuid;

    /**
     * 货主GUID
     */
    @ApiModelProperty("货主GUID")
    private String cargoOwnerGuid;

    /**
     * 货主名称
     */
    @ApiModelProperty("货主名称")
    private String cargoOwnerName;

    /**
     * 补货单号
     */
    @ApiModelProperty("补货单号")
    private String bBillNo;

    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("单据日期")
    private Date billDate;

    /**
     * 缺货仓库GUID
     */
    @ApiModelProperty("缺货仓库GUID")
    private String transferWHGuid;

    /**
     * 缺货仓库名称
     */
    @ApiModelProperty("缺货仓库名称")
    private String transferWHName;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String receivingAddress;

    /**
     * 补货仓仓库GUID
     */
    @ApiModelProperty("补货仓仓库GUID")
    private String replenishmentWHGuid;

    /**
     * 补货仓仓库名称
     */
    @ApiModelProperty("补货仓仓库名称")
    private String replenishmentWHName;

    /**
     * 补货单产生方式
1 自动产生 2 手工录入
     */
    @ApiModelProperty("补货单产生方式 1 自动产生 2 手工录入")
    private String requisitionMode;

    /**
     * 是否冷链单据
Y 冷藏;N 否;C 冷冻
     */
    @ApiModelProperty("是否冷链单据 Y 冷藏;N 否;C 冷冻")
    private String isCcbb;

    /**
     * Y 是;N 否;  三方货主的需要审核
     */
    @ApiModelProperty("Y 是;N 否;  三方货主的需要审核")
    private String isNeedAudit;

    /**
     * 状态
W 初始；S 提交；E 作废
     */
    @ApiModelProperty("状态 W 初始；S 提交；E 作废")
    private String state;

    /**
     * 紧急程度
1：一般 2：紧急
     */
    @ApiModelProperty("紧急程度 1：一般 2：紧急")
    private String urgency;

    /**
     * 补货类型
1 通常补货；2 计划补货 
     */
    @ApiModelProperty("补货类型 1 通常补货；2 计划补货")
    private String replenishmentType;

    /**
     * 品规数
     */
    @ApiModelProperty("品规数")
    private Integer goodsSpecNum;

    /**
     * 建议补货数量
单位：件
     */
    @ApiModelProperty("建议补货数量 单位：件")
    private BigDecimal totalQty;

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
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 修改日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


}
