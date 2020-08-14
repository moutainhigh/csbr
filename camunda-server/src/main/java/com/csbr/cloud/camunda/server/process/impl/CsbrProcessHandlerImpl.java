package com.csbr.cloud.camunda.server.process.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.camunda.server.process.CsbrProcessHandler;
import com.csbr.cloud.camunda.server.request.CsbrCommonProcessRequest;
import com.csbr.cloud.camunda.server.request.CsbrCommonTaskRequest;
import com.csbr.cloud.common.response.CommonRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.persistence.entity.TaskEntity;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Comment;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author zhangheng
 * @date 2020/7/28 10:30
 */
@Slf4j
@Service
@Transactional
public class CsbrProcessHandlerImpl implements CsbrProcessHandler {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    /**
     * 流程初始化根据流程定义ID
     *
     * @param csbrCommonProcessRequest
     */
    @Override
    @Transactional
    public CommonRes<List<TaskDto>> initProcess(CsbrCommonProcessRequest csbrCommonProcessRequest) throws Exception {
        //流程定义ID
        String processInstanceId = null;
        //待办任务集合
        List<TaskDto> resultList = new ArrayList<>();
        //流程变量
        Map<String, Object> variables = new HashMap<>();
        if(csbrCommonProcessRequest.getVariables() != null){
            variables = csbrCommonProcessRequest.getVariables();
        }
        //流程启动者
        variables.put("starter",csbrCommonProcessRequest.getStarter());
        variables.put("approval_state","");
        //省略流程中的参数
        // 流程初始化
        ProcessInstance processInstance = null;
        //流程定义key和流程定义id启动流程
        if(StringUtils.isNotBlank(csbrCommonProcessRequest.getProcessDefKey())){
            //根据流程文件定义的process节点的id启动流程 可以带入流程map参数
            processInstance = runtimeService.startProcessInstanceByKey(csbrCommonProcessRequest.getProcessDefKey(),variables);
        }else{
            //通过流程定义id来启动流程
            processInstance = runtimeService.startProcessInstanceById(csbrCommonProcessRequest.getProcessDefId(),variables);
        }
        if (processInstance != null && StringUtils.isNotBlank(processInstance.getId())) {
            //创建注释 任务id 流程id 注释
            taskService.createComment(processInstance.getId(),processInstance.getProcessInstanceId(), "审核中");
            processInstanceId = processInstance.getId();
            resultList = getTasks(processInstanceId);
        } else {
            throw new Exception("创建流程实例失败：");
        }
//        CommonRes.ok(resultList);
        return CommonRes.ok(resultList);
    }

    /**
     * 流程提交
     *
     * @param processInstanceId
     */
    @Override
    @Transactional
    public CommonRes<List<TaskDto>> startProcess(String processInstanceId,String userId) throws Exception {

        List<TaskDto> resultList = new ArrayList<TaskDto>();
        Map<String, Object> variables = new HashMap<String, Object>();
        // 创建成功
        if (StringUtils.isNotBlank(processInstanceId)) {
            List<TaskDto> taskList = getTasks(processInstanceId);
            log.info(JSON.toJSONString(taskList));
            if (taskList != null && taskList.size() == 1) {
                //任务成功执行时调用，最终用户提供所需的任务参数
                taskService.complete(taskList.get(0).getId(), variables);
                //创建注释
                taskService.createComment(taskList.get(0).getId(), processInstanceId, "提交流程");
                taskService.setAssignee(taskList.get(0).getId(),userId);
                resultList = getTasks(processInstanceId);
            } else {
                throw new Exception("获取提交任务失败：" + taskList.size());
            }
        } else {
            throw new Exception("创建流程实例失败：");
        }
        return CommonRes.ok(resultList);
    }

    /**
     * 查找运行的任务
     *
     * @param processDefKey
     */
    @Override
    public List<TaskDto> getTaskkeys(String processDefKey) throws Exception {
        List<TaskDto> resultList = new ArrayList<TaskDto>();
        //返回一个新的TaskQuery可用于动态查询任务的对象。 流程定义key
        List<Task> taskList = taskService.createTaskQuery().processDefinitionKey(processDefKey).list();
        for (Task task : taskList) {
            TaskDto dto = new TaskDto();
            dto = TaskDto.fromEntity(task);
            resultList.add(dto);
        }
        return resultList;
    }

    /**
     * 查找历史任务 流程定义id
     * @param processDefKey
     */
    @Override
    public List<HistoricTaskInstance> getHisTasks(String processDefKey) throws Exception {
        List<HistoricTaskInstance> resultList = new ArrayList<HistoricTaskInstance>();
        //创建历史任务 流程定义key
        resultList = historyService.createHistoricTaskInstanceQuery().processDefinitionKey(processDefKey).list();
        return resultList;
    }


