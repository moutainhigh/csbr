package com.csbr.cloud.hy.server.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.hy.server.entity.TrTplbbDetailEntity;
import com.csbr.cloud.hy.server.entity.TrTplbbEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/4/29 11:05
 */
@Data
@ApiModel(value = "物流订单信息实体")
public class TrTplpoMainDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @TableId(value = "guid", type = IdType.AUTO)
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
     * 物流单号
     */
    @ApiModelProperty("物流单号")
    private String tplpoNo;

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private String state;

    /**
     * 承运商GUID
     */
    @ApiModelProperty("承运商GUID")
    private String deliverGuid;

    /**
     * 承运商名称
     */
    @ApiModelProperty("承运商名称")
    private String deliverName;

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
     * 配送路线GUID
     */
    @ApiModelProperty("配送路线GUID")
    private String routeGuid;

    /**
     * 配送路线名称
     */
    @ApiModelProperty("配送路线名称")
    private String routeName;

    /**
     * 起运地省份
     */
    @ApiModelProperty("起运地省份")
    private String departureProvince;

    /**
     * 起运地城市
     */
    @ApiModelProperty("起运地城市")
    private String departureCity;

    /**
     * 起运地区/县/乡
     */
    @ApiModelProperty("起运地区/县/乡")
    private String departureDistrict;

    /**
     * 起运地
     */
    @ApiModelProperty("起运地")
    private String departureVenue;

    /**
     * 起运地地点
     */
    @ApiModelProperty("起运地地点")
    private String departureAddress;

    /**
     * 目的地省份
     */
    @ApiModelProperty("目的地省份")
    private String arrivalProvince;

    /**
     * 目的地城市
     */
    @ApiModelProperty("目的地城市")
    private String arrivalCity;

    /**
     * 目的地区/县/乡
     */
    @ApiModelProperty("目的地区/县/乡")
    private String arrivalDistrict;

    /**
     * 目的地
     */
    @ApiModelProperty("目的地")
    private String arrivalVenue;

    /**
     * 目的地地点
     */
    @ApiModelProperty("目的地地点")
    private String arrivalAddress;

    /**
     * 送货联系人
     */
    @ApiModelProperty("送货联系人")
    private String sendContacts;

    /**
     * 送货联系电话
     */
    @ApiModelProperty("送货联系电话")
    private String sendContactsTel;

    /**
     * 要求提货时间
     */
    @ApiModelProperty("要求提货时间")
    private Date collectiontime;

    /**
     * 实际提货时间
     */
    @ApiModelProperty("实际提货时间")
    private Date realityCollectiontime;

    /**
     * 提货联系人
     */
    @ApiModelProperty("提货联系人")
    private String pickContacts;

    /**
     * 提货联系电话
     */
    @ApiModelProperty("提货联系电话")
    private String pickContactsTel;

    /**
     * 要求起运日期
     */
    @ApiModelProperty("要求起运日期")
    private Date departureDate;

    /**
     * 要求到达日期 仅日期
     */
    @ApiModelProperty("要求到达日期 仅日期")
    private Date arrivalArrivedDate;

    /**
     * 最晚要求到货日期 仅日期
     */
    @ApiModelProperty("最晚要求到货日期 仅日期")
    private Date lastArrivalArrivedDate;

    /**
     * 实际到达日期 仅日期
     */
    @ApiModelProperty("实际到达日期 仅日期")
    private Date realityArrivalArrivedDate;

    /**
     * 运输方式
     （1 汽运；2 航空），默认1 汽运
     */
    @ApiModelProperty("运输方式 （1 汽运；2 航空），默认1 汽运")
    private String transportWay;

    /**
     * 下发车型
     1.整车，2.零担，3。拼车
     */
    @ApiModelProperty("下发车型 1.整车，2.零担，3。拼车")
    private String issuedVehicleType;

    /**
     * 是否冷链单据
     Y 冷藏;N 否;C 冷冻
     */
    @ApiModelProperty("是否冷链单据 Y 冷藏;N 否;C 冷冻")
    private String isCcbb;

    /**
     * 制单日期
     */
    @ApiModelProperty("制单日期")
    private Date inputDate;

    /**
     * 订单件数
     单位：件
     */
    @ApiModelProperty("订单件数 单位：件")
    private BigDecimal ordersPkgs;

    /**
     * 订单重量
     单位：KG
     */
    @ApiModelProperty("订单重量 单位：KG")
    private BigDecimal ordersWeight;

    /**
     * 订单体积
     单位：立方米
     */
    @ApiModelProperty("订单体积 单位：立方米")
    private BigDecimal ordersVolumn;

    /**
     * 制单人
     */
    @ApiModelProperty("制单人")
    private String operatorName;

    /**
     * 结算类型（1 合同物流；2 临时物流; 3 代理物流），默认1合同物流
     */
    @ApiModelProperty("结算类型（1 合同物流；2 临时物流; 3 代理物流），默认1合同物流")
    private String settleType;

    /**
     * 承运金额
     */
    @ApiModelProperty("承运金额")
    private BigDecimal carrierAmount;

    /**
     * 提货方式
     1.自提，2.配送
     */
    @ApiModelProperty("提货方式 1.自提，2.配送")
    private String deliveryWay;

    /**
     * 运输时限
     */
    @ApiModelProperty("运输时限")
    private Integer timeLimit;

    /**
     * 外形
     1 普通； 2 特殊
     */
    @ApiModelProperty("外形 1 普通； 2 特殊")
    private String shape;

    /**
     * 紧急标志
     Y 紧急；N 普通
     */
    @ApiModelProperty("紧急标志 Y 紧急；N 普通")
    private String urgentFlag;

    /**
     * 待发区
     */
    @ApiModelProperty("待发区")
    private String waitShippingArea;

    /**
     * 回单标识 Y.已回单 N.未回单
     */
    @ApiModelProperty("回单标识 Y.已回单 N.未回单")
    private String backBillFlag;

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

    /**
     *物流业务单据汇总表
     */
    private List<TrTplbbEntity> trTplbb;

    /**
     * 物流业务单据明细表
     */
    private List<TrTplbbDetailEntity> trTplbbDetail;
}
