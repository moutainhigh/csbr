
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
public class CusStorageSum {
    
    /** CreateTime */
    private Timestamp createTime;
        
    /** Guid */
    private String guid;
        
    /** Id */
    private Long id;
        
    /** 三方仓库面积 */
    private Double threeArea;
        
    /** 三方仓库总数量 */
    private Long threeGspCount;
        
    /** 仓库面积 */
    private Double totleArea;
        
    /** GSP仓库总数量 */
    private Long totleGsp;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
}