package com.csbr.cloud.hy.server.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/29 15:27
 * OTMS需求订单下单接口实体
 */
@Data
public class OTMSOrder {

    @ApiModelProperty("客户订单号")
    private String customer_order_code;

    @ApiModelProperty("OTMS系统项⽬编码（需要提前在OTMS 系统内维护)")
    private String project_code;

    @ApiModelProperty("OTMS系统客户编码（需要提前在OTMS 系统内维护")
    private String customer_code;

    @ApiModelProperty("月结卡号")
    private String cust_id;

    @ApiModelProperty("需求订单类型（1-运输单，2-仓配单）， 默认为1")
    private Integer order_type;

    @ApiModelProperty("是否需要⼈⼯审核（1需要；2不需要） 不传的话，默认是不需要⼈⼯审核")
    private Integer is_need_check;

    @ApiModelProperty("是否直接下发（1直接下发；2不直接下 发） 不传的话，默认是直接下发； 注意：直接下发若出现错误，会导致下单 失败")
    private Integer auto_push;

    @ApiModelProperty("需求订单包含的运输单列表数据，对象数 组成员字段⻅详细信息表格")
    private List<ExeOrderList> exe_order_list;




}
