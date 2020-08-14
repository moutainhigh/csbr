package com.csbr.qingcloud.user.scm.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/8/5 17:19
 * 商户备案流程
 */
public interface CamundaFilingService {


    /**
     * 商户备案(初始化)
     */
    CommonRes initFilingProcess(MfMerchantFiling mfMerchantFiling);

    /**
     * 商户备案审批
     */
    CommonRes filingApproveProcess(String processInstanceId,String approvalState,String rejectReason);

    /**
     * 商户备案详情
     */
    CommonRes getFilingHisTasksDetail(String processInstanceId);

    /**
     * 商户备案查询
     */
    PageListVO getFilingPageList(JSONObject obj);
}
