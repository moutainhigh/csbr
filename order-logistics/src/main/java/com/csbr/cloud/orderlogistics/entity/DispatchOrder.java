package com.csbr.cloud.orderlogistics.entity;

import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/11/6 16:16
 */
@Data
public class DispatchOrder extends BaseDO {

    private String tplpoNo;

    private String state;

    private String cargoOwnerName;

    private String deliverName;

    private String routeName;

    private String departureDistrict;

    private String departureVenue;

    private String arrivalVenue;

    private String sendContacts;

    private String sendContactsTel;

    private Date departureTime;

    private Date arrivalArrivedTime;

    private Date inputDate;

    private Integer timeLimit;

    private BigDecimal carrierAmount;

    private String deliveryWay;

    private String transportWay;

}
