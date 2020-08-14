
package com.csbr.cloud.flink.api.mybatis.transport.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 运输车辆类型实体.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

@Data
public class TrMfvehicletype {
    
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
        
    /** 车型(如：五十铃、依维柯等) */
    private String vehicleType;
        
    /** 车种 */
    private String vehicleKind;
        
    /** 载重(单位:吨) */
    private Double carryingCapacity;
        
    /** 车长(单位:米) */
    private Double length;
        
    /** 车宽(单位:米) */
    private Double width;
        
    /** 车高(单位:米) */
    private Double height;
        
    /** 车厢容积(单位:立方米) */
    private Double carriageVolumn;
        
    /** 车辆规格 */
    private String vehicleSpec;
        
    /** 可装件数上限 */
    private Double loadPKGNum;
        
    /** 可装件数超限比率 */
    private Double loadPkgMaxRatio;
        
    /** 运输成本(单元:元/km) */
    private Double transCost;
        
    /** 运输报价(会员针对货主的报价) */
    private Double transQuote;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
    /** 创建人ID */
    private String createUserId;
        
    /** 创建人姓名 */
    private String createUserName;
        
    /** 修改人ID */
    private String updateUserId;
        
    /** 修改人姓名 */
    private String updateUserName;
        
    /** 数据版本号 */
    private String dataVersion;
        
    /** 是否删除 */
    private String isDeleted;
        
    /** 是否初始数据(Y 是；N 否) */
    private String isInitData;
        
}