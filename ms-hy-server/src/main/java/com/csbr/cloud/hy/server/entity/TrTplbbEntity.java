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
 * 物流业务单据汇总表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_tplbb")
@ApiModel(value = "物流业务单据汇总实体")
public class TrTplbbEntity implements Serializable {

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
     * 物流订单主表GUID
     */
    @ApiModelProperty("物流订单主表GUID")
    private String mainPoGuid;

    /**
     * 业务单号
     */
    @ApiModelProperty("业务单号")
    private String bBillNo;

    /**
     * 业务单据唯一编号
     */
    @ApiModelProperty("业务单据唯一编号")
    private String bBillUid;

    /**
     * 单据类型
1：入库订单；2：出库订单；3：生产入库:4：调拨订单；
     */
    @ApiModelProperty("单据类型 1：入库订单；2：出库订单；3：生产入库:4：调拨订单")
    private String billType;

    /**
     * 业务单据类型
10 采购入库；11 购进退；20 销售出库；21 购进退回；23 残损报废；24 调拨出库
     */
    @ApiModelProperty("业务单据类型 10 采购入库；11 购进退；20 销售出库；21 购进退回；23 残损报废；24 调拨出库")
    private String businessType;

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
     * 供应单位GUID
     */
    @ApiModelProperty("供应单位GUID")
    private String supplyCorpGuid;

    /**
     * 供应单位名称
     */
    @ApiModelProperty("供应单位名称")
    private String supplyCorpName;

    /**
     * 销售单位GUID
     */
    @ApiModelProperty("销售单位GUID")
    private String saleCorpGuid;

    /**
     * 销售单位名称
     */
    @ApiModelProperty("销售单位名称")
    private String saleCorpName;

    /**
     * 业务员GUID
     */
    @ApiModelProperty("业务员GUID")
    private String buyerGuid;

    /**
     * 业务员名称
     */
    @ApiModelProperty("业务员名称")
    private String buyerName;

    /**
     * 订单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("订单日期")
    private Date billDate;

    /**
     * 总件数
     */
    @ApiModelProperty("总件数")
    private BigDecimal pkgs;

    /**
     * 总体积
     */
    @ApiModelProperty("总体积")
    private BigDecimal volume;

    /**
     * 总重量
     */
    @ApiModelProperty("总重量")
    private BigDecimal weight;

    /**
     * 明细金额
     */
    @ApiModelProperty("明细金额")
    private BigDecimal detailSum;

    /**
     * 签收件数
单位：件
     */
    @ApiModelProperty("签收件数 单位：件")
    private BigDecimal recPkgs;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String state;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String memo;

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
