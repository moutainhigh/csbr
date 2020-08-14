
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
public class PStorageDeliveryRegionSum {
    
    /** 市 */
    private String city;
        
    /** 区 */
    private String district;
        
    /** Id */
    private Long id;
        
    /** 省 */
    private String province;
        
    /** 三方仓库面积 */
    private Double threeArea;
        
    /** 三方仓库总数量 */
    private Long threeGspCount;
        
    /** 仓库面积 */
    private Double totleArea;
        
    /** 平台运力 */
    private Long totleDelivery;
        
    /** GSP仓库总数量 */
    private Long totleGsp;
        
}