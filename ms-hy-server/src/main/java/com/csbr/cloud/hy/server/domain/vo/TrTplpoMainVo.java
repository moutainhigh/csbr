package com.csbr.cloud.hy.server.domain.vo;

import com.csbr.cloud.hy.server.entity.TrBbTracingEntity;
import com.csbr.cloud.hy.server.entity.TrTplbbDetailEntity;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/5/7 14:48
 */
@Data
public class TrTplpoMainVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mainPoGuid;
    /**
     * 所属租户GUID
     */
    private String tenantGuid;

    /**
     * 所属公司GUID
     */
    private String corpGuid;

    /**
     * 物流单号
     */
    private String tplpoNo;

    /**
     * 状态
     */
    private String mainState;

    /**
     * 承运商GUID
     */
    private String deliverGuid;

    /**
     * 承运商名称
     */
    private String deliverName;

    /**
     * 仓库GUID
     */
    private String warehouseGuid;

    /**
     * 仓库名称
     */
    private String warehouseName;

    /**
     * 配送路线GUID
     */
    private String routeGuid;

    /**
     * 配送路线名称
     */
    private String routeName;

    /**
     * 起运地省份
     */
    private String departureProvince;

    /**
     * 起运地城市
     */
    private String departureCity;

    /**
     * 起运地区/县/乡
     */
    private String departureDistrict;

    /**
     * 起运地
     */
    private String departureVenue;

    /**
     * 起运地地点
     */
    private String departureAddress;

    /**
     * 目的地省份
     */
    private String arrivalProvince;

    /**
     * 目的地城市
     */
    private String arrivalCity;

    /**
     * 目的地区/县/乡
     */
    private String arrivalDistrict;

    /**
     * 目的地
     */
    private String arrivalVenue;

    /**
     * 目的地地点
     */
    private String arrivalAddress;

    /**
     * 送货联系人
     */
    private String sendContacts;

    /**
     * 送货联系电话
     */
    private String sendContactsTel;

    /**
     * 要求提货时间
     */
    private Date collectiontime;

    /**
     * 实际提货时间
     */
    private Date realityCollectiontime;

    /**
     * 提货联系人
     */
    private String pickContacts;

    /**
     * 提货联系电话
     */
    private String pickContactsTel;

    /**
     * 要求起运日期
     */
    private Date departureDate;

    /**
     * 要求到达日期 仅日期
     */
    private Date arrivalArrivedDate;

    /**
     * 最晚要求到货日期 仅日期
     */
    private Date lastArrivalArrivedDate;

    /**
     * 实际到达日期 仅日期
     */
    private Date realityArrivalArrivedDate;

    /**
     * 运输方式
     （1 汽运；2 航空），默认1 汽运
     */
    private String transportWay;

    /**
     * 下发车型
     1.整车，2.零担，3。拼车
     */
    private String issuedVehicleType;

    /**
     * 是否冷链单据
     Y 冷藏;N 否;C 冷冻
     */
    private String isCcbb;

    /**
     * 制单日期
     */
    private Date inputDate;

    /**
     * 订单件数
     单位：件
     */
    private BigDecimal ordersPkgs;

    /**
     * 订单重量
     单位：KG
     */
    private BigDecimal ordersWeight;

    /**
     * 订单体积
     单位：立方米
     */
    private BigDecimal ordersVolumn;

    /**
     * 制单人
     */
    private String operatorName;

    /**
     * 结算类型（1 合同物流；2 临时物流; 3 代理物流），默认1合同物流
     */
    private String settleType;

    /**
     * 承运金额
     */
    private BigDecimal carrierAmount;

    /**
     * 提货方式
     1.自提，2.配送
     */
    private String deliveryWay;

    /**
     * 运输时限
     */
    private Integer timeLimit;

    /**
     * 外形
     1 普通； 2 特殊
     */
    private String shape;

    /**
     * 紧急标志
     Y 紧急；N 普通
     */
    private String urgentFlag;

    /**
     * 待发区
     */
    private String waitShippingArea;

    /**
     * 回单标识 Y.已回单 N.未回单
     */
    private String backBillFlag;

    /**
     * bb表guid和明细表guid一样
     */
    private String bbGuid;

    /**
     * 业务单号
     */
    private String bBillNo;

    /**
     * 业务单据唯一编号
     */
    private String bBillUid;

    /**
     * 单据类型
     1：入库订单；2：出库订单；3：生产入库:4：调拨订单；
     */
    private String billType;

    /**
     * 业务单据类型
     10 采购入库；11 购进退；20 销售出库；21 购进退回；23 残损报废；24 调拨出库
     */
    private String businessType;

    /**
     * 所属货主GUID
     */
    private String cargoOwnerGuid;

    /**
     * 所属货主名称
     */
    private String cargoOwnerName;

    /**
     * 供应单位GUID
     */
    private String supplyCorpGuid;

    /**
     * 供应单位名称
     */
    private String supplyCorpName;

    /**
     * 销售单位GUID
     */
    private String saleCorpGuid;

    /**
     * 销售单位名称
     */
    private String saleCorpName;

    /**
     * 业务员GUID
     */
    private String buyerGuid;

    /**
     * 业务员名称
     */
    private String buyerName;

    /**
     * 订单日期
     */
    private Date billDate;

    /**
     * 明细表
     */
    private List<TrTplbbDetailEntity> trTplbbDetail;

    /**
     * 物流执行跟踪表
     */
    private List<TrBbTracingEntity> trBbTracing;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

}
