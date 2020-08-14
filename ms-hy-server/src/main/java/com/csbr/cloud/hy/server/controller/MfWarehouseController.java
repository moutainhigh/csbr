package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfWarehouseEntity;
import com.csbr.cloud.hy.server.service.MfWarehouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  仓库资料
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/ms-hy-server/warehouse")
@Api(tags = {"仓库资料"})
public class MfWarehouseController {

    @Autowired
    private MfWarehouseService mfWarehouseService;

    /**
     * 新增或修改仓库资料
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-warehouse-operation")
    @ApiOperation(value = "新增或修改仓库资料")
    public CommonRes postWareHouseOperation(@RequestBody MfWarehouseEntity mfWarehouseEntity){
        CommonRes commonRes = null;
        try {
            commonRes = mfWarehouseService.postWareHouseOperation(mfWarehouseEntity);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }

    }

    /**
     * 查询仓库资料信息
     */
    @SentinelResource(value = "sentile-rule")
    @GetMapping("get-warehouse-select")
    @ApiOperation(value = "查询仓库资料信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "tenantGuid", dataType = "String", value = "租户信息"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageIndex", dataType = "int", value = "当前页"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pageSize", dataType = "int", value = "每页显示数"),
            @ApiImplicitParam(paramType = "query", required = false, name = "guid", dataType = "String", value = "仓库主键"),
            @ApiImplicitParam(paramType = "query", required = false, name = "chineseName", dataType = "String", value = "仓库名称"),
    })
    public CommonRes getWareHouseSelect(
            @RequestParam(value = "tenantGuid",required = true) String tenantGuid,
            @RequestParam(value = "pageIndex",required = true) Integer pageIndex,
            @RequestParam(value = "pageSize",required = true) Integer pageSize,
            @RequestParam(value = "guid",required = false) String guid,
            @RequestParam(value = "goodsCode",required = false) String chineseName,
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
        params.put("guid",guid);
        params.put("chineseName",chineseName);

        CommonRes wareHouseSelect = mfWarehouseService.getWareHouseSelect(params);
        return wareHouseSelect;
    }


}

