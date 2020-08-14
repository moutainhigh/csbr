package com.csbr.cloud.hy.server.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/29 17:08
 * OTMS需求订单下单接口实体
 */
@Data
public class ExeOrderList {

    @ApiModelProperty("地址取数逻辑（1-始发地和⽬的地信息 均提前在 OTMS 系统维护；2-始发地 信息有提前维护，⽬的地信息没有提前 维护；3-始发地信息没有提前维护，⽬ 的地信息有提前维护；4-始发地和⽬的 地信息均没有提前维护。针对提前维护 的站点信息，OTMS系统会校验站点信 息是否存在；对于没有提前维护的站点 信息，OTMS会校验联系⼈、联系⽅ 式、详细地址是否填写）")
    private Integer address_fetching_logic;
    @ApiModelProperty("始发地站点编码")
    private String start_station_code;
    @ApiModelProperty("始发地站点名称")
    private String start_station_name;
    @ApiModelProperty("始发地联系⼈姓名")
    private String start_contact_name;
    @ApiModelProperty("始发地联系⼈电话")
    private String start_contact_phone;
    @ApiModelProperty("始发地详细地址，包含省市区信息")
    private String start_station_address;
    @ApiModelProperty("⽬的地站点编码")
    private String end_station_code;
    @ApiModelProperty("⽬的地站点名称")
    private String end_station_name;
    @ApiModelProperty("⽬的地联系⼈姓名")
    private String end_contact_name;
    @ApiModelProperty("⽬的地联系⼈电话")
    private String end_contact_phone;
    @ApiModelProperty("⽬的地详细地址，包含省市区信息")
    private String end_station_address;
    @ApiModelProperty("⼤⽹产品类型")
    private Integer express_type;
    @ApiModelProperty("付款⽅式")
    private Integer pay_method;
    @ApiModelProperty("温层（1-⾮温控，2-2℃~8℃，310℃~25℃，4-0℃~5℃")
    private Integer temperature;
    @ApiModelProperty("运输⽅式（1-陆运，2-空运，3-海运")
    private Integer transport_type;
    @ApiModelProperty("是否保价（1-是，2-否），默认为2")
    private Integer is_insure;
    @ApiModelProperty("保价⾦额，选择保价服务时必填且⼤于 0，单位元，保留⼩数点后两位")
    private String insure_value;
    @ApiModelProperty("是否代收货款（1-是，2-否），默认为 2")
    private Integer is_cod;
    @ApiModelProperty("代收货款⾦额，选择代收货款服务时必 填且⼤于 0，单位元，保留⼩数点后两 位")
    private String cod_value;
    @ApiModelProperty("是否签回单（1-是，2-否），默认为2")
    private Integer is_return_tracking;
    @ApiModelProperty("运输单备注信息")
    private String exe_order_remark;
    @ApiModelProperty("运输单包含的货品信息列表，对象数组")
    private List<SkuList> sku_list;
}
