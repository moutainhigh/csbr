package com.csbr.cloud.orderbusiness.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/11/6 15:11
 */
@Data
public class BusinessOrderDetail extends BaseDO {

    private Integer rowNo;

    private String bBillNo;

    private String goodsGuid;

    private String goodsCode;

    private String goodsName;

    private String unitQty;

    private BigDecimal price;

    private BigDecimal pcks;

    private BigDecimal qty;

    private BigDecimal amount;

    private String lot;

    private Date productionDate;

    private Date expireDate;

    private String productionDateT;

    private String expireDateT;


}
