
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
public class PDeliverySum {
    
    /** 在线运营冷藏车辆 */
    private Long chillCar;
        
    /** 冷藏车总数 */
    private Long chillCarSum;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** 在线物流网点数 */
    private Long dotNumber;
        
    /** 物流网点总数量 */
    private Long dotTotle;
        
    /** 分拨中心数量 */
    private Long fenboNumber;
        
    /** Id */
    private Long id;
        
    /** 在线运营车辆 */
    private Long totleCar;
        
    /** 物流公司总车辆 */
    private Long totleCarSum;
        
    /** 平台运力 */
    private Long totleDelivery;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
}