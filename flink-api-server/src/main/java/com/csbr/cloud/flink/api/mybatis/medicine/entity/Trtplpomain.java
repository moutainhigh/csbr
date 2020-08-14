
package com.csbr.cloud.flink.api.mybatis.medicine.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 物流订单主实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class Trtplpomain {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 所属租户GUID */
    private String tenantGuid;
        
    /** 所属租户名称 */
    private String tenantName;
        
    /** 所属公司GUID */
    private String corpGuid;
        
    /** 所属公司名称 */
    private String corpName;
        
    /** 物流订单号 */
    private String tPLPoNo;
        
    /** 订单类型(1：物流原始订单；2:调度合并后物流订单) */
    private String tPLPoType;
        
    /** 订单状态 */
    private String state;
        
    /** 货主GUID */
    private String cargoOwnerGuid;
        
    /** 货主名称 */
    private String cargoOwnerName;
        
    /** 承运商GUID */
    private String deliverGuid;
        
    /** 承运商名称 */
    private String deliverName;
        
    /** 仓储商GUID */
    private String tPWareHouseCorpGuid;
        
    /** 仓储商名称 */
    private String tPWareHouseCorpName;
        
    /** 物流中心GUID */
    private String logCenterGuid;
        
    /** 物流中心名称 */
    private String logCenterName;
        
    /** 仓库GUID */
    private String warehouseGuid;
        
    /** 仓库名称 */
    private String warehouseName;
        
    /** 起运地省份 */
    private String departureProvince;
        
    /** 起运地城市 */
    private String departureCity;
        
    /** 起运地区/县/乡 */
    private String departureDistrict;
        
    /** 起运地地点 */
    private String departureAddress;
        
    /** 提货联系人 */
    private String pickContacts;
        
    /** 提货联系电话 */
    private String pickContactsTel;
        
    /** 提货时间 */
    private Timestamp pickTime;
        
    /** 目的地省份 */
    private String arrivalProvince;
        
    /** 目的地城市 */
    private String arrivalCity;
        
    /** 目的地区/县/乡 */
    private String arrivalDistrict;
        
    /** 目的地地点 */
    private String arrivalAddress;
        
    /** 中转地省份 */
    private String transitProvince;
        
    /** 中转地城市 */
    private String transitCity;
        
    /** 中转地区/县/乡 */
    private String transitDistrict;
        
    /** 中转地 */
    private String transitVenue;
        
    /** 送货联系人 */
    private String sendContacts;
        
    /** 送货联系电话 */
    private String sendContactsTel;
        
    /** 要求起运日期 */
    private Date departureDate;
        
    /** 要求起运时间 */
    private String departureTime;
        
    /** 要求到达日期(仅日期) */
    private Date arrivalArrivedDate;
        
    /** 要求到达时间(仅时间) */
    private String arrivalArrivedTime;
        
    /** 最晚要求到货日期(仅日期) */
    private Timestamp lastArrivalArrivedDate;
        
    /** ArrivalTerm */
    private Double arrivalTerm;
        
    /** 运输方式(Y 是(平台运输)；N (不负责运输)；Z(货主自行运输)) */
    private String isCollection;
        
    /** 提货时间 */
    private Timestamp collectionTime;
        
    /** 提货目的地公司名称 */
    private String collectionArrivalCorp;
        
    /** 订单要求 */
    private String orderRequirement;
        
    /** 装载要求 */
    private String loadRequirement;
        
    /** 冷链设备要求 */
    private String equipRequirement;
        
    /** 是否回收设备 */
    private String isRecycle;
        
    /** 设备回收类别 */
    private String recycleKind;
        
    /** 车种要求 */
    private String vehicleKind;
        
    /** 订单件数 */
    private Double ordersPKGS;
        
    /** 订单重量 */
    private Double ordersWeight;
        
    /** 订单体积 */
    private Double ordersVolumn;
        
    /** 下发车型(1：整车；2：零担；3：拼车；) */
    private String issuedVehicleType;
        
    /** 下发日期 */
    private Timestamp dateIssued;
        
    /** 是否冷链单据(Y：是；N：否；) */
    private String isCCBB;
        
    /** 物流订单日期 */
    private Timestamp billDate;
        
    /** 业务单据数量 */
    private Integer bBillNum;
        
    /** 提货交接人 */
    private String consignee;
        
    /** 提货交接时间 */
    private Timestamp consignTime;
        
    /** 送货签收人 */
    private String receiver;
        
    /** 送货签收时间 */
    private Timestamp receiveTime;
        
    /** 制单日期 */
    private Timestamp inputDate;
        
    /** 运输时限 */
    private Integer timeLimit;
        
    /** 订单完成时间 */
    private Timestamp completeTime;
        
    /** 回单标志 Y.已回单 N.未回单 C.已确认 */
    private String backBillFlag;
        
    /** 回单时间 */
    private Timestamp backBillTime;
        
    /** 回单人 */
    private String backBillName;
        
    /** 返单标识 Y.已返单 N.未返单 */
    private String returnBillFlag;
        
    /** 返单时间 */
    private Timestamp retrunBillTime;
        
    /** 返单信息 */
    private String retrunInfo;
        
    /** 备注 */
    private String memo;
        
    /** 集散仓GUID */
    private String distributingBinGuid;
        
    /** 集散仓名称 */
    private String distributingBinName;
        
    /** 申报金额(申报物流订单的价值) */
    private Double declaredAmount;
        
    /** 收货公司GUID */
    private String recCorpGuid;
        
    /** 收货公司名称 */
    private String recCorpName;
        
    /** 冷链设备分类(1.一体式保温箱；2.分解式保温箱；3.冷藏箱；4冷藏车) */
    private String coldEquipmentCategory;
        
    /** 温度要求(1 普通；2 冷藏；3 冷冻) */
    private String temperatureRequire;
        
    /** 运输要求 */
    private String transportRequire;
        
    /** 物流服务类型(1 仓储；2 配送；3 仓配一体) */
    private String logisticsServiceType;
        
    /** 备用字符1 */
    private String varchar1;
        
    /** 备用字符2 */
    private String varchar2;
        
    /** 备用字符3 */
    private String varchar3;
        
    /** Num1 */
    private Double num1;
        
    /** Num2 */
    private Double num2;
        
    /** Num3 */
    private Double num3;
        
    /** 备用日期1 */
    private Timestamp date1;
        
    /** 备用日期2 */
    private Timestamp date2;
        
    /** 备用日期3 */
    private Timestamp date3;
        
    /** 创建人ID */
    private String createUserId;
        
    /** 创建人姓名 */
    private String createUserName;
        
    /** 修改人ID */
    private String modifyUserId;
        
    /** 修改人姓名 */
    private String modifyUserName;
        
    /** 数据版本号 */
    private String dataVersion;
        
    /** 是否删除 */
    private Boolean isDeleted;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** 起运地存放省市区的文本 */
    private String departureVenue;
        
    /** 目的地存放省市区的文本 */
    private String arrivalVenue;
        
    /** 是否生成对账单 */
    private String statementFlag;
        
    /** 修改人 */
    private String updateUserID;
        
    /** 修改姓名 */
    private String updateUserName;
        
    /** 配送路线GUID */
    private String routeGuid;
        
    /** 配送路线名称 */
    private String routeName;
        
    /** 总订单数 */
    private Integer billTotalNum;
        
    /** 送货地址数 */
    private Integer addressNum;
        
    /** 客户数 */
    private Integer customerNum;
        
    /** 品规数 */
    private Integer goodsSpecNum;
        
    /** 承运费用 */
    private Double carrierAmount;
        
    /** 是否抢单0.未抢单，1.抢单 */
    private String robFlag;
        
    /** 据单承运商JSON串 */
    private String rejectCarriers;
        
    /** 提货方式 1.自提，2.配送 */
    private String deliveryWay;
        
    /** 运输方式 1.汽配，2.航空 */
    private String transportWay;
        
    /** 主提货单号 */
    private String mainDeliveryNo;
        
    /** 航班编号 */
    private String flightNo;
        
    /** 航班信息GUID */
    private String flightInfoGuid;
        
    /** 航班信息名称 */
    private String flightInfoName;
        
    /** 物控标识 0.非物控，1.物控 */
    private String propertyCtrlFlag;
        
    /** 物控更新人 */
    private String pCtrlUpdateName;
        
    /** 物控更新时间 */
    private Timestamp pCtrlUpdateTime;
        
    /** 原件签收(Y 是 N 否) */
    private String originalSign;
        
    /** 温度计编号 */
    private String thermometerCode;
        
    /** 回单提醒时间 */
    private Timestamp planBackBillTime;
        
    /** 代收货款 */
    private Double collectionAmount;
        
    /** 付款方式(1 现结；2 月结) */
    private String payMode;
        
    /** 是否入集散仓 */
    private String isDistributingBin;
        
    /** 物流区域GUID(仓储辐射区域) */
    private String logAreaGuid;
        
    /** 物流区域名称（仓储辐射区域） */
    private String logAreaName;
        
    /** 预计配送区域GUID(预计物流配送区域) */
    private String planTransAreaGuid;
        
    /** 预计配送区域名称（预计物流配送区域） */
    private String planTransAreaName;
        
    /** 配送区域名称 */
    private String transAreaName;
        
    /** 实际配送区域GUID */
    private String resultTransAreaGuid;
        
    /** 实际配送区域名称 */
    private String resultTransAreaName;
        
    /** 集单异常信息 */
    private String combinLogInfo;
        
    /** 送货上门(Y 是；N 否) */
    private String deliveredToDoor;
        
    /** 外形(1 普通；2 特殊) */
    private String shape;
        
    /** 附属信息自定义字段 */
    private String customField;
        
    /** 专车标志(Y 是;N 否) */
    private String specialCarFlag;
        
    /** 计费方式(1 按件数；2 按重量；3 按体积；S 按专车) */
    private String valuationMethod;
        
    /** 主单GUID */
    private String mainOrderGuid;
        
    /** 主单名称 */
    private String mainOrderName;
        
    /** 结算类型(1 合同物流；2 临时物流; 3 代理物流) */
    private String settleType;
        
    /** 是否拆单(Y 是；N 否) */
    private String splitOrderFlag;
        
    /** 是否合单(Y 是；N 否) */
    private String combineOrderFlag;
        
    /** 物流运单号 */
    private String tracingNos;
        
    /** 紧急标志 */
    private String urgentFlag;
        
}