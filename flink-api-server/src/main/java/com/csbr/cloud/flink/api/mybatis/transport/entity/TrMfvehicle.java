
package com.csbr.cloud.flink.api.mybatis.transport.entity;

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
public class TrMfvehicle {
    
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
        
    /** 营运状态(1 正常；2 在途；3 停运；4 报废; U 占用) */
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
        
}