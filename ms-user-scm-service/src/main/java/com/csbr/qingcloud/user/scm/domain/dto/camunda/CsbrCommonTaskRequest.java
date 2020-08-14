package com.csbr.qingcloud.user.scm.domain.dto.camunda;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/7/28 16:49
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="CsbrCommonTaskRequest-流程提交参数对象")
@Data
public class CsbrCommonTaskRequest {

    @ApiModelProperty(value="流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value="流程定义ID")
    private String processDefinitionId;

    @ApiModelProperty(value="任务ID")
    private String taskId;

    @ApiModelProperty(value="任务Key")
    private String taskDefinitionKey;

    @ApiModelProperty(value="任务处理人")
    private String userId;

    @ApiModelProperty(value="流程变量键值对")
    private Map<String, Object> variables;

    @ApiModelProperty(value="任务变量键值对")
    private Map<String, Object> taskVariables;

    @ApiModelProperty(value="下一节点任务处理人")
    private String nextUserId;

    @ApiModelProperty(value="驳回类型，1：起草节点，2：上一节点，3：目标节点")
    private String rejectType;

    @ApiModelProperty(value="目标节点Id")
    private String toActId;

    @ApiModelProperty(value="跳转类型，1：往前跳转，2：往回跳转")
    private String jumpType;

    /**
     * 驳回类型
     */
    public static String REJECT_TO_START ="1";
    public static String REJECT_TO_LAST ="2";
    public static String REJECT_TO_TARGET ="3";
    public static String WITHDRAW_TO_START ="1";
    public static String WITHDRAW_TO_TARGET ="2";
    /**
     * 跳转类型
     */
    public static String JUMP_FORWARD ="1";
    public static  String JUMP_BACK ="2";


}
