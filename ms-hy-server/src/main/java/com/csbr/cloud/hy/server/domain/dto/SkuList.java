package com.csbr.cloud.hy.server.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/7/29 17:19
 * OTMS需求订单下单接口实体
 */
@Data
public class SkuList {

    @ApiModelProperty("货品编码")
    private String sku_no;
    @ApiModelProperty("货品名称")
    private String sku_name;
    @ApiModelProperty("货品数量，需⼤于0")
    private Integer sku_count;
    @ApiModelProperty("单个货品重量，单位kg，需⼤于 0，保留 ⼩数点后两位")
    private String sku_weight;
    @ApiModelProperty("单个货品体积，单位m³，需⼤于 0，保留 ⼩数点后两位")
    private String sku_volume;

}
