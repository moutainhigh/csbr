
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
public class PCustomerTypeSum {
    
    /** CreateTime */
    private Timestamp createTime;
        
    /** Id */
    private Long id;
        
    /** 流通企业 */
    private Long totleDelivery;
        
    /** 医疗机构 */
    private Long totleMedIns;
        
    /** 生产企业 */
    private Long totlePro;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
}