
package com.csbr.cloud.flink.api.mybatis.transport.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 配送路线资料实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class TrMftransroute {
    
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
        
    /** 所属物流中心GUID */
    private String logCenterGuid;
        
    /** 所属物流中心名称 */
    private String logCenterName;
        
    /** 所属承运商GUID */
    private String carrierGuid;
        
    /** 所属承运商名称 */
    private String carrierName;
        
    /** State */
    private String state;
        
    /** 路线编号 */
    private String routeCode;
        
    /** 路线名称 */
    private String routeName;
        
    /** 起运地 */
    private String dispatch;
        
    /** 起始地 */
    private String dispatchVenue;
        
    /** 目的地 */
    private String destination;
        
    /** 目的地 */
    private String destinationVenue;
        
    /** 途经地({ViaPlace:[{Place:北京,Cost:80,Method:件},{Place:石家庄,Cost:85,Method:件},{Place:郑州,Cost:95,Method:件} ] } 释义: Place:地点;Cost:费用;Method:收费方式(件/重量/体积/面积)) */
    private String viaPlace;
        
    /** 备用字符1 */
    private String varchar1;
        
    /** 备用字符2 */
    private String varchar2;
        
    /** 备用字符3 */
    private String varchar3;
        
    /** 备用字符4 */
    private String varchar4;
        
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
        
    /** UpdateUserId */
    private String updateUserId;
        
    /** UpdateUserName */
    private String updateUserName;
        
    /** 数据版本号 */
    private String dataVersion;
        
    /** IsDeleted */
    private String isDeleted;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** 运输方式(1 陆运；2 空运) */
    private String transportMode;
        
    /** 航空公司名称 */
    private String airLineName;
        
    /** 航空公司GUID */
    private String airLineGuid;
        
    /** 物流区域GUID */
    private String logAreaGuid;
        
    /** 物流区域名称 */
    private String logAreaName;
        
    /** 在途天数 */
    private Integer transitDays;
        
    /** TransitAddress */
    private String transitAddress;
        
    /** DataSources */
    private String dataSources;
        
    /** NodePoints */
    private Integer nodePoints;
        
    /** 1 干线，2 城际，3 同城 */
    private String routeType;
        
    /** 自动调度(Y 是；N 否) */
    private String autoDispatch;
        
    /** 截单日期 */
    private Timestamp deadline;
        
    /** 备注 */
    private String memo;
        
    /** 司机Guid */
    private String staffGuid;
        
    /** 司机名称 */
    private String staffName;
        
    /** 车辆Guid */
    private String vehicleGuid;
        
    /** 车辆名称 */
    private String vehicleName;
        
}