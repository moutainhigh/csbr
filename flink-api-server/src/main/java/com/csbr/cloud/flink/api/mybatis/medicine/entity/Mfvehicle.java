
package com.csbr.cloud.flink.api.mybatis.medicine.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 运输车辆资料实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class Mfvehicle {
    
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
        
    /** 营运状态(1 正常；2 在途；3 停运；4 报废; U 占用: 6 维修) */
    private String bizState;
        
    /** 车型GUID */
    private String vehicleTypeGuid;
        
    /** 车型名称 */
    private String vehicleTypeName;
        
    /** 车型(如：五十铃、依维柯等) */
    private String vehicleType;
        
    /** 车牌号 */
    private String plateNumber;
        
    /** 车辆性质(1 自有车辆；2 外租车辆) */
    private String vehicleNature;
        
    /** 是否对外承运(Y 可对外承运;N 仅自用) */
    private String isExternalCarrier;
        
    /** 车辆图片 */
    private String pictContent;
        
    /** 有无通行证(Y:有；N:无) */
    private String passportFlag;
        
    /** 行驶证号 */
    private String drivingLicNo;
        
    /** 注册日期 */
    private Date registerDate;
        
    /** 提报接收方GUID */
    private String receivingPartyGuid;
        
    /** 提报接收方名称 */
    private String receivingPartyName;
        
    /** 提报方GUID */
    private String reportingPartyGuid;
        
    /** 提报方名称 */
    private String reportingPartyName;
        
    /** 审核人GUID */
    private String auditorGuid;
        
    /** 审核人名称 */
    private String auditorName;
        
    /** 审核人 */
    private String auditor;
        
    /** 审核结果(N:未审核 Y:审核通过 R:驳回) */
    private String auditState;
        
    /** 审核意见 */
    private String auditSuggest;
        
    /** 审核时间 */
    private Timestamp auditTime;
        
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
        
    /** 所属机构 */
    private String organization;
        
    /** 登记日期 */
    private Date registrationDate;
        
    /** 登记机构 */
    private String institutions;
        
    /** 品牌 */
    private String brandName;
        
    /** 底盘型号 */
    private String chassisType;
        
    /** 备注 */
    private String mero;
        
}