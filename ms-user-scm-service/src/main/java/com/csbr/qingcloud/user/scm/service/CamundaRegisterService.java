package com.csbr.qingcloud.user.scm.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;

/**
 * @author zhangheng
 * @date 2020/8/4 16:27
 * 商户注册流程
 */
public interface CamundaRegisterService {

    /**
     * 流程初始化
     */
//    CommonRes initProcess(CsbrCommonProcessRequest csbrCommonProcessRequest);

    /**
     * 流程审批 (通过 驳回)
     */
    CommonRes registerApproveProcess(String processInstanceId,String approvalState,String rejectReason);

    /**
     * 查询流程详情(商户注册)
     */
    CommonRes getRegisterHisTasksDetail(String processInstanceId);

    /**
     * 根据流程id修改本地数据库的状态
     */
//    Boolean updateStateByprocessInstanceId(String processInstanceId,String state,String taskId,String rejectReason);

    /**
     * 商户注册提交流程
     */
    CommonRes registerStartProcess(MfMerchant mfMerchant);

    /**
     * 商户注册审批查询
     */
    PageListVO getRegisterPageList(JSONObject obj);

}
