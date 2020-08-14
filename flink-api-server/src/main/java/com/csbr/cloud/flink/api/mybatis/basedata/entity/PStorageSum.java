
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
public class PStorageSum {
    
    /** 冷库体积 */
    private Double chillArea;
        
    /** 三方仓辐射市 */
    private Long cityCount;
        
    /** Id */
    private Long id;
        
    /** 三方仓辐射省 */
    private Long proCount;
        
    /** 阴凉库面积 */
    private Double shadeArea;
        
    /** 三方仓库面积 */
    private Double threeArea;
        
    /** 三方仓库总数量 */
    private Long threeGspCount;
        
    /** 仓库面积 */
    private Double totleArea;
        
    /** GSP仓库总数量 */
    private Long totleGsp;
        
}