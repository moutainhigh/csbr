package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrPlanSalesDto;
import com.csbr.cloud.hy.server.service.TrPlanSalesService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/ms-hy-server/trPlanSales")
@Api(tags = {"计划销量"})
public class TrPlanSalesController {

    @Autowired
    private TrPlanSalesService trPlanSalesService;


    /**
     * 预期销量设置新增修改
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-plan-sales-operation")
    @ApiOperation(value = "预期销量设置新增修改")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", required = true, name = "trPlanSalesDto", dataType = "trPlanSalesDto", value = "计划销量信息"),
//    })
    public CommonRes postPlanSalesOperation(@RequestBody TrPlanSalesDto trPlanSalesDto){
        CommonRes commonRes = null;
        try {
            commonRes = trPlanSalesService.postPlanSalesOperation(trPlanSalesDto);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }

        return commonRes;
    }

    /**
     * 预期销量设置查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-plan-sales-select")
    @ApiOperation(value = "预期销量设置查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "logAreaGuid", dataType = "String", value = "物流区域GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "wareHouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deptGuid", dataType = "String", value = "销售部门GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deptName", dataType = "String", value = "销售部门名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "planYear", dataType = "String", value = "计划年度"),
            @ApiImplicitParam(paramType = "query", required = false, name = "status", dataType = "String", value = "状态"),
    })
    public CommonRes getPlanSalesSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "logAreaGuid",required = false) String logAreaGuid,
            @RequestParam(value = "wareHouseGuid",required = false) String wareHouseGuid,
            @RequestParam(value = "deptGuid",required = false) String deptGuid,
            @RequestParam(value = "deptName",required = false) String deptName,
            @RequestParam(value = "planYear",required = false) String planYear,
            @RequestParam(value = "status",required = false) String status,
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
            params.put("logAreaGuid",logAreaGuid);
            params.put("wareHouseGuid",wareHouseGuid);
            params.put("deptGuid",deptGuid);
            params.put("deptName",deptName);
            params.put("planYear",planYear);
            params.put("status",status);
            CommonRes commonRes = trPlanSalesService.getPlanSalesSelect(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }

    /**
     * 预期销量明细查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-plan-sales-detail-select")
    @ApiOperation(value = "预期销量明细查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "guid", dataType = "String", value = "主表guid"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsGuid", dataType = "String", value = "商品GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsCode", dataType = "String", value = "商品编码"),
    })
    public CommonRes getPlanSalesDetailSelect(
            @RequestParam(value = "guid",required = true) String guid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "goodsGuid",required = false) String goodsGuid,
            @RequestParam(value = "goodsCode",required = false) String goodsCode,
            HttpServletRequest request
    ){
        try {
            JSONObject params = new JSONObject();
            params.put("guid",guid);
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
            params.put("goodsGuid",goodsGuid);
            params.put("goodsCode",goodsCode);
            CommonRes commonRes = trPlanSalesService.getPlanSalesDetailSelect(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }

    /**
     * 查询计划销量
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-sales-accuracy")
    @ApiOperation(value = "查询计划销量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "guid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsGuid", dataType = "String", value = "商品GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "wareHouseGuid", dataType = "String", value = "仓库GUID"),
    })
    public CommonRes getSalesAccuracy(
            @RequestParam(value = "guid",required = true) String guid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "goodsGuid",required = true) String goodsGuid,
            @RequestParam(value = "wareHouseGuid",required = true) String wareHouseGuid,
            HttpServletRequest request
    ){
        try {
            JSONObject params = new JSONObject();
            params.put("guid",guid);
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
            params.put("goodsGuid",goodsGuid);
            params.put("wareHouseGuid",wareHouseGuid);
            PageInfo<Map<String, Object>> salesAccuracy = trPlanSalesService.getSalesAccuracy(params);
            return CommonRes.ok("sucess",salesAccuracy);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }
}

