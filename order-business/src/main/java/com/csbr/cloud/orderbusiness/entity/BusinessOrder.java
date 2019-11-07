package com.csbr.cloud.orderbusiness.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/11/6 15:05
 */
@Data
public class BusinessOrder extends BaseDO {

    private String cargoOwnerName;

    private String bBillNo;

    private String state;

    private String billType;

    private String businessRelatedCompany;

    private Date preDeliverDate;

    private Date preArrivalDate;

    private String address;

    private BigDecimal pkgs;

    private BigDecimal detailSum;

    private Date inputDate;

    private Integer goodsSpecNum;

}
