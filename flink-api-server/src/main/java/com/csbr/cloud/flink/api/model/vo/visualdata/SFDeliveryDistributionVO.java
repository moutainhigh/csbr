package com.csbr.cloud.flink.api.model.vo.visualdata;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @program: flink-api-service
 * @description:
 * @author: Huanglh
 * @create: 2020-01-16 13:45
 **/
@Data
public class SFDeliveryDistributionVO {
    private List<Map<String,String>> ylfb;
    private List<Map<String,String>> ck;
}
