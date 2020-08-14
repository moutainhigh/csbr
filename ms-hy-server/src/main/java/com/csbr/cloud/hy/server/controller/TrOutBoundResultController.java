package com.csbr.cloud.hy.server.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.InBoundResultInsertDto;
import com.csbr.cloud.hy.server.domain.dto.OutBoundResultInsertDto;
import com.csbr.cloud.hy.server.service.TrOutBoundResultService;
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
@RequestMapping("/ms-hy-server/trOutBoundResult")
@Api(tags = {"出库"})
public class TrOutBoundResultController {

    @Autowired
    private TrOutBoundResultService trOutBoundResultService;

    @SentinelResource(value = "sentile-rule")
    @PostMapping("post-out-bound-result-insert")
    @ApiOperation(value = "出库历史新增")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType = "body", required = true, name = "outBoundResultInsertDto", dataType = "OutBoundResultInsertDto", value = "出库信息"),
//    })
    public CommonRes postInBoundResultInsert(@RequestBody OutBoundResultInsertDto outBoundResultInsertDto){

        try {
            trOutBoundResultService.postOutBoundResultInsert(outBoundResultInsertDto);
            return CommonRes.ok("success",null);

        }catch (Exception e){
            return CommonRes.ok(e.getMessage(),null);
        }

    }

}

