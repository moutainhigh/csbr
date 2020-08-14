package com.csbr.cloud.camunda.server.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/7/28 10:35
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="CsbrCommonProcessRequest-流程提交参数对象")
@Data
public class CsbrCommonProcessRequest {

    @ApiModelProperty(value="流程定义ID")
    private String processDefId;

    @ApiModelProperty(value="流程定义Key")
    private String processDefKey;

    @ApiModelProperty(value="启动者")
    private String starter;

    @ApiModelProperty(value="流程标题")
    private	String title;

    @ApiModelProperty(value="流程变量键值对")
    private Map<String, Object> variables;
}
