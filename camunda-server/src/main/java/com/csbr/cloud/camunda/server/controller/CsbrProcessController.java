package com.csbr.cloud.camunda.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.camunda.server.process.CsbrProcessHandler;
import com.csbr.cloud.camunda.server.request.CsbrCommonProcessRequest;
import com.csbr.cloud.camunda.server.request.CsbrCommonTaskRequest;
import com.csbr.cloud.common.response.CommonRes;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/31 11:14
 */
@RestController
@RequestMapping("/csbr-process")
@Api(tags = {"商户备案流程"})
@Slf4j
public class CsbrProcessController {

    @Autowired
    private CsbrProcessHandler csbrProcessHandler;

    @ApiOperation(value = "流程初始化", notes = "流程初始化")
    @RequestMapping(value = "/initProcess", method = RequestMethod.POST)
    public CommonRes<List<TaskDto>> initProcess(@RequestBody CsbrCommonProcessRequest csbrCommonProcessRequest){
        try {
            CommonRes<List<TaskDto>> listCommonRes = csbrProcessHandler.initProcess(csbrCommonProcessRequest);
            return listCommonRes;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

    @ApiOperation(value = "流程提交", notes = "流程提交")
    @RequestMapping(value = "/startProcess", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "processInstanceId", dataType = "String", value = "流程id"),
            @ApiImplicitParam(paramType = "query", required = true, name = "userId", dataType = "String", value = "用户信息"),
    })
    public CommonRes<List<TaskDto>> startProcess(
            @RequestParam(value = "processInstanceId",required = true) String processInstanceId,
            @RequestParam(value = "userId",required = true) String userId
    ){
        try {
            CommonRes<List<TaskDto>> listCommonRes = csbrProcessHandler.startProcess(processInstanceId, userId);
            return listCommonRes;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

    @ApiOperation(value = "查找历史任务", notes = "根据流程定义Key查找历史任务记录")
    @RequestMapping(value = "/getHisTasks/{processDefKey}", method = RequestMethod.POST)
    public List<HistoricTaskInstance> getHisTasks(
            @ApiParam(name = "processDefKey", value = "流程定义Key", required = true) @PathVariable String processDefKey)
            throws Exception{
        List<HistoricTaskInstance> hisTasks = csbrProcessHandler.getHisTasks(processDefKey);
        return hisTasks;
    }

    @ApiOperation(value = "查找运行任务", notes = "根据流程定义Key查找运行任务")
    @RequestMapping(value = "/getTaskIds/{processDefKey}", method = RequestMethod.POST)
    public List<TaskDto> getTaskIds(
            @ApiParam(name = "processDefKey", value = "流程定义Key", required = true) @PathVariable String processDefKey)
            throws Exception{
        List<TaskDto> taskIds = csbrProcessHandler.getTaskkeys(processDefKey);
        return taskIds;
    }


    @ApiOperation(value = "流程审批", notes = "流程审批")
    @RequestMapping(value = "/approveProcess", method =RequestMethod.POST)
    public CommonRes<List<TaskDto>>  approveProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request){
        try {
            CommonRes<List<TaskDto>> listCommonRes = csbrProcessHandler.approveProcess(csbrCommonTaskRequest);
            return listCommonRes;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }

    }

//    @ApiOperation(value = "流程撤回", notes = "流程撤回")
//    @RequestMapping(value = "/undoProcess", method =  RequestMethod.POST)
//    public List<TaskDto> undoProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception{
//        List<TaskDto> taskDtos = csbrProcessHandler.undoProcess(csbrCommonTaskRequest);
//        return taskDtos;
//    }

//    @ApiOperation(value = "流程驳回", notes = "流程驳回，驳回类型，1：起草节点，2：上一节点，3：目标节点")
//    @RequestMapping(value = "/rollbackProcess", method =  RequestMethod.POST)
//    public List<TaskDto> rollbackProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception{
//        List<TaskDto> taskDtos = csbrProcessHandler.rollbackProcess(csbrCommonTaskRequest,request);
//        return taskDtos;
//    }

    @ApiOperation(value = "流程终止、作废", notes = "流程终止、作废，审批人执行为终止、提交人执行草稿状态的为作废")
    @RequestMapping(value = "/terminateProcess", method =  RequestMethod.POST)
    public List<TaskDto> terminateProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception{
        List<TaskDto> taskDtos = csbrProcessHandler.terminateProcess(csbrCommonTaskRequest, request);
        return taskDtos;
    }

//    @ApiOperation(value = "流程重启", notes = "流程重启，被审批通过、终止、作废的流程可重新启动到指定节点")
//    @RequestMapping(value = "/restartProcess", method =  RequestMethod.POST)
//    public List<TaskDto> restartProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception{
//        List<TaskDto> taskDtos = csbrProcessHandler.restartProcess(csbrCommonTaskRequest, request);
//        return taskDtos;
//    }

//    @ApiOperation(value = "流程转办", notes = "流程转办，将当期任务转交给其他人处理")
//    @RequestMapping(value = "/turnOverProcess", method =  RequestMethod.POST)
//    public List<TaskDto> turnOverProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception{
//        List<TaskDto> taskDtos = csbrProcessHandler.turnOverProcess(csbrCommonTaskRequest, request);
//        return taskDtos;
//    }

//    @ApiOperation(value = "流程跳转", notes = "流程跳转，跳转类型,1:往前跳转，2：往回跳转")
//    @RequestMapping(value = "/jumpProcess", method =  RequestMethod.POST)
//    public List<TaskDto> jumpProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception{
//        List<TaskDto> taskDtos = csbrProcessHandler.jumpProcess(csbrCommonTaskRequest, request);
//        return taskDtos;
//    }

    /**
     * 根据流程id获取任务详情
     */
    @ApiOperation(value = "查找历史详情", notes = "根据流程id查找任务详情")
    @RequestMapping(value = "/getHisTasksDetail/{processInstanceId}", method = RequestMethod.POST)
    public CommonRes<List<JSONObject>> getHisTasksDetail(
            @ApiParam(name = "processInstanceId", value = "流程id", required = true) @PathVariable String processInstanceId){
        try {
            CommonRes<List<JSONObject>> hisDetailsTasks = csbrProcessHandler.getHisDetailsTasks(processInstanceId);
            return hisDetailsTasks;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

}
