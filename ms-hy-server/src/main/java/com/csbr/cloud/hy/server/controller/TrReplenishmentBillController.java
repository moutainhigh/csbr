package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.TrReplenishmentBillService;
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
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/ms-hy-server/trReplenishmentBill")
@Api(tags = {"补货单"})
public class TrReplenishmentBillController {

    @Autowired
    private TrReplenishmentBillService trReplenishmentBillService;

    /**
     * 补货单表查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-replenishment-bill-select")
    @ApiOperation(value = "补货单表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "transferWHName", dataType = "String", value = "缺货仓库"),
            @ApiImplicitParam(paramType = "query", required = false, name = "receivingAddress", dataType = "String", value = "收货地址"),
            @ApiImplicitParam(paramType = "query", required = false, name = "state", dataType = "String", value = "状态"),
            @ApiImplicitParam(paramType = "query", required = false, name = "startTime", dataType = "String", value = "开始时间yyyy-MM"),
            @ApiImplicitParam(paramType = "query", required = false, name = "endTime", dataType = "String", value = "结束时间yyyy-MM"),
    })
    public CommonRes getReplenishmentBillSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "transferWHName",required = false) String transferWHName,
            @RequestParam(value = "receivingAddress",required = false) String receivingAddress,
            @RequestParam(value = "state",required = false) String state,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime,
            HttpServletRequest request
    ){

        try {
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
            params.put("transferWHName",transferWHName);
            params.put("receivingAddress",receivingAddress);
            params.put("state",state);
            params.put("startTime",startTime);
            params.put("endTime",endTime);
            CommonRes commonRes = trReplenishmentBillService.getReplenishmentBillSelect(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }
}

