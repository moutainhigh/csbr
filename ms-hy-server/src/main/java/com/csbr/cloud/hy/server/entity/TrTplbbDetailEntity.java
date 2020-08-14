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
 * 物流业务单据明细表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_tplbb_detail")
@ApiModel(value = "物流业务单据明细实体")
public class TrTplbbDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
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
     * 业务单据编号
     */
    @ApiModelProperty("业务单据编号")
    private String bBillNo;

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
     * 包装数
     */
    @ApiModelProperty("包装数")
    private BigDecimal unitQty;

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
     * 批号
     */
    @ApiModelProperty("批号")
    private String lot;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("生产日期")
    private Date productionDate;

    /**
     * 有效期至
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("有效期至")
    private Date expireDate;

    /**
     * 件数
     */
    @ApiModelProperty("件数")
    private BigDecimal pkgs;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private BigDecimal qty;

    /**
     * 商品重量
     */
    @ApiModelProperty("商品重量")
    private BigDecimal weight;

    /**
     * 商品体积
     */
    @ApiModelProperty("商品体积")
    private BigDecimal volume;

    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private BigDecimal price;

    /**
     * 金额
     */
    @ApiModelProperty("金额")
    private BigDecimal amount;

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
