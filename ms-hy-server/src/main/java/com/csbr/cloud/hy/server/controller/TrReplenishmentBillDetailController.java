package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.service.TrReplenishmentBillDetailService;
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
@RequestMapping("/ms-hy-server/trReplenishmentBillDetail")
@Api(tags = {"补货单明细"})
public class TrReplenishmentBillDetailController {

    @Autowired
    private TrReplenishmentBillDetailService trReplenishmentBillDetailService;

    /**
     * 补货单明细查询
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-replenishment-bill-detail-select")
    @ApiOperation(value = "补货单明细查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "guid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "rowNo", dataType = "String", value = "行号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsGuid", dataType = "String", value = "商品GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsCode", dataType = "String", value = "商品编码"),
    })
    public CommonRes getReplenishmentBillDetailSelect(
            @RequestParam(value = "guid",required = true) String guid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "rowNo",required = false) String rowNo,
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
            params.put("rowNo",rowNo);
            params.put("goodsGuid",goodsGuid);
            params.put("goodsCode",goodsCode);
            CommonRes commonRes = trReplenishmentBillDetailService.getReplenishmentBillDetailSelect(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }

}

