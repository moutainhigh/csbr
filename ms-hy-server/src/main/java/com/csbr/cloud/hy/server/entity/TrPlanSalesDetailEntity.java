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
@TableName("tr_plan_sales_detail")
@ApiModel(value = "计划销量明细实体")
public class TrPlanSalesDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @TableId(value = "guid")
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 计划销量GUID
     */
    @ApiModelProperty("计划销量GUID")
    private String planSalesGuid;

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
     * 包装单位
     */
    @ApiModelProperty("包装单位")
    private String unit;

    /**
     * 包装数量
     */
    @ApiModelProperty("包装数量")
    private BigDecimal unitQty;

    /**
     * 价格
     */
    @ApiModelProperty("价格")
    private BigDecimal price;

    /**
     * 预测总量
     */
    @ApiModelProperty("预测总量")
    private BigDecimal totalCalculate;

    /**
     * 1月
     */
    @ApiModelProperty("1月")
    private BigDecimal jan;

    /**
     * 2月
     */
    @ApiModelProperty("2月")
    private BigDecimal feb;

    /**
     * 3月
     */
    @ApiModelProperty("3月")
    private BigDecimal mar;

    /**
     * 4月
     */
    @ApiModelProperty("4月")
    private BigDecimal apr;

    /**
     * 5月
     */
    @ApiModelProperty("5月")
    private BigDecimal may;

    /**
     * 6月
     */
    @ApiModelProperty("6月")
    private BigDecimal jun;

    /**
     * 7月
     */
    @ApiModelProperty("7月")
    private BigDecimal jul;

    /**
     * 8月
     */
    @ApiModelProperty("8月")
    private BigDecimal aug;

    /**
     * 9月
     */
    @ApiModelProperty("9月")
    private BigDecimal sept;

    /**
     * 10月
     */
    @ApiModelProperty("10月")
    private BigDecimal oct;

    /**
     * 11月
     */
    @ApiModelProperty("11月")
    private BigDecimal nov;

    /**
     * 12月
     */
    @ApiModelProperty("12月")
    private BigDecimal dec;

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
