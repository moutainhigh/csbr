
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
public class CsbrSumInfo {
    
    /** 冷库 */
    private Double chillArea;
        
    /** CreateTime */
    private Timestamp createTime;
        
    /** Id */
    private Long id;
        
    /** 阴凉库 */
    private Double shadeArea;
        
    /** 三方库 */
    private Double threeArea;
        
    /** 总面积 */
    private Double totleArea;
        
    /** 平台签约GSP仓 */
    private Long totleGsp;
        
    /** UpdateTime */
    private Timestamp updateTime;
        
}