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
 * 入库历史表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tr_in_bound_result")
@ApiModel(value = "入库历史实体")
public class TrInBoundResultEntity implements Serializable {

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
     * 业务单据编号
     */
    @ApiModelProperty("业务单据编号")
    private String bBillNo;

    /**
     * 业务单据类型
10 采购入库 ；11 购进退回
     */
    @ApiModelProperty("业务单据类型 10 采购入库 ；11 购进退回")
    private String businessType;

    /**
     * 业务单据唯一编号
业务系统中能表示业务单据唯一性的编号
     */
    @ApiModelProperty("业务单据唯一编号")
    private String bBillUid;

    /**
     * 业务单明细行号
业务系统中能表示业务单明细行号
     */
    @ApiModelProperty("业务单明细行号")
    private Integer bBillRowNo;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("收货日期")
    private Date receiveDate;

    /**
     * 入库完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("入库完成时间")
    private Date finishTime;

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
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


}
