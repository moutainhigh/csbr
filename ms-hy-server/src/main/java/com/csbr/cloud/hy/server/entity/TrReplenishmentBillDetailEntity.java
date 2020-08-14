package com.csbr.cloud.hy.server.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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
@TableName("tr_replenishment_bill_detail")
@ApiModel(value = "补货单明细实体")
public class TrReplenishmentBillDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识 与补货单表的GUID一致
     */
    @TableId(value = "guid")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 行号
     */
    @TableId(type=IdType.AUTO)
    @ApiModelProperty("行号")
    private Integer rowNo;

    /**
     * 商品GUID
     */
    @ApiModelProperty("商品GUID")
    private String goodsGuid;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 商品规格
     */
    @ApiModelProperty("商品规格")
    private String goodsSpec;

    /**
     * 商品类别
     */
    @ApiModelProperty("商品类别")
    private String goodsType;

    /**
     * 基本单位
     */
    @ApiModelProperty("基本单位")
    private String unit;

    /**
     * 注册证号/批准文号
     */
    @ApiModelProperty("注册证号/批准文号")
    private String registKey;

    /**
     * 生产厂商
     */
    @ApiModelProperty("生产厂商")
    private String producer;

    /**
     * 库存数
     */
    @ApiModelProperty("库存数")
    private BigDecimal stockQty;

    /**
     * 库存上限
     */
    @ApiModelProperty("库存上限")
    private BigDecimal inventoryCap;

    /**
     * 库存下限
     */
    @ApiModelProperty("库存下限")
    private BigDecimal inventoryLimit;

    /**
     * 本月计划销量
     */
    @ApiModelProperty("本月计划销量")
    private BigDecimal thisMonthPlannedSales;

    /**
     * 下月计划销量
     */
    @ApiModelProperty("下月计划销量")
    private BigDecimal nextMonthPlannedSales;

    /**
     * 本月出库量
     */
    @ApiModelProperty("本月出库量")
    private BigDecimal deliveryGoods;

    /**
     * 建议补货数量（按计划销量）
     */
    @ApiModelProperty("建议补货数量（按计划销量）")
    private BigDecimal replenishmentQtyPlan;

    /**
     * 周期内日均销量
     */
    @ApiModelProperty("周期内日均销量")
    private BigDecimal daySales;

    /**
     * 建议补货数量（按日均销量）
     */
    @ApiModelProperty("建议补货数量（按日均销量）")
    private BigDecimal replenishmentQtyDaySales;

    /**
     * 销量预测准确率
     */
    @ApiModelProperty("销量预测准确率")
    private BigDecimal accuracy;

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
    private Date date1;

    /**
     * 备用日期2
     */
    private Date date2;

    /**
     * 备用日期3
     */
    private Date date3;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;


}
