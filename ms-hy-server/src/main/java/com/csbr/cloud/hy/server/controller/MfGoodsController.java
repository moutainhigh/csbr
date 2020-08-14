package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGoodsEntity;
import com.csbr.cloud.hy.server.service.MfGoodsService;
import com.csbr.cloud.hy.server.util.BusinessData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server/goods")
@Api(tags = {"商品资料"})
public class MfGoodsController {

    @Autowired
    private MfGoodsService mfGoodsService;

    /**
     * 新增或修改商品信息
     * @param mfGoodsEntity
     * @return
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-goods-operation")
    @ApiOperation(value = "新增或修改商品资料")
    public CommonRes postGoodsOperation(@RequestBody MfGoodsEntity mfGoodsEntity){
        CommonRes commonRes = null;
        try {
            commonRes = mfGoodsService.postGoodsOperation(mfGoodsEntity);
            return commonRes;
        }catch (Exception e){
//            commonRes.setMsg(e.getMessage());
//            commonRes.setAt(new Date().getTime()+"");
            return CommonRes.ok(e.getMessage());
        }

    }

    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-goods-select")
    @ApiOperation(value = "查询商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "guid", dataType = "String", value = "商品主键"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsCode", dataType = "String", value = "商品编号"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsName", dataType = "String", value = "商品名称"),
            @ApiImplicitParam(paramType = "query", required = false, name = "goodsSpec", dataType = "String", value = "规格"),
            @ApiImplicitParam(paramType = "query", required = false, name = "helpCode", dataType = "String", value = "助记码"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerGuid", dataType = "String", value = "所属货主GUID"),
            @ApiImplicitParam(paramType = "query", required = false, name = "cargoOwnerName", dataType = "String", value = "所属货主名称"),
    })
    public CommonRes getGoodsSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "guid",required = false) String guid,
            @RequestParam(value = "goodsCode",required = false) String goodsCode,
            @RequestParam(value = "goodsName",required = false) String goodsName,
            @RequestParam(value = "goodsSpec",required = false) String goodsSpec,
            @RequestParam(value = "helpCode",required = false) String helpCode,
            @RequestParam(value = "cargoOwnerGuid",required = false) String cargoOwnerGuid,
            @RequestParam(value = "cargoOwnerName",required = false) String cargoOwnerName,
            HttpServletRequest request
    ){
        CommonRes commonRes = null;
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
            params.put("goodsCode",goodsCode);
            params.put("goodsName",goodsName);
            params.put("goodsSpec",goodsSpec);
            params.put("helpCode",helpCode);
            params.put("cargoOwnerGuid",cargoOwnerGuid);
            params.put("cargoOwnerName",cargoOwnerName);
            commonRes = mfGoodsService.getGoodsSelect(params);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }

    }

}

