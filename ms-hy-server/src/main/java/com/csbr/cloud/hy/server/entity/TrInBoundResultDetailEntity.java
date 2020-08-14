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
 * @since 2020-05-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_in_bound_result_detail")
@ApiModel(value = "入库历史明细实体")
public class TrInBoundResultDetailEntity implements Serializable {

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
     * 包装数
     */
    @ApiModelProperty("包装数")
    private BigDecimal unitQty;

    /**
     * 批号
     */
    @ApiModelProperty("批号")
    private String lot;

    /**
     * 生产日期
     */
    @ApiModelProperty("生产日期")
    private Date productionDate;

    /**
     * 有效期至
     */
    @ApiModelProperty("有效期至")
    private Date expireDate;

    /**
     * 计划数量
     */
    @ApiModelProperty("计划数量")
    private BigDecimal planQty;

    /**
     * 件数
     */
    @ApiModelProperty("件数")
    private BigDecimal pkgs;

    /**
     * 零散数
     */
    @ApiModelProperty("零散数")
    private BigDecimal scatteredQty;

    /**
     * 入库数量
     */
    @ApiModelProperty("入库数量")
    private BigDecimal qty;

    /**
     * 收货日期
     */
    @ApiModelProperty("收货日期")
    private Date receiveDate;

    /**
     * 拒收数量
     */
    @ApiModelProperty("拒收数量")
    private BigDecimal rejectQty;

    /**
     * 拒收原因
     */
    @ApiModelProperty("拒收原因")
    private String rejectReason;

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
