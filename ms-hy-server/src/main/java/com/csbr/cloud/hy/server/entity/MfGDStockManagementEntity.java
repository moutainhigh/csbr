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
@TableName("mf_g_d_stock_management")
@ApiModel(value = "商品库管参数配置实体")
public class MfGDStockManagementEntity implements Serializable {

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
     * 商品GUID
     */
    @ApiModelProperty("商品GUID")
    private String goodGuid;

    /**
     * 物流中心GUID
     */
    @ApiModelProperty("物流中心GUID")
    private String carrierGuid;

    /**
     * 物流中心名称
     */
    @ApiModelProperty("物流中心名称")
    private String carrierName;

    /**
     * 仓库GUID
     */
    @ApiModelProperty("仓库GUID")
    private String warehouseGuid;

    /**
     * 仓库名称
     */
    @ApiModelProperty("仓库名称")
    private String warehouseName;

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
     * 补货周期
     */
    @ApiModelProperty("补货周期")
    private BigDecimal replenishmentCycle;

    /**
     * 库存周转合理天数
     */
    @ApiModelProperty("库存周转合理天数")
    private BigDecimal rinvtod;

    /**
     * 库存滞销系数
     */
    @ApiModelProperty("库存滞销系数")
    private BigDecimal cInvUnsaleable;

    /**
     * 库存滞销天数
     */
    @ApiModelProperty("库存滞销天数")
    private BigDecimal dInvUnsaleable;

    /**
     * 仓库补货级次
{RO:
    [
     {WHGUID:"仓库GUID",WHName:"仓库名称",LastOrders:0},
     {WHGUID:"仓库1GUID",WHName:"仓库名称1",LastOrders:1},
     {WHGUID:"仓库2GUID",WHName:"仓库名称2",LastOrders:2},
     {WHGUID:"仓库3GUID",WHName:"仓库名称3",LastOrders:3}
    ]
}
释义
LastOrders=0是商品所在的末级仓库
LastOrders=3向LastOrders=2的仓库补货，LastOrders=2向LastOrders=1的仓库补货
     */
    @ApiModelProperty("仓库补货级次")
    private String replenishmentOrders;

    /**
     * 安全库存
     */
    @ApiModelProperty("安全库存")
    private BigDecimal safetyStock;

    /**
     * 状态
Y 有效；N 无效
     */
    @ApiModelProperty("状态")
    private String state;

    /**
     * 最后缺货时间
     */
    @ApiModelProperty("最后缺货时间")
    private Date lastUnavailableTime;

    /**
     * 计划销量备货系数
     */
    @ApiModelProperty("计划销量备货系数")
    private BigDecimal planStockupRatio;

    /**
     * 到货准备天数
     */
    @ApiModelProperty("到货准备天数")
    private Integer arrivalReadyDays;

    /**
     * 来源
[
    {
        "Platform": "平台(01.医链云 02.药链云 03.四方云)",
        "PlatformType": "1.共有云 2.私有云",
        "ServiceObjects": "平台服务对象（传世、医药、七曜、新里程、哈药）",
        "DataSources": "WMS、TMS、SAP",
        "Guid": "平台唯一码"
    }
]
     */
    @ApiModelProperty("来源")
    private String applySource;

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
