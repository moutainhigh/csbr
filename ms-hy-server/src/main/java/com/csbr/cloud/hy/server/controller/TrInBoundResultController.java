package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.InBoundResultInsertDto;
import com.csbr.cloud.hy.server.domain.dto.TrTplpoMainDto;
import com.csbr.cloud.hy.server.service.TrInBoundResultService;
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
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/ms-hy-server/trInBoundResult")
@Api(tags = {"入库"})
public class TrInBoundResultController {

    @Autowired
    private TrInBoundResultService trInBoundResultService;

    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-in-bound-result-insert")
    @ApiOperation(value = "入库历史新增")
    public CommonRes postInBoundResultInsert(@RequestBody InBoundResultInsertDto inBoundResultInsertDto){

        try {
            trInBoundResultService.postInBoundResultInsert(inBoundResultInsertDto);
            return CommonRes.ok("success",null);

        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }

}

