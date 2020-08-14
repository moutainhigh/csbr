
package com.csbr.cloud.flink.api.mybatis.basedata.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 实体.
 *
 * @author Huanglh
 * @since 2020-01-13
 */

@Data
public class CusRelation {
    
    /** Id */
    private Long id;
        
    /** 用户GUID */
    private String guid;
        
    /** 源数据GUID */
    private String sourceGuid;
        
    /** 医链云、药链云、四方云 */
    private String sourceData;
        
}