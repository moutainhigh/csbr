package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.vo.TrTplpoMainVo;
import com.csbr.cloud.hy.server.service.TrTplbbDetailService;
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
@RequestMapping("/ms-hy-server")
@Api(tags = {"订单明细"})
public class TrTplbbDetailController {

    @Autowired
    private TrTplbbDetailService trTplbbDetailService;

    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-bbdetail-select")
    @ApiOperation(value = "订单明细表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "guid", dataType = "String", value = "业务汇总表GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "rowNo", dataType = "String", value = "行号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "bBillNo", dataType = "String", value = "业务单号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsGuid", dataType = "String", value = "商品GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsCode", dataType = "String", value = "商品编码"),
            @ApiImplicitParam(paramType = "query", required = false, name = "lot", dataType = "String", value = "批号"),
    })
    public CommonRes getBBDetailSelect(
            @RequestParam(value = "guid",required = true) String guid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "rowNo",required = false) String rowNo,
            @RequestParam(value = "bBillNo",required = false) String bBillNo,
            @RequestParam(value = "goodsGuid",required = false) String goodsGuid,
            @RequestParam(value = "goodsCode",required = false) String goodsCode,
            @RequestParam(value = "lot",required = false) String lot,
            HttpServletRequest request
    ){
        try {
            JSONObject params = new JSONObject();
            params.put("guid",guid);
            params.put("pageIndex",pageIndex);
            params.put("pageSize",pageSize);
            params.put("rowNo",rowNo);
            params.put("bBillNo",bBillNo);
            params.put("goodsGuid",goodsGuid);
            params.put("goodsCode",goodsCode);
            params.put("lot",lot);
            TrTplpoMainVo bbDetailSelect = trTplbbDetailService.getBBDetailSelect(params);
            return CommonRes.ok("sucess",bbDetailSelect);
        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }


}

