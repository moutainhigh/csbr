
package com.csbr.cloud.flink.api.mybatis.hospital.entity;

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
public class Mfmasterrelation {
    
    /** 系统唯一标识 */
    private String guid;
        
    /** 企业GUID */
    private String enterpriseGuid;
        
    /** 企业名称 */
    private String enterpriseName;
        
    /** 关系类型(1 供应商;2 货主;3 承运商;4 客户) */
    private String relationType;
        
    /** 关系企业GUID */
    private String relationEntGuid;
        
    /** 关系企业名称 */
    private String relationEntName;
        
    /** 关系建立日期 */
    private Timestamp relationCreateTime;
        
    /** 状态(Y 有效；S 停用；E 作废) */
    private String state;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
}