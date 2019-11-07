package com.csbr.cloud.orderbusiness.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhangheng
 * @date 2019/11/6 15:14
 */
@Data
public class OrderCreateNoRule extends BaseDO {

    private String tenantGuid;

    private String corpGuid;

    private String ruleName;

    private String prefix;

    private String ruleContent;

    private BigDecimal currentSeqNo;

    private Integer maxLength;

    private Integer userLength;

    private String memo;

    private String isValid;
}
