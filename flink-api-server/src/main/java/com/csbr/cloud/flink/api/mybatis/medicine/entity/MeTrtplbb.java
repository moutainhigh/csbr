
package com.csbr.cloud.flink.api.mybatis.medicine.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 物流业务单据汇总实体.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Data
public class MeTrtplbb {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 物流订单主表GUID(与物流订单主表的GUID一致) */
    private String mainPoGuid;
        
    /** 物流订单主表名称(与物流订单主表的名称一致) */
    private String mainPoName;
        
    /** 所属租户GUID */
    private String tenantGuid;
        
    /** 所属租户名称 */
    private String tenantName;
        
    /** 所属公司GUID */
    private String corpGuid;
        
    /** 所属公司名称 */
    private String corpName;
        
    /** 货主GUID */
    private String cargoOwnerGuid;
        
    /** 货主名称 */
    private String cargoOwnerName;
        
    /** 业务单据编号 */
    private String bBillNo;
        
    /** 业务单据唯一编号(业务系统中能表示业务单据唯一性的编号) */
    private String bBillUID;
        
    /** 单据类型(B01：入库订单；B03：出库订单；B09：调拨订单；B04：高值送货单 B05：高值还货单) */
    private String billType;
        
    /** 是否自动处理订单(Y 是;N 否) */
    private String isAuto;
        
    /** 是否冷链单据(Y 是;N 否) */
    private String isCCBB;
        
    /** 是否越库订单(Y 是;N 否) */
    private String isCrossDoscking;
        
    /** 供应单位GUID */
    private String supplyCorpGuid;
        
    /** 供应单位名称 */
    private String supplyCorpName;
        
    /** 销售单位GUID */
    private String saleCorpGuid;
        
    /** 销售单位名称 */
    private String saleCorpName;
        
    /** 业务员GUID */
    private String buyerGuid;
        
    /** 业务员名称 */
    private String buyerName;
        
    /** 调出物流中心GUID(仅调拨使用) */
    private String transferOutDCGuid;
        
    /** 调出物流中心名称(仅调拨使用) */
    private String transferOutDCName;
        
    /** 调出仓库GUID(仅调拨使用) */
    private String transferOutWHGuid;
        
    /** 调出仓库名称(仅调拨使用) */
    private String transferOutWHName;
        
    /** 调入物流中心GUID(仅调拨使用) */
    private String transferInDCGuid;
        
    /** 调入物流中心名称(仅调拨使用) */
    private String transferInDCName;
        
    /** 调入仓库GUID(仅调拨使用) */
    private String transferInWHGuid;
        
    /** 调入仓库名称(仅调拨使用) */
    private String transferInWHName;
        
    /** 订货日期日期 */
    private Timestamp billDate;
        
    /** 计划发货日期,出库日期 */
    private Timestamp preDeliverDate;
        
    /** 计划到货日期 */
    private Timestamp preArrivalDate;
        
    /** 收货地址 */
    private String address;
        
    /** 总件数 */
    private Double pkgs;
        
    /** 总体积 */
    private Double volume;
        
    /** 总重量 */
    private Double weight;
        
    /** 明细条数 */
    private Integer countSum;
        
    /** 明细金额 */
    private Double detailSum;
        
    /** 制单日期 */
    private Timestamp inputDate;
        
    /** 签收件数(单位：件) */
    private Double recPKGS;
        
    /** 物流调度合单Guid */
    private String combinMainPoGuid;
        
    /** 物流调度合单名称 */
    private String combinMainPoName;
        
    /** 上传标志(Y:上传 N:未上传) */
    private String reportFlag;
        
    /** 上传时间 */
    private Timestamp reportTime;
        
    /** 原物流订单主表GUID(原租户的物流订单主表的GUID) */
    private String oldMainPoGuid;
        
    /** 原物流订单主表名称（原租户的物流订单主表的名称） */
    private String oldMainPoName;
        
    /** 备注 */
    private String memo;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** 创建人 */
    private String createUserID;
        
    /** 创建人姓名 */
    private String createUserName;
        
    /** 修改人 */
    private String updateUserID;
        
    /** 修改姓名 */
    private String updateUserName;
        
    /** 行数据版本 */
    private String dataVersion;
        
    /** 是否删除 */
    private String isDeleted;
        
    /** 总品规数 */
    private Integer goodsSpecNum;
        
    /** 评价总分 */
    private Double evaluationTotal;
        
    /** 仓储作业状态 31接单，R34出库结束，W30设备绑定，W50提货交接,I60入库受理 */
    private String whWorkState;
        
    /** 仓储作业开始时间 */
    private Timestamp whWorkStartTime;
        
    /** 采购订单GUID */
    private String poGuid;
        
    /** 采购订单名称 */
    private String poName;
        
    /** 采购订单编号 */
    private String poBillNo;
        
    /** 原单据编号(销售退回时，销售订单编号) */
    private String oldBillNo;
        
    /** 订货科室名称 */
    private String medDeptName;
        
    /** 订货库房名称 */
    private String medStoreName;
        
    /** 业务类型 01:普耗;02:跟台高值;03:备货高值;04:药品;05:后勤物资;06:消毒用品;07:洗涤用品;08:中药材;09:设施设备;30:高值送货;31:高值还货 */
    private String businessType;
        
    /** 1.商业客户 2.医疗机构 */
    private String origin;
        
    /** 调拨单产生来源(仅调拨使用1 补货产生；2 调拨产生) */
    private String requisitionOrigin;
        
    /** 单据产生方式(1 自动产生；2 手工录入 */
    private String requisitionMode;
        
    /** 是否还货 */
    private String isReturn;
        
    /** 是否同步电票 */
    private String isSyncInvoice;
        
    /** 开票状态(N:未开票；P:部分开票；A:全部开票) */
    private String invoiceState;
        
    /** 发票GUID */
    private String invoiceGuids;
        
    /** 是否紧急(Y 紧急；N 非紧急) */
    private String isUrgent;
        
    /** 支付状态(1 已支付；2 未支付) */
    private String payStatus;
        
    /** 仓库调度批次号 */
    private String schedulingBatchNo;
        
    /** 仓库调度状态 */
    private String whDispatcherState;
        
    /** 代发区 */
    private String waitShippingArea;
        
    /** 医药订单(Y 是；N 否) */
    private String isMedOrder;
        
    /** 物流订单主表GUDI */
    private String newMainPoGuid;
        
    /** 物流订单主表gudi */
    private String newMainPoName;
        
}