package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrTplpoMainDto;
import com.csbr.cloud.hy.server.service.TrTplpoMainService;
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
 *  物流相关
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server/tpl")
@Api(tags = {"物流订单接口"})
public class TrTplpoMainController {

    @Autowired
    private TrTplpoMainService trTplpoMainService;


    /**
     * 新增物流订单
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-poMain-insert")
    @ApiOperation(value = "新增物流订单")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", required = true, name = "trTplpoMainDto", dataType = "TrTplpoMainDto", value = "物流订单信息"),
//    })
    public CommonRes postPoMainInsert(@RequestBody TrTplpoMainDto trTplpoMainDto){

        try {
            trTplpoMainService.postPoMainInsert(trTplpoMainDto);
            return CommonRes.ok("success",null);

        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }

    /**
     * 物流订单主表信息查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-poMain-select")
    @ApiOperation(value = "物流订单主表信息查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "tplPoNo", dataType = "String", value = "物流单号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "mainPoGuid", dataType = "String", value = "物流单GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "bBillNo", dataType = "String", value = "业务单号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "businessType", dataType = "String", value = "单据类型"),
            @ApiImplicitParam(paramType = "query", required = false, name = "departureAddress", dataType = "String", value = "库存地点"),
            @ApiImplicitParam(paramType = "query", required = false, name = "saleCorpGuid", dataType = "String", value = "收货单位GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "supplyCorpName", dataType = "String", value = "收货单位"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverGuid", dataType = "String", value = "承运商GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverName", dataType = "String", value = "承运商"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseName", dataType = "String", value = "仓库名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "routeGuid", dataType = "String", value = "配送路线GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "routeName", dataType = "String", value = "配送路线名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "mainState", dataType = "String", value = "物流订单主表状态"),
    })
    public CommonRes getPoMainSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "tplPoNo",required = false) String tplPoNo,
            @RequestParam(value = "mainPoGuid",required = false) String mainPoGuid,
            @RequestParam(value = "bBillNo",required = false) String bBillNo,
            @RequestParam(value = "businessType",required = false) String businessType,
            @RequestParam(value = "departureAddress",required = false) String departureAddress,
            @RequestParam(value = "saleCorpGuid",required = false) String saleCorpGuid,
            @RequestParam(value = "supplyCorpName",required = false) String supplyCorpName,
            @RequestParam(value = "deliverGuid",required = false) String deliverGuid,
            @RequestParam(value = "deliverName",required = false) String deliverName,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            @RequestParam(value = "warehouseGuid",required = false) String warehouseGuid,
            @RequestParam(value = "warehouseName",required = false) String warehouseName,
            @RequestParam(value = "routeGuid",required = false) String routeGuid,
            @RequestParam(value = "routeName",required = false) String routeName,
            @RequestParam(value = "mainState",required = false) String mainState,
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

            params.put("tplPoNo",tplPoNo);
            params.put("mainPoGuid",mainPoGuid);
            params.put("bBillNo",bBillNo);
            params.put("businessType",businessType);
            params.put("departureAddress",departureAddress);
            params.put("saleCorpGuid",saleCorpGuid);
            params.put("supplyCorpName",supplyCorpName);
            params.put("deliverGuid",deliverGuid);
            params.put("deliverName",deliverName);
            params.put("cargoOwnerGuid",cargoOwnerGuid);
            params.put("cargoOwnerName",cargoOwnerName);
            params.put("warehouseGuid",warehouseGuid);
            params.put("warehouseName",warehouseName);
            params.put("routeGuid",routeGuid);
            params.put("routeName",routeName);
            params.put("mainState",mainState);

            PageInfo<Map<String, Object>> poMainSelect = trTplpoMainService.getPoMainSelect(params);

            return CommonRes.ok("sucess",poMainSelect);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }

    /**
     * 订单节点监控
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-order-monitoring")
    @ApiOperation(value = "订单节点监控")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "tplPoNo", dataType = "String", value = "物流单号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "mainPoGuid", dataType = "String", value = "物流单GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "bBillNo", dataType = "String", value = "业务单号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "departureAddress", dataType = "String", value = "库存地点"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverGuid", dataType = "String", value = "承运商GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverName", dataType = "String", value = "承运商"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseName", dataType = "String", value = "仓库名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "routeGuid", dataType = "String", value = "配送路线GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "routeName", dataType = "String", value = "配送路线名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "mainState", dataType = "String", value = "物流订单主表状态"),
    })
    public CommonRes getOrderMonitoring(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "tplPoNo",required = false) String tplPoNo,
            @RequestParam(value = "mainPoGuid",required = false) String mainPoGuid,
            @RequestParam(value = "bBillNo",required = false) String bBillNo,
            @RequestParam(value = "departureAddress",required = false) String departureAddress,
            @RequestParam(value = "deliverGuid",required = false) String deliverGuid,
            @RequestParam(value = "deliverName",required = false) String deliverName,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            @RequestParam(value = "warehouseGuid",required = false) String warehouseGuid,
            @RequestParam(value = "warehouseName",required = false) String warehouseName,
            @RequestParam(value = "routeGuid",required = false) String routeGuid,
            @RequestParam(value = "routeName",required = false) String routeName,
            @RequestParam(value = "mainState",required = false) String mainState,
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

            params.put("tplPoNo",tplPoNo);
            params.put("mainPoGuid",mainPoGuid);
            params.put("bBillNo",bBillNo);
            params.put("departureAddress",departureAddress);
            params.put("deliverGuid",deliverGuid);
            params.put("deliverName",deliverName);
            params.put("cargoOwnerGuid",cargoOwnerGuid);
            params.put("cargoOwnerName",cargoOwnerName);
            params.put("warehouseGuid",warehouseGuid);
            params.put("warehouseName",warehouseName);
            params.put("routeGuid",routeGuid);
            params.put("routeName",routeName);
            params.put("mainState",mainState);

            Map<String, Object> orderMonitoring = trTplpoMainService.getOrderMonitoring(params);

            return CommonRes.ok("sucess",orderMonitoring);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

    /**
     * 提货超时、到站/签收超时订单
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-timeout-order-select")
    @ApiOperation(value = "提货超时、到站/签收超时订单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "orderType", dataType = "String", value = "订单类型  1：提货超时订单  2：到货签收超时订单"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverGuid", dataType = "String", value = "承运商GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "deliverName", dataType = "String", value = "承运商名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "startTime", dataType = "String", value = "开始时间yyyy-MM"),
            @ApiImplicitParam(paramType = "query", required = false, name = "endTime", dataType = "String", value = "结束时间yyyy-MM"),
    })
    public  CommonRes getTimeoutOrderSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "orderType",required = true) String orderType,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            @RequestParam(value = "deliverGuid",required = false) String deliverGuid,
            @RequestParam(value = "deliverName",required = false) String deliverName,
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

            params.put("orderType",orderType);
            params.put("cargoOwnerGuid",cargoOwnerGuid);
            params.put("cargoOwnerName",cargoOwnerName);
            params.put("deliverGuid",deliverGuid);
            params.put("deliverName",deliverName);
            params.put("startTime",startTime);
            params.put("endTime",endTime);

            PageInfo<Map<String, Object>> timeoutOrderSelect = trTplpoMainService.getTimeoutOrderSelect(params);

            return CommonRes.ok("sucess",timeoutOrderSelect);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

}

