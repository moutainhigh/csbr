package com.csbr.cloud.orderlogistics.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/11/6 16:21
 */
@Data
public class DispatchOrderDetail extends BaseDO {

    private Integer rowNo;

    private String cargoOwnerName;

    private String bbillNo;

    private Integer bbillRowNo;

    private String billType;

    private String businessRelatedCompany;

    private String goodsGuid;

    private String goodsCode;

    private String goodsName;

    private BigDecimal unitQty;

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
