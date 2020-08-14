package com.csbr.cloud.camunda.server.controller;

import com.csbr.cloud.camunda.server.request.PscCommonProcessRequest;
import com.csbr.cloud.camunda.server.request.PscCommonTaskRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/11/15 15:04
 */
@RestController
@RequestMapping("/simpleProcessHandler")
public interface SimpleProcessHandler {

    @ApiOperation(value = "流程初始化", notes = "流程初始化")
    @RequestMapping(value = "/simpleInitProcess", method = RequestMethod.POST)
    public List<TaskDto> simpleInitProcess(@RequestBody PscCommonProcessRequest pscCommonProcessRequest) throws Exception;

    @ApiOperation(value = "流程提交", notes = "流程提交")
    @RequestMapping(value = "/simpleStartProcess", method = RequestMethod.POST)
    public List<TaskDto> simpleStartProcess(@RequestBody PscCommonProcessRequest pscCommonProcessRequest, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "查找历史任务", notes = "根据流程定义Key查找历史任务记录")
    @RequestMapping(value = "/simpleGetHisTasks/{processDefKey}", method = RequestMethod.POST)
    public List<HistoricTaskInstance> simpleGetHisTasks(
            @ApiParam(name = "processDefKey", value = "流程定义Key", required = true) @PathVariable String processDefKey)
            throws Exception;

    @ApiOperation(value = "查找运行任务", notes = "根据流程定义Key查找运行任务")
    @RequestMapping(value = "/simpleGetTaskIds/{processDefKey}", method = RequestMethod.POST)
    public List<TaskDto> simpleGetTaskIds(
            @ApiParam(name = "processDefKey", value = "流程定义Key", required = true) @PathVariable String processDefKey)
            throws Exception;


    @ApiOperation(value = "流程审批", notes = "流程审批")
    @RequestMapping(value = "/simpleApproveProcess", method =RequestMethod.POST)
    public List<TaskDto>  simpleApproveProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程撤回", notes = "流程撤回")
    @RequestMapping(value = "/simpleUndoProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleUndoProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程驳回", notes = "流程驳回，驳回类型，1：起草节点，2：上一节点，3：目标节点")
    @RequestMapping(value = "/simpleRollbackProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleRollbackProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程终止、作废", notes = "流程终止、作废，审批人执行为终止、提交人执行草稿状态的为作废")
    @RequestMapping(value = "/simpleTerminateProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleTerminateProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程重启", notes = "流程重启，被审批通过、终止、作废的流程可重新启动到指定节点")
    @RequestMapping(value = "/simpleRestartProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleRestartProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程转办", notes = "流程转办，将当期任务转交给其他人处理")
    @RequestMapping(value = "/simpleTurnOverProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleTurnOverProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

    @ApiOperation(value = "流程跳转", notes = "流程跳转，跳转类型,1:往前跳转，2：往回跳转")
    @RequestMapping(value = "/simpleJumpProcess", method =  RequestMethod.POST)
    public List<TaskDto> simpleJumpProcess(@RequestBody PscCommonTaskRequest pscCommonTaskRequest,HttpServletRequest request) throws Exception;

}
