
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
public class PPurchaseGoodsSum {
    
    /** 月 */
    private String goodsMonth;
        
    /** 年 */
    private String goodsYear;
        
    /** Id */
    private Long id;
        
    /** 采购金额 */
    private Double totlePurchaseAmount;
        
}