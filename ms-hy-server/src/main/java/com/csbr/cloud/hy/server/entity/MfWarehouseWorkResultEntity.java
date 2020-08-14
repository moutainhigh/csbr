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
 * 仓库作业历史表
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mf_warehouse_work_result")
@ApiModel(value = "仓库作业历史实体")
public class MfWarehouseWorkResultEntity implements Serializable {

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
     * 业务单据GUID
     */
    @ApiModelProperty("业务单据GUID")
    private String bBillGuid;

    /**
     * 业务单据编号
     */
    @ApiModelProperty("业务单据编号")
    private String bBillNo;

    /**
     * 业务单明细行号
     */
    @ApiModelProperty("业务单明细行号")
    private Integer bBillRowNo;

    /**
     * 业务单据类型: 10 采购入库；11 购进退；20 销售出库；21 购进退回；23 残损报废；24 调拨出库库
     */
    @ApiModelProperty("业务单据类型: 10 采购入库；11 购进退；20 销售出库；21 购进退回；23 残损报废；24 调拨出库库")
    private String businessType;

    /**
     * 作业状态 
S31 入库收货
S32 入库验收
S33 入库完成
R31 波次配货
R32 出库拣货
R33 出库内复核
R34 出库外复核
     */
    @ApiModelProperty("作业状态")
    private String workState;

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
     * 数量
     */
    @ApiModelProperty("数量")
    private BigDecimal qty;

    /**
     * 作业情况
     */
    @ApiModelProperty("作业情况")
    private String situation;

    /**
     * 作业结论
     */
    @ApiModelProperty("作业结论")
    private String conclusion;

    /**
     * 操作员1
     */
    @ApiModelProperty("操作员1")
    private String operater1;

    /**
     * 操作时间1
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("操作时间1")
    private Date operateDate1;

    /**
     * 操作员2
     */
    @ApiModelProperty("操作员2")
    private String operater2;

    /**
     * 操作时间2
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty("操作时间2")
    private Date operateDate2;

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
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;


}
