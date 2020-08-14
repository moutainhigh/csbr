
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
public class CsGoodsRegionSum {
    
    /** 市 */
    private String city;
        
    /** 区 */
    private String district;
        
    /** 月 */
    private String goodsMonth;
        
    /** 年 */
    private String goodsYear;
        
    /** Id */
    private Long id;
        
    /** 发货量(医药) */
    private Double medicineDeliverGoods;
        
    /** 省 */
    private String province;
        
    /** 总发货量 */
    private Double totleDeliverGoods;
        
}