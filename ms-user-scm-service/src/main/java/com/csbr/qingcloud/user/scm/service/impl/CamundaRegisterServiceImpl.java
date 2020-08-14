package com.csbr.qingcloud.user.scm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.interceptor.UserContextHolder;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonTaskRequest;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantUpdateDTO;
import com.csbr.qingcloud.user.scm.feign.CamundaFeign;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantService;
import com.csbr.qingcloud.user.scm.service.CamundaRegisterService;
import com.csbr.qingcloud.user.scm.service.MerchantService;
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
 * @date 2020/8/4 16:37
 * 商户注册流程
 */
@Service
@Slf4j
@Transactional
public class CamundaRegisterServiceImpl implements CamundaRegisterService {


    @Autowired
    private CamundaFeign camundaFeign;

    @Autowired
    private MfMerchantService mfMerchantService;

    @Autowired
    private MerchantService merchantService;

    /**
     * 流程初始化
     *
     * @param csbrCommonProcessRequest
     */
//    @Override
//    public CommonRes initProcess(CsbrCommonProcessRequest csbrCommonProcessRequest) {
//        JSONObject jsonObject = camundaFeign.initProcess(csbrCommonProcessRequest);
//        log.info(jsonObject.toJSONString());
//        if(jsonObject != null && jsonObject.get("code").equals("00000")){
//            JSONArray data = jsonObject.getJSONArray("data");
//            if(data != null && data.size() > 0){
//                 //获取任务id
//                JSONObject o = (JSONObject) data.get(0);
//                String taskId = o.getString("id");
//                //流程id
//                String processInstanceId = o.getString("processInstanceId");
//                //流程定义id
//                String processDefinitionId = o.getString("processDefinitionId");
//            }
//
//        }
//        return null;
//    }

    /**
     * 流程审批 通过/驳回 (商户注册)
     * @param processInstanceId
     * @param approvalState
     * @param rejectReason
     * @return
     */
    @Override
    @GlobalTransactional
    public CommonRes<Boolean> registerApproveProcess(String processInstanceId,String approvalState,String rejectReason) {
        boolean flag = false;
        CsbrCommonTaskRequest csbrCommonTaskRequest = new CsbrCommonTaskRequest();
        //查询最新的流程
        JSONObject hisTasksDetail = camundaFeign.getHisTasksDetail(processInstanceId);
        if(hisTasksDetail != null && hisTasksDetail.get("code").equals("00000")){
            JSONArray data = hisTasksDetail.getJSONArray("data");
            //获取最后一条记录
            if(data != null && data.size() > 0){
                JSONObject o = data.getJSONObject(data.size() - 1);
                if(StringUtils.isNotEmpty(o.getString("taskId"))){
                    //流程id
                    csbrCommonTaskRequest.setProcessInstanceId(processInstanceId);
                    //任务id
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

                        MfMerchant mfMerchant = new MfMerchant();
                        mfMerchant.setProcDefId(processInstanceId);
                        //任务id
                        if(StringUtils.isNotEmpty(o.getString("taskId"))){
                            mfMerchant.setTaskId(o.getString("taskId"));
                        }
                        //驳回原因
                        if(StringUtils.isNotEmpty(rejectReason)){
                            mfMerchant.setRejectReason(rejectReason);
                        }
                        mfMerchant.setApprovalState(approvalState);
                        LambdaUpdateWrapper<MfMerchant> mfMerchantLambdaUpdateWrapper = mfMerchantService.csbrUpdateWrapper(MfMerchant.class);
                        mfMerchantLambdaUpdateWrapper.eq(MfMerchant::getProcDefId,processInstanceId);
                        flag = mfMerchantService.update(mfMerchant, mfMerchantLambdaUpdateWrapper);
                        if(!flag){
                            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("流程(%s)更新失败，可能已在其他终端更新，请重试。", processInstanceId));
                        }
                    }
                }
            }
        }
        return CommonRes.success(flag);
    }

    /**
     * 查询流程详情
     *
     * @param processInstanceId
     */
    @Override
    public CommonRes getRegisterHisTasksDetail(String processInstanceId) {
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
     * 根据流程id修改本地数据库的状态(商户注册)
     *
     * @param processInstanceId
     */
//    @Override
//    @Transactional
//    public Boolean updateStateByprocessInstanceId(String processInstanceId,String state,String taskId,String rejectReason) {
//        MfMerchant mfMerchant = new MfMerchant();
//        mfMerchant.setProcDefId(processInstanceId);
//        //任务id
//        if(StringUtils.isNotEmpty(taskId)){
//            mfMerchant.setTaskId(taskId);
//        }
//        //驳回原因
//        if(StringUtils.isNotEmpty(rejectReason)){
//            mfMerchant.setRejectReason(rejectReason);
//        }
//        mfMerchant.setApprovalState(state);
//        LambdaUpdateWrapper<MfMerchant> mfMerchantLambdaUpdateWrapper = mfMerchantService.csbrUpdateWrapper(MfMerchant.class);
//        mfMerchantLambdaUpdateWrapper.eq(MfMerchant::getProcDefId,processInstanceId);
//        boolean update = mfMerchantService.update(mfMerchant, mfMerchantLambdaUpdateWrapper);
//        if(!update){
//            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("流程(%s)更新失败，可能已在其他终端更新，请重试。", processInstanceId));
//        }
//        return update;
//    }

    /**
     * 商户注册提交流程
     *
     * @param mfMerchant
     */
    @Override
    @Transactional
    public CommonRes registerStartProcess(MfMerchant mfMerchant) {
        String userInfo = UserContextHolder.get();
        String u = "admin";
        if(StringUtils.isEmpty(userInfo)){
            userInfo = u;
        }
        JSONObject jsonObject = camundaFeign.startProcess(mfMerchant.getProcDefId(), userInfo);
        if(jsonObject != null && jsonObject.get("code").equals("00000")){
            JSONArray data = jsonObject.getJSONArray("data");
            if(data != null && data.size() > 0){
                JSONObject object = data.getJSONObject(0);
                MerchantUpdateDTO merchantUpdateDTO = new MerchantUpdateDTO();
                //提交流程后修改状态
                merchantUpdateDTO.setGuid(mfMerchant.getGuid());
                //流程id
                merchantUpdateDTO.setProcDefId(object.getString("processInstanceId"));
                //提交流程后将本地数据库状态更改为待审核
                merchantUpdateDTO.setApprovalState("A");
                CommonRes<Boolean> booleanCommonRes = merchantService.updateMerchant(merchantUpdateDTO);
                return booleanCommonRes;
            }
        }
        return CommonRes.success("无此流程id",null);
    }

    /**
     * 商户注册审批查询
     *
     * @param obj
     */
    @Override
    public PageListVO getRegisterPageList(JSONObject obj) {
        PageListVO registerPageList = mfMerchantService.getRegisterPageList(obj);
        return registerPageList;
    }
}
