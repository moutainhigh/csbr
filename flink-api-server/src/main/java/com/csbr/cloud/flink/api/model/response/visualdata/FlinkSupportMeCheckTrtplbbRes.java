package com.csbr.cloud.flink.api.model.response.visualdata;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeTrtplbb;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-14 16:24
 **/
@Data
@ApiModel(value = "响应模型")
public class FlinkSupportMeCheckTrtplbbRes {
    @ApiModelProperty(value = "是否可以关联上 trtplbb 表")
    private Boolean checkTrtplbb = false;
    @ApiModelProperty(value = "trtplbb 表数据")
    private MeTrtplbb meTrtplbb;
}
