
package com.csbr.cloud.flink.api.mybatis.basedata.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 实体.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Data
public class MfCustomerInfo {
    
    /** 地址 */
    private String address;
        
    /** 名称 */
    private String chineseName;
        
    /** 城市 */
    private String city;
        
    /** 联系电话 */
    private String contactTel;
        
    /** 联系人 */
    private String contacts;
        
    /** 创建时间 */
    private Timestamp createTime;
        
    /** 区/县/乡 */
    private String district;
        
    /** 企业类型 */
    private String enterpriseClass;
        
    /** 企业类型 */
    private String enterpriseType;
        
    /** Guid */
    private String guid;
        
    /** 医疗机构等级 */
    private String medLevel;
        
    /** 省份 */
    private String province;
        
}