    /**
     * 流程审批
     * @param csbrCommonTaskRequest
     */
    @Override
    @Transactional
    public CommonRes<List<TaskDto>> approveProcess(CsbrCommonTaskRequest csbrCommonTaskRequest) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        //流程变量参数
        Map<String, Object> variables = new HashMap<String, Object>();
        if(csbrCommonTaskRequest.getVariables() != null){
            variables = csbrCommonTaskRequest.getVariables();
        }

        //任务变量参数
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        if(csbrCommonTaskRequest.getTaskVariables() != null){
            taskVariables = csbrCommonTaskRequest.getTaskVariables();
        }

        //更新或创建执行任务变量
        runtimeService.setVariables(csbrCommonTaskRequest.getProcessInstanceId(), taskVariables);
        if(StringUtils.isNoneBlank(csbrCommonTaskRequest.getToActId())){
            //将任务标记为已完成，然后继续执行流程
            taskService.complete(csbrCommonTaskRequest.getTaskId(), variables);
            //创建对任务和/或流程实例的注释并返回该注释
            taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "重新提交流程");
            //查找活动中正在等待执行的活动
            ActivityInstance tree = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
            //修改流程实例
            runtimeService
                    .createProcessInstanceModification(csbrCommonTaskRequest.getProcessInstanceId())
                    .cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
                    .startBeforeActivity(csbrCommonTaskRequest.getToActId())
                    .execute();
        }else{
            //审批通过
            if(variables.get("approval_state").toString().equals("Y")){
                //创建注释
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type","审批通过");
                jsonObject.put("approval_state","Y");
                taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), jsonObject.toJSONString());
                taskService.setAssignee(csbrCommonTaskRequest.getTaskId(),csbrCommonTaskRequest.getUserId());
            }else {//审核不通过
                //创建注释
                //增加驳回原因
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type","审批驳回");
                jsonObject.put("approval_state","R");
                jsonObject.put("reason",variables.get("reason"));
                taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), jsonObject.toJSONString());
                taskService.setAssignee(csbrCommonTaskRequest.getTaskId(),csbrCommonTaskRequest.getUserId());
            }
            //当任务成功执行并且最终用户提供所需的任务参数时调用
            taskService.complete(csbrCommonTaskRequest.getTaskId(), variables);
        }
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        if (taskList != null && taskList.size() == 1) {
            //将给定任务的受让人更改为给定的userId。
            taskService.setAssignee(taskList.get(0).getId(), csbrCommonTaskRequest.getNextUserId());
        }
        return CommonRes.ok(taskList);
    }

    /**
     * 根据流程定义id获取任务列表
     * @param processInstanceId
     * @return
     * @throws Exception
     */
    public List<TaskDto> getTasks(String processInstanceId) throws Exception {
        List<TaskDto> resultList = new ArrayList<TaskDto>();
        //返回一个新的TaskQuery可用于动态查询任务的对象 流程定义id
        List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        for (Task task : taskList) {
            TaskDto dto = new TaskDto();
            dto = TaskDto.fromEntity(task);
            resultList.add(dto);
        }
        return resultList;
    }

    public String getInstanceIdForActivity(ActivityInstance activityInstance, String activityId) {
        ActivityInstance instance = getChildInstanceForActivity(activityInstance, activityId);
        if (instance != null) {
            return instance.getId();
        }
        return null;
    }

    public ActivityInstance getChildInstanceForActivity(ActivityInstance activityInstance, String activityId) {
        if (activityId.equals(activityInstance.getActivityId())) {
            return activityInstance;
        }
        for (ActivityInstance childInstance : activityInstance.getChildActivityInstances()) {
            ActivityInstance instance = getChildInstanceForActivity(childInstance, activityId);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    /**
     * 流程撤回
     *
     * @param csbrCommonTaskRequest
     */
    @Override
    @Transactional
    public List<TaskDto> undoProcess(CsbrCommonTaskRequest csbrCommonTaskRequest) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        //根据流程实例ID查找活动中正在等待执行的活动
        ActivityInstance tree = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
        //创建对任务和/或流程实例的注释并返回该注释
        taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "撤回流程");
        runtimeService
                .createProcessInstanceModification(csbrCommonTaskRequest.getProcessInstanceId())
                .cancelActivityInstance(getInstanceIdForActivity(tree, tree.getActivityId()))
                .startBeforeActivity(csbrCommonTaskRequest.getTaskDefinitionKey())
                .execute();
        //获取任务列表
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }

    /**
     * 流程驳回
     *
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TaskDto> rollbackProcess(CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception {
        String rejectType = csbrCommonTaskRequest.getRejectType();
        if(StringUtils.isBlank(rejectType)){
            throw new Exception("驳回类型不能为空！");
        }
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        //根据流程实例ID查找活动中正在等待执行的活动
        ActivityInstance tree = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
        //驳回类型为起始节点
        if(rejectType.equals(CsbrCommonTaskRequest.REJECT_TO_START)){
            //创建一个新的程序化查询来搜索
            List<HistoricActivityInstance> resultList = historyService
                    .createHistoricActivityInstanceQuery()
                    //仅选择具有给定流程实例的历史活动实例
                    .processInstanceId(csbrCommonTaskRequest.getProcessInstanceId())
                    //仅选择具有给定活动类型的活动的历史活动实例
                    .activityType("userTask")
                    //仅选择已完成的历史活动实例。
                    .finished()
                    //按顺序排序（需要后跟Query.asc()或Query.desc()）。
                    .orderByHistoricActivityInstanceEndTime()
                    .asc()
                    .list();
            if (resultList == null || resultList.size() <= 0) {
                throw new Exception("未找到发起节点");
            }
            //目标节点id
            csbrCommonTaskRequest.setToActId(resultList.get(0).getActivityId());
        }else if(rejectType.equals(CsbrCommonTaskRequest.REJECT_TO_LAST)){//上一节点
            List<HistoricActivityInstance> resultList = historyService
                    .createHistoricActivityInstanceQuery()
                    .processInstanceId(csbrCommonTaskRequest.getProcessInstanceId())
                    .activityType("userTask")
                    .finished()
                    //按顺序排序（需要后跟Query.asc()或Query.desc()）。
                    .orderByHistoricActivityInstanceEndTime()
                    .desc()
                    .list();
            if (resultList == null || resultList.size() <= 0) {
                throw new Exception("未找到上一节点");
            }
            //目标节点id
            csbrCommonTaskRequest.setToActId(resultList.get(0).getActivityId());
        }else if(rejectType.equals(CsbrCommonTaskRequest.REJECT_TO_TARGET)){//目标节点
            if(StringUtils.isBlank(csbrCommonTaskRequest.getToActId())){
                throw new Exception("指定目标节点不能为空！");
            }
        }else{
            throw new Exception("驳回类型值不对，三种类型  1：起草节点，2：上一节点，3：目标节点！");
        }
        //创建对任务和/或流程实例的注释并返回该注释
        taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "驳回流程");
        runtimeService
                //修改流程实例
                .createProcessInstanceModification(csbrCommonTaskRequest.getProcessInstanceId())
                //取消当前活动流程
                .cancelActivityInstance(getInstanceIdForActivity(tree, csbrCommonTaskRequest.getTaskDefinitionKey()))
                .startBeforeActivity(csbrCommonTaskRequest.getToActId())
                .execute();
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }

    /**
     * 流程终止、作废
     *
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TaskDto> terminateProcess(CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        ActivityInstance tree = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
        taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "终止、作废流程");
        //删除现有的运行时流程实例
        runtimeService.deleteProcessInstance(csbrCommonTaskRequest.getProcessInstanceId(), TaskEntity.DELETE_REASON_COMPLETED);
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }

    /**
     * 流程重启
     *
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TaskDto> restartProcess(CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        String processDefId = csbrCommonTaskRequest.getProcessDefinitionId();
        //流程定义id为空
        if(StringUtils.isBlank(processDefId)){
            //创建历史
            processDefId = historyService
                    .createHistoricProcessInstanceQuery()
                    .processInstanceId(csbrCommonTaskRequest.getProcessInstanceId())
                    .singleResult().getProcessDefinitionId();
        }
        log.info("processDefId---->"+processDefId);
        //重启流程
        runtimeService.restartProcessInstances(processDefId)
                .startBeforeActivity(csbrCommonTaskRequest.getTaskDefinitionKey())
                .initialSetOfVariables()
                .processInstanceIds(csbrCommonTaskRequest.getProcessInstanceId())
                .execute();
        //获取任务列表
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }

    /**
     * 流程转办
     *
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TaskDto> turnOverProcess(CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();
        //返回一个新的TaskQuery可用于动态查询任务的对象。
        Task task = taskService.createTaskQuery().taskId(csbrCommonTaskRequest.getTaskId()).singleResult();
        //将给定任务的受让人更改为给定的userId。
        task.setAssignee(csbrCommonTaskRequest.getNextUserId());
        //将给定的任务保存到持久数据存储中。
        taskService.saveTask(task);
        String comment = csbrCommonTaskRequest.getUserId()+"将流程转办给"+csbrCommonTaskRequest.getNextUserId()+"处理";
        //创建注释
        taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(),comment);
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }

    /**
     * 流程跳转
     *
     * @param csbrCommonTaskRequest
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public List<TaskDto> jumpProcess(CsbrCommonTaskRequest csbrCommonTaskRequest, HttpServletRequest request) throws Exception {
        List<TaskDto> taskList = new ArrayList<TaskDto>();

        ActivityInstance tree = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
        if(StringUtils.isBlank(csbrCommonTaskRequest.getJumpType())){
            throw new Exception("跳转类型不能为空！");
        }
        if(StringUtils.isBlank(csbrCommonTaskRequest.getToActId())){
            throw new Exception("目标节点不能为空！");
        }
        //往回跳转流程
        if(csbrCommonTaskRequest.getJumpType().equals(CsbrCommonTaskRequest.JUMP_BACK)){
            //创建注释
            taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "往回跳转流程");
            //修改流程实例
            runtimeService
                    .createProcessInstanceModification(csbrCommonTaskRequest.getProcessInstanceId())
                    .cancelActivityInstance(getInstanceIdForActivity(tree, csbrCommonTaskRequest.getTaskDefinitionKey()))
                    .startBeforeActivity(csbrCommonTaskRequest.getToActId())
                    .execute();
        }else if(csbrCommonTaskRequest.getJumpType().equals(CsbrCommonTaskRequest.JUMP_FORWARD)){//往前跳转流程
            //任务成功执行时调用，最终用户提供所需的任务参数
            taskService.complete(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getVariables());
            //创建注释
            taskService.createComment(csbrCommonTaskRequest.getTaskId(), csbrCommonTaskRequest.getProcessInstanceId(), "往前跳转流程");
            //查找活动中正在等待的所有执行的活动实例
            ActivityInstance tree2 = runtimeService.getActivityInstance(csbrCommonTaskRequest.getProcessInstanceId());
            //修改流程实例
            runtimeService
                    .createProcessInstanceModification(csbrCommonTaskRequest.getProcessInstanceId())
                    .cancelActivityInstance(getInstanceIdForActivity(tree2, tree2.getActivityId()))
                    .startBeforeActivity(csbrCommonTaskRequest.getToActId())
                    .execute();
        }
        taskList = getTasks(csbrCommonTaskRequest.getProcessInstanceId());
        return taskList;
    }


    /**
     * 根据流程id获取任务实例详情
     *
     * @param processInstanceId
     */
    @Override
    public CommonRes<List<JSONObject>> getHisDetailsTasks(String processInstanceId) {

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();
        List<JSONObject> result=new LinkedList<>();
//        System.out.println(list.size());
        for (HistoricActivityInstance historicActivityInstance : list) {
            JSONObject map=new JSONObject();
            String taskId = historicActivityInstance.getTaskId();
            List<Comment> taskComments = taskService.getTaskComments(taskId);
//            System.out.println(taskComments.size());
            //不记录未知节点信息
            if(!matching(historicActivityInstance.getActivityType()).equals("未知节点")){
                map.put("processInstanceId",historicActivityInstance.getProcessInstanceId());
                map.put("taskId",historicActivityInstance.getTaskId());
                map.put("activityName",historicActivityInstance.getActivityName());
                map.put("activityType",matching(historicActivityInstance.getActivityType()));
                map.put("assignee",historicActivityInstance.getAssignee()==null?"无":historicActivityInstance.getAssignee());
                map.put("startTime", DateFormatUtils.format(historicActivityInstance.getStartTime(),"yyyy-MM-dd HH:mm:ss") );
                if(historicActivityInstance.getEndTime() != null){
                    map.put("endTime",DateFormatUtils.format(historicActivityInstance.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
                }
                if(historicActivityInstance.getEndTime() != null){
                    map.put("costTime",getDatePoor(historicActivityInstance.getEndTime(),historicActivityInstance.getStartTime()));
                }
                if (taskComments.size()>0){
                    map.put("message",taskComments.get(0).getFullMessage());
                }else {
                    map.put("message","无");
                }
                result.add(map);
            }
        }
//        System.out.println(JSON.toJSONString(result));
        return CommonRes.ok(result);
    }

    private String matching(String ActivityType){
        String value="";
        switch (ActivityType){
            case "startEvent":
                value="流程开始";
                break;
            case "userTask":
                value="用户处理";
                break;
            case "noneEndEvent":
                value="流程结束";
                break;
            default:
                value="未知节点";
                break;
        }
        return value;
    }

    public  String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟"+ sec + "秒";
    }
}
