package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.InvLotStockSapHistoryService;
import com.csbr.cloud.hy.server.service.InvLotStockWmsHistoryService;
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
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/ms-hy-server/invLotStockWmsHistory")
@Api(tags = {"wms历史库存"})
public class InvLotStockWmsHistoryController {

    @Autowired
    private InvLotStockWmsHistoryService invLotStockWmsHistoryService;

    /**
     * 根据仓库汇总查询库存
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-lot-stock-wms")
    @ApiOperation(value = "根据仓库汇总查询库存")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
    })
    public CommonRes getLotStockWms(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = true) String warehouseGuid,
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
            params.put("warehouseGuid",warehouseGuid);
            PageInfo<Map<String, Object>> lotStockWms = invLotStockWmsHistoryService.getLotStockWms(params);
            return CommonRes.ok("sucess",lotStockWms);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

    /**
     * 查询仓库库存明细
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-lot-stock-wms-history")
    @ApiOperation(value = "查询仓库库存明细")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsCode", dataType = "String", value = "商品编号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = true, name = "manufacturer", dataType = "String", value = "生产厂商"),
            @ApiImplicitParam(paramType = "query", required = true, name = "lot", dataType = "String", value = "批号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "storageDate", dataType = "String", value = "入库时间"),
    })
    public CommonRes getLotStockWmsHistory(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = true) String warehouseGuid,
            @RequestParam(value = "goodsCode",required = true) String goodsCode,
            @RequestParam(value = "goodsName",required = true) String goodsName,
            @RequestParam(value = "manufacturer",required = true) String manufacturer,
            @RequestParam(value = "lot",required = true) String lot,
            @RequestParam(value = "storageDate",required = true) String storageDate,
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
            CommonRes commonRes = invLotStockWmsHistoryService.getLotStockWmsHistory(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

    /**
     * 根据商品汇总查询库存
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-wms-stock-by-goods")
    @ApiOperation(value = "根据商品汇总查询库存")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = true, name = "warehouseGuid", dataType = "String", value = "仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsCode", dataType = "String", value = "商品编号"),
            @ApiImplicitParam(paramType = "query", required = true, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = true, name = "manufacturer", dataType = "String", value = "生产厂商"),
            @ApiImplicitParam(paramType = "query", required = true, name = "createTime", dataType = "String", value = "存储日期"),
    })
    public CommonRes getWmsStockByGoods(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = true) String warehouseGuid,
            @RequestParam(value = "goodsCode",required = true) String goodsCode,
            @RequestParam(value = "goodsName",required = true) String goodsName,
            @RequestParam(value = "manufacturer",required = true) String manufacturer,
            @RequestParam(value = "createTime",required = true) String createTime,
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
            params.put("createTime",createTime);
            CommonRes commonRes = invLotStockWmsHistoryService.getWmsStockByGoods(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }
    }

}

