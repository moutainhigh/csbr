package com.csbr.cloud.camunda.server.process;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.camunda.server.request.CsbrCommonProcessRequest;
import com.csbr.cloud.camunda.server.request.CsbrCommonTaskRequest;
import com.csbr.cloud.common.response.CommonRes;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/7/28 10:22
 */
public interface CsbrProcessHandler {

    /**
     * 流程初始化
     */
    public CommonRes<List<TaskDto>> initProcess(@RequestBody CsbrCommonProcessRequest csbrCommonProcessRequest) throws Exception;

    /**
     * 流程提交
     */
    public CommonRes<List<TaskDto>> startProcess(@RequestParam String processInstanceId,@RequestParam String userId) throws Exception;

    /**
     * 查找历史任务 流程定义id
     */
    public List<HistoricTaskInstance> getHisTasks(@RequestParam String processDefKey) throws Exception;

    /**
     * 查找运行的任务
     */
    public List<TaskDto> getTaskkeys(@RequestParam String processDefKey) throws Exception;

    /**
     * 流程审批
     */
    public CommonRes<List<TaskDto>> approveProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest) throws Exception;

    /**
     * 流程撤回
     */
    public List<TaskDto> undoProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest) throws Exception;

    /**
     *
     * 流程驳回
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    public List<TaskDto> rollbackProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception;

    /**
     * 流程终止、作废
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    public List<TaskDto> terminateProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception;

    /**
     * 流程重启
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    public List<TaskDto> restartProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception;

    /**
     * 流程转办
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    public List<TaskDto> turnOverProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception;

    /**
     * 流程跳转
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    public List<TaskDto> jumpProcess(@RequestBody CsbrCommonTaskRequest csbrCommonTaskRequest,HttpServletRequest request) throws Exception;

    /**
     * 根据流程id获取任务实例详情
     */
    CommonRes<List<JSONObject>> getHisDetailsTasks(@RequestParam String processInstanceId);
}
