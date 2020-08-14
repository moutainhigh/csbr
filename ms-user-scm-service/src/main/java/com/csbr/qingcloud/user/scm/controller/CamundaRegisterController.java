package com.csbr.qingcloud.user.scm.controller;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.csbr.qingcloud.user.scm.service.CamundaFilingService;
import com.csbr.qingcloud.user.scm.service.CamundaRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangheng
 * @date 2020/8/4 16:24
 */
@RestController
@RequestMapping("/camunda-register")
@Api(tags = "商户注册工作流相关接口")
@Slf4j
public class CamundaRegisterController {

    @Autowired
    private CamundaRegisterService camundaRegisterService;


    /**
     * 商户注册审批通过/驳回
     */
    @ApiOperation(value = "商户注册审批", notes = "商户注册审批")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "processInstanceId", dataType = "String", value = "流程id"),
            @ApiImplicitParam(paramType = "query", required = true, name = "approvalState", dataType = "String", value = "审批状态(Y:通过 R:驳回)"),
            @ApiImplicitParam(paramType = "query", required = false, name = "rejectReason", dataType = "String", value = "驳回原因"),
    })
    @PostMapping(value = "/registerApproveProcess")
    public CommonRes<Boolean> registerApproveProcess(
            @RequestParam(value = "processInstanceId", required = true) String processInstanceId,
            @RequestParam(value = "approvalState", required = true) String approvalState,
            @RequestParam(value = "rejectReason", required = false) String rejectReason,
            HttpServletRequest request
    ){
        try {
            CommonRes commonRes = camundaRegisterService.registerApproveProcess(processInstanceId, approvalState, rejectReason);
            return commonRes;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

    /**
     * 商户注册提交
     */
    @ApiOperation(value = "商户注册提交", notes = "商户注册提交")
    @PostMapping(value = "/registerStartProcess")
    public CommonRes registerStartProcess(@RequestBody MfMerchant mfMerchant){
        try {
            CommonRes commonRes = camundaRegisterService.registerStartProcess(mfMerchant);
            return commonRes;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }

    }

    /**
     * 商户注册流程详情
     */
    @ApiOperation(value = "商户注册流程详情", notes = "商户注册流程详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "processInstanceId", dataType = "String", value = "流程id"),
    })
    @GetMapping(value = "getRegisterHisTasksDetail")
    public CommonRes getRegisterHisTasksDetail(
            @RequestParam(value = "processInstanceId", required = true) String processInstanceId,
            HttpServletRequest request
    ){
        try {
            CommonRes registerHisTasksDetail = camundaRegisterService.getRegisterHisTasksDetail(processInstanceId);
            return registerHisTasksDetail;
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

    /**
     * 商户注册审批查询
     */
    @ApiOperation(value = "商户注册审批查询", notes = "商户注册审批查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "merchantName", dataType = "String", value = "商户"),
            @ApiImplicitParam(paramType = "query", required = false, name = "manager", dataType = "String", value = "人员"),
            @ApiImplicitParam(paramType = "query", required = false, name = "isFreeMan", dataType = "String", value = "来源"),
            @ApiImplicitParam(paramType = "query", required = false, name = "province", dataType = "String", value = "省"),
            @ApiImplicitParam(paramType = "query", required = false, name = "city", dataType = "String", value = "市"),
            @ApiImplicitParam(paramType = "query", required = false, name = "district", dataType = "String", value = "区"),
            @ApiImplicitParam(paramType = "query", required = false, name = "approvalState", dataType = "String", value = "审批状态"),
            @ApiImplicitParam(paramType = "query", required = false, name = "startTime", dataType = "String", value = "开始时间yyyy-MM"),
            @ApiImplicitParam(paramType = "query", required = false, name = "endTime", dataType = "String", value = "结束时间yyyy-MM"),
    })
    @PostMapping(value = "/getRegisterPageList")
    public CommonRes getRegisterPageList(
            @RequestParam(value = "pageIndex", required = true) Integer pageIndex,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "merchantName", required = false) String merchantName,
            @RequestParam(value = "manager", required = false) String manager,
            @RequestParam(value = "isFreeMan", required = false) String isFreeMan,
            @RequestParam(value = "province", required = false) String province,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "district", required = false) String district,
            @RequestParam(value = "approvalState", required = false) String approvalState,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            HttpServletRequest request
    ){
        try {
            JSONObject params = new JSONObject();
            if(pageIndex > 0){
                params.put("pageIndex",pageIndex);
            }else{
                params.put("pageIndex",1);
            }
            if(pageSize > 0){
                params.put("pageSize",pageSize);
            }else{
                params.put("pageSize",10);
            }
            params.put("merchantName",merchantName);
            params.put("manager",manager);
            params.put("isFreeMan",isFreeMan);
            params.put("province",province);
            params.put("city",city);
            params.put("district",district);
            params.put("approvalState",approvalState);
            params.put("startTime",startTime);
            params.put("endTime",endTime);
            PageListVO registerPageList = camundaRegisterService.getRegisterPageList(params);
            return CommonRes.success(registerPageList);
        }catch (Exception e){
            log.error(e.getMessage());
            return CommonRes.ok("B0001","接口异常!",null);
        }
    }

}
