
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
public class PTransrouteSum {
    
    /** 市 */
    private String city;
        
    /** Id */
    private Long id;
        
    /** 省 */
    private String province;
        
}