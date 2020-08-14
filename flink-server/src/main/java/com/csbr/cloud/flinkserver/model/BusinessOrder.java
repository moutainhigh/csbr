package com.csbr.cloud.flinkserver.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2019/11/6 15:05
 */
@Data
public class BusinessOrder{

    private String cargo_owner_name;

    private String b_bill_no;

    public BusinessOrder(String cargo_owner_name, String b_bill_no) {
    }

//    private String state;
//
//    private String bill_type;
//
//    private String business_related_company;
//
//    private Date pre_deliver_date;
//
//    private Date pre_arrival_date;
//
//    private String address;
//
//    private BigDecimal pkgs;
//
//    private BigDecimal detail_sum;
//
//    private Date input_date;
//
//    private Integer goods_spec_num;

}
