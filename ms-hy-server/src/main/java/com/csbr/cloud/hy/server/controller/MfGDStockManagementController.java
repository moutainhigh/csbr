package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGDStockManagementEntity;
import com.csbr.cloud.hy.server.service.MfGDStockManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@RestController
@RequestMapping("/ms-hy-server/mfGDStockManagement")
@Api(tags = {"商品库管参数配置"})
public class MfGDStockManagementController {

    @Autowired
    private MfGDStockManagementService mfGDStockManagementService;

    /**
     * 商品库管参数配置新增修改
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-stock-management-operation")
    @ApiOperation(value = "商品库管参数配置新增修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", required = true, name = "mfGDStockManagementEntity", dataType = "mfGDStockManagementEntity", value = "商品库管参数配置信息"),
    })
    public CommonRes postStockManagementOperation(@RequestBody MfGDStockManagementEntity mfGDStockManagementEntity){

        try {
            CommonRes commonRes = mfGDStockManagementService.postStockManagementOperation(mfGDStockManagementEntity);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }
    }

}

