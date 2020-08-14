package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.InvLotStockWmsService;
import com.github.pagehelper.PageInfo;
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
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server/invLotStockWms")
@Api(tags = {"wms库存"})
public class InvLotStockWmsController {

    @Autowired
    private InvLotStockWmsService invLotStockWmsService;

    /**
     * 库存差异查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-sales-accuracy")
    @ApiOperation(value = "查询计划销量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsCode", dataType = "String", value = "商品编号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = true, name = "manufacturer", dataType = "String", value = "生产厂商"),
            @ApiImplicitParam(paramType = "query", required = true, name = "lot", dataType = "String", value = "批号"),
    })
    public CommonRes getStockDifference(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = true) String warehouseGuid,
            @RequestParam(value = "goodsCode",required = true) String goodsCode,
            @RequestParam(value = "goodsName",required = true) String goodsName,
            @RequestParam(value = "manufacturer",required = true) String manufacturer,
            @RequestParam(value = "lot",required = true) String lot,
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
            params.put("goodsCode",goodsCode);
            params.put("warehouseGuid",warehouseGuid);
            params.put("goodsName",goodsName);
            params.put("manufacturer",manufacturer);
            params.put("lot",lot);
            PageInfo<Map<String, Object>> stockDifference = invLotStockWmsService.getStockDifference(params);
            return CommonRes.ok("sucess",stockDifference);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

    /**
     * 滞销库存查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-unsalable-stock")
    @ApiOperation(value = "滞销库存查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsCode", dataType = "String", value = "商品编号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = true, name = "manufacturer", dataType = "String", value = "生产厂商"),
            @ApiImplicitParam(paramType = "query", required = true, name = "lot", dataType = "String", value = "批号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "storageDate", dataType = "String", value = "入库时间"),
            @ApiImplicitParam(paramType = "query", required = true, name = "stockAge", dataType = "String", value = "库龄"),
    })
    public CommonRes getUnsalableStock(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = true) String warehouseGuid,
            @RequestParam(value = "goodsCode",required = true) String goodsCode,
            @RequestParam(value = "goodsName",required = true) String goodsName,
            @RequestParam(value = "manufacturer",required = true) String manufacturer,
            @RequestParam(value = "lot",required = true) String lot,
            @RequestParam(value = "storageDate",required = false) String storageDate,
            @RequestParam(value = "stockAge",required = true) String stockAge,
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
            params.put("goodsCode",goodsCode);
            params.put("warehouseGuid",warehouseGuid);
            params.put("goodsName",goodsName);
            params.put("manufacturer",manufacturer);
            params.put("lot",lot);
            params.put("storageDate",storageDate);
            params.put("stockAge",stockAge);
            PageInfo<Map<String, Object>> unsalableStock = invLotStockWmsService.getUnsalableStock(params);
            return CommonRes.ok("sucess",unsalableStock);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }
}

