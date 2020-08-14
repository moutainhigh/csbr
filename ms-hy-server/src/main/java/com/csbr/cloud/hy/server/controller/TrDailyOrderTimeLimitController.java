package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.TrDailyOrderTimeLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server/daily-order-time-limit")
@Api(tags = {"订单时效"})
public class TrDailyOrderTimeLimitController {

    @Autowired
    private TrDailyOrderTimeLimitService trDailyOrderTimeLimitService;

    /**
     * 订单执行时效查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-daily-order-time-limit-select")
    @ApiOperation(value = "订单执行时效查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverGuid", dataType = "String", value = "承运商GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverName", dataType = "String", value = "承运商名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "startTime", dataType = "String", value = "开始时间yyyy-MM"),
            @ApiImplicitParam(paramType = "query", required = false, name = "endTime", dataType = "String", value = "结束时间yyyy-MM"),
    })
    public CommonRes getDailyOrderTimeLimitSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            @RequestParam(value = "deliverGuid",required = false) String deliverGuid,
            @RequestParam(value = "deliverName",required = false) String deliverName,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime,
            HttpServletRequest request
    ){
        JSONObject params = new JSONObject();
        params.put("tenantGuid",tenantGuid);
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
        params.put("cargoOwnerGuid",cargoOwnerGuid);
        params.put("cargoOwnerName",cargoOwnerName);
        params.put("deliverGuid",deliverGuid);
        params.put("deliverName",deliverName);
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        CommonRes commonRes = trDailyOrderTimeLimitService.getDailyOrderTimeLimitSelect(params);
        return commonRes;
    }




}

