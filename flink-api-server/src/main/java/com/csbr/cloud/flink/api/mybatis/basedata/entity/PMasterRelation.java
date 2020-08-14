
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
public class PMasterRelation {
    
    /** 企业GUID */
    private String enterpriseGGuid;
        
    /** 企业名称 */
    private String enterpriseGName;
        
    /** Guid */
    private String guid;
        
    /** Id */
    private Long id;
        
    /** 关系建立日期 */
    private String relationCreateTime;
        
    /** 关系企业GUID */
    private String relationEntGuGuid;
        
    /** 关系企业名称 */
    private String relationEntGuName;
        
    /** 关系类型 */
    private String relationType;
        
    /** 状态 */
    private String state;
        
}