package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.TrOutBoundResultDetailService;
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
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/ms-hy-server/trOutBoundResultDetail")
@Api(tags = {"出库明细"})
public class TrOutBoundResultDetailController {

    @Autowired
    private TrOutBoundResultDetailService trOutBoundResultDetailService;

    /**
     * 出库历史汇总查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-out-bound-result-select")
    @ApiOperation(value = "出库历史汇总查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseGuid", dataType = "String", value = "所属仓库GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "warehouseName", dataType = "String", value = "所属仓库名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "bBillNo", dataType = "String", value = "业务单据编号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "businessType", dataType = "String", value = "业务单据类型"),
    })
    public CommonRes getOutBoundResultSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "warehouseGuid",required = false) String warehouseGuid,
            @RequestParam(value = "warehouseName",required = false) String warehouseName,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            @RequestParam(value = "bBillNo",required = false) String bBillNo,
            @RequestParam(value = "businessType",required = false) String businessType,
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
            params.put("warehouseName",warehouseName);
            params.put("cargoOwnerGuid",cargoOwnerGuid);
            params.put("cargoOwnerName",cargoOwnerName);
            params.put("bBillNo",bBillNo);
            params.put("businessType",businessType);
            Map<String, Object> outBoundResultSelect = trOutBoundResultDetailService.getOutBoundResultSelect(params);
            return CommonRes.ok("success",outBoundResultSelect);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }


    /**
     *  出库历史明细查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-out-bound-result-detail-select")
    @ApiOperation(value = "出库历史明细查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "guid", dataType = "String", value = "主表GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "rowNo", dataType = "String", value = "行号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsGuid", dataType = "String", value = "商品GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsCode", dataType = "String", value = "商品编码"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "lot", dataType = "String", value = "批号"),
    })
    public CommonRes getInBoundResultDetailSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "guid",required = false) String guid,
            @RequestParam(value = "rowNo",required = false) String rowNo,
            @RequestParam(value = "goodsGuid",required = false) String goodsGuid,
            @RequestParam(value = "goodsCode",required = false) String goodsCode,
            @RequestParam(value = "goodsName",required = false) String goodsName,
            @RequestParam(value = "lot",required = false) String lot,
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
            params.put("guid",guid);
            params.put("rowNo",rowNo);
            params.put("goodsGuid",goodsGuid);
            params.put("goodsCode",goodsCode);
            params.put("goodsName",goodsName);
            params.put("lot",lot);
            Map<String, Object> outBoundResultDetailSelect = trOutBoundResultDetailService.getOutBoundResultDetailSelect(params);
            return CommonRes.ok("success",outBoundResultDetailSelect);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }

}

