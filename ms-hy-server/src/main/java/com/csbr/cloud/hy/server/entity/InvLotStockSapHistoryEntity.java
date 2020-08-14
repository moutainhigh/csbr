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
@TableName("inv_lot_stock_sap_history")
@ApiModel(value = "SAP商品历史库存实体")
public class InvLotStockSapHistoryEntity implements Serializable {

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
     * 所属仓库GUID
     */
    @ApiModelProperty("所属仓库GUID")
    private String warehouseGuid;

    /**
     * 所属仓库名称
     */
    @ApiModelProperty("所属仓库名称")
    private String warehouseName;

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
     * 注册证号/批准文号
     */
    @ApiModelProperty("注册证号/批准文号")
    private String registKey;

    /**
     * 生产厂商
     */
    @ApiModelProperty("生产厂商")
    private String manufacturer;

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
     * 库存数量
     */
    @ApiModelProperty("库存数量")
    private BigDecimal stockQty;

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
     * 库存状态
1 合格
2 抽检
3 质量停销
4 质量疑问
5 待处理
6 待退出
7 不合格
8 批号停销
9 近效期停销
     */
    @ApiModelProperty("库存状态")
    private String stockState;

    /**
     * 托盘/储位号
     */
    @ApiModelProperty("托盘/储位号")
    private String locationNo;

    /**
     * 货位类型
1 整托货位；
2 箱分拣货位；
3 零分拣货位
     */
    @ApiModelProperty("货位类型")
    private String locationType;

    /**
     * 最新入库日期
     */
    @ApiModelProperty("最新入库日期")
    private Date storageDate;

    /**
     * 最后出库日期
     */
    @ApiModelProperty("最后出库日期")
    private Date lastOutDate;

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
