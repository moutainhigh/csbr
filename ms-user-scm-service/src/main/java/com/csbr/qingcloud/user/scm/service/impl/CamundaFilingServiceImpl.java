package com.csbr.qingcloud.user.scm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.entity.UserInfo;
import com.csbr.cloud.mybatis.interceptor.UserContextHolder;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonTaskRequest;
import com.csbr.qingcloud.user.scm.feign.CamundaFeign;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantFilingService;
import com.csbr.qingcloud.user.scm.service.CamundaFilingService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/8/5 17:19
 * 商户备案流程
 */
@Service
@Slf4j
@Transactional
public class CamundaFilingServiceImpl implements CamundaFilingService {

    @Autowired
    private CamundaFeign camundaFeign;

    @Autowired
    private MfMerchantFilingService mfMerchantFilingService;

    /**
     * 商户备案(初始化)
     *
     * @param mfMerchantFiling
     */
    @Override
    @GlobalTransactional
    public CommonRes<Boolean> initFilingProcess(MfMerchantFiling mfMerchantFiling) {
        boolean flag = false;
        CsbrCommonProcessRequest csbrCommonProcessRequest = new CsbrCommonProcessRequest();
        MfMerchantFiling byId = mfMerchantFilingService.getById(mfMerchantFiling.getGuid());
        if(byId == null && StringUtils.isEmpty(byId.getProcDefId())){
            //初始化流程
            //商户备案初始化
            csbrCommonProcessRequest.setProcessDefKey("Merchant_Filing_And_Approval_Process_1");
            JSONObject initProcess = camundaFeign.initProcess(csbrCommonProcessRequest);
            if(initProcess != null && initProcess.get("code").equals("00000")){
                JSONArray data = initProcess.getJSONArray("data");
                if(data != null && data.size() > 0){
                    //获取任务id
                    JSONObject o = (JSONObject) data.get(0);
                    String taskId = o.getString("id");
                    //流程id
                    String processInstanceId = o.getString("processInstanceId");
                    if(byId == null){//无此任务 新增
                        mfMerchantFiling.setTaskId(taskId);
                        mfMerchantFiling.setProcDefId(processInstanceId);
                        //设置状态为审核中
                        mfMerchantFiling.setApprovalState("A");
                        flag = mfMerchantFilingService.save(mfMerchantFiling);
                        if (!flag) {
                            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("流程(%s)新增失败。", processInstanceId));
                        }
                    }
                }
            }
        }else{
            //商户备案 驳回 走提交流程
            JSONObject hisTasksDetail = camundaFeign.getHisTasksDetail(byId.getProcDefId());
            if(hisTasksDetail != null && hisTasksDetail.get("code").equals("00000")){
                JSONArray data = hisTasksDetail.getJSONArray("data");
                //获取最后一条记录
                JSONObject o = data.getJSONObject(data.size() - 1);
                if(o.getString("activityName").equals("提交编辑")){//最新任务节点是提交编辑
                    String userInfo = UserContextHolder.get();
                    String u = "admin";
                    if(StringUtils.isEmpty(userInfo)){
                        userInfo = u;
                    }
                    //提交流程
                    camundaFeign.startProcess(o.getString("processInstanceId"),userInfo);
                    //修改本地库状态
                    mfMerchantFiling.setApprovalState("A");
                    mfMerchantFiling.setTaskId(o.getString("taskId"));
                    LambdaUpdateWrapper<MfMerchantFiling> mfMerchantFilingLambdaUpdateWrapper = mfMerchantFilingService.csbrUpdateWrapper(MfMerchantFiling.class);
                    mfMerchantFilingLambdaUpdateWrapper.eq(MfMerchantFiling::getProcDefId,o.getString("processInstanceId"));
                    flag = mfMerchantFilingService.update(mfMerchantFiling, mfMerchantFilingLambdaUpdateWrapper);
                    if(!flag){
                        throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("流程(%s)更新失败，可能已在其他终端更新，请重试。", o.getString("processInstanceId")));
                    }
                }
            }
        }
        return CommonRes.success(flag);
    }

    /**
     * 商户备案审批
     *
     * @param processInstanceId
     * @param approvalState
     * @param rejectReason
     */
    @Override
    @Transactional
    public CommonRes filingApproveProcess(String processInstanceId, String approvalState, String rejectReason) {
        boolean flag = false;
        //获取最新的一条任务信息
        JSONObject hisTasksDetail = camundaFeign.getHisTasksDetail(processInstanceId);
        if(hisTasksDetail != null && hisTasksDetail.get("code").equals("00000")){
            JSONArray data = hisTasksDetail.getJSONArray("data");
            //获取最后一条记录
            JSONObject o = data.getJSONObject(data.size() - 1);
            if(StringUtils.isNotEmpty(o.getString("taskId"))){
                //商户备案审批
                CsbrCommonTaskRequest csbrCommonTaskRequest = new CsbrCommonTaskRequest();
                csbrCommonTaskRequest.setProcessInstanceId(processInstanceId);
                csbrCommonTaskRequest.setTaskId(o.getString("taskId"));
                String userInfo = UserContextHolder.get();
                String u = "admin";
                if(StringUtils.isEmpty(userInfo)){
                    userInfo = u;
                }
                csbrCommonTaskRequest.setUserId(userInfo);
                //流程变量参数
                Map<String, Object> variables = new HashMap<String, Object>();
                //审批状态
                variables.put("approval_state",approvalState);
                if(StringUtils.isNotEmpty(rejectReason)){
                    //驳回原因
                    variables.put("reason",rejectReason);
                }
                //审批
                JSONObject jsonObject = camundaFeign.approveProcess(csbrCommonTaskRequest);
                if(jsonObject != null && jsonObject.get("code").equals("00000")){
                    //审批通过后流程结束 data节点为空
                    MfMerchantFiling mfMerchantFiling = new MfMerchantFiling();
                    mfMerchantFiling.setProcDefId(o.getString("processInstanceId"));
                    if(StringUtils.isNotEmpty(rejectReason)){
                        mfMerchantFiling.setRejectReason(rejectReason);
                    }
                    if(StringUtils.isNotBlank(o.getString("taskId"))){
                        mfMerchantFiling.setTaskId(o.getString("taskId"));
                    }
                    mfMerchantFiling.setApprovalState(approvalState);
                    LambdaUpdateWrapper<MfMerchantFiling> mfMerchantFilingLambdaUpdateWrapper = mfMerchantFilingService.csbrUpdateWrapper(MfMerchantFiling.class);
                    mfMerchantFilingLambdaUpdateWrapper.eq(MfMerchantFiling::getProcDefId,processInstanceId);
                    flag = mfMerchantFilingService.update(mfMerchantFiling, mfMerchantFilingLambdaUpdateWrapper);
                    if(!flag){
                        throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("流程(%s)更新失败，可能已在其他终端更新，请重试。", csbrCommonTaskRequest.getProcessInstanceId()));
                    }
                }
            }
        }
        return CommonRes.success(flag);
    }

    /**
     * 商户备案详情
     *
     * @param processInstanceId
     */
    @Override
    public CommonRes getFilingHisTasksDetail(String processInstanceId) {
        JSONObject hisTasksDetail = camundaFeign.getHisTasksDetail(processInstanceId);
        if(hisTasksDetail != null && hisTasksDetail.get("code").equals("00000")){
            JSONArray data = hisTasksDetail.getJSONArray("data");
            if(data != null && data.size() > 0){
                return CommonRes.success(data);
            }
        }
        return CommonRes.success("无此流程详情信息",null);
    }

    /**
     * 商户备案查询
     *
     * @param obj
     */
    @Override
    public PageListVO getFilingPageList(JSONObject obj) {
        PageListVO filingPageList = mfMerchantFilingService.getFilingPageList(obj);
        return filingPageList;
    }
}
