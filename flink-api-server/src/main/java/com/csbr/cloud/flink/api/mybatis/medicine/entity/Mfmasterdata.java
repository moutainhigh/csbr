
package com.csbr.cloud.flink.api.mybatis.medicine.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 主数据资料实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class Mfmasterdata {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 企业名称 */
    private String chineseName;
        
    /** EnterpriseType */
    private String enterpriseType;
        
    /** 企业类型 */
    private String enterpriseClass;
        
    /** 业务范围 */
    private String bizRange;
        
    /** 省 */
    private String province;
        
    /** 市 */
    private String city;
        
    /** 区 */
    private String district;
        
    /** 地址 */
    private String address;
        
    /** 注册日期 */
    private Date registeredDate;
        
    /** 经营范围 */
    private String oORange;
        
    /** Contacts */
    private String contacts;
        
    /** 联系人电话 */
    private String contactTel;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** 主数据Guid */
    private String masterGuid;
        
    /** 主数据名称 */
    private String masterName;
        
    /** MasTerGuidTemp */
    private String masTerGuidTemp;
        
    /** MedOrgType */
    private String medOrgType;
        
    /** MedOrgRank */
    private String medOrgRank;
        
    /** MedOrgGrade */
    private String medOrgGrade;
        
    /** CarSumCount */
    private Integer carSumCount;
        
    /** CarColdCount */
    private Integer carColdCount;
        
    /** OutletsCount */
    private Integer outletsCount;
        
    /** DistribeCenterCount */
    private Integer distribeCenterCount;
        
    /** CarAvailableCount */
    private Integer carAvailableCount;
        
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
        
    /** TableSource */
    private String tableSource;
        
}