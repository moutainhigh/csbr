package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGoodsEntity;
import com.csbr.cloud.hy.server.entity.TrBbTracingEntity;
import com.csbr.cloud.hy.server.service.TrBbTracingService;
import io.swagger.annotations.Api;
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
 * @author L-ZP
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server")
@Api(tags = {"物流执行跟踪"})
public class TrBbTracingController {


    @Autowired
    private TrBbTracingService trBbTracingService;

    /**
     * 新增物流执行跟踪
     * @param trBbTracingEntity
     * @return
     */
    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-bbtracing-insert")
    @ApiOperation(value = "新增物流执行跟踪")
    public CommonRes postBBTracingInsert(@RequestBody TrBbTracingEntity trBbTracingEntity){
        CommonRes commonRes = null;
        try {
            commonRes = trBbTracingService.postBBTracingInsert(trBbTracingEntity);
            return commonRes;
        }catch (Exception e){
            return CommonRes.ok(e.getMessage());
        }

    }
}

