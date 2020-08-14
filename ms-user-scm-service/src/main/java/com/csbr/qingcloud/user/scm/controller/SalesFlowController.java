package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesflow.SalesFlowQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.CustomerSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.domain.vo.SalesFlowSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.SalesFlowVO;
import com.csbr.qingcloud.user.scm.service.CustomerSalesIndicatorsService;
import com.csbr.qingcloud.user.scm.service.SalesFlowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向控制器
 * @author: yio
 * @create: 2020-08-07 12:14
 **/
@RestController
@RequestMapping("/salesflow")
@Api(tags = "销售流向控制器接口")
public class SalesFlowController {
    @Autowired
    private SalesFlowService salesFlowService;

    @PostMapping("/data/get")
    @ApiOperation(value = "获取销售流向接口")
    public CommonRes<PageListVO<SalesFlowVO>> getSalesFlow(@RequestBody @Valid SalesFlowQueryDTO dto) {

        return CommonRes.success(salesFlowService.getSalesFlow(dto));

    }

    @PostMapping("/data/get-salesflow-summary")
    @ApiOperation(value = "获取销售流向汇总接口")
    public CommonRes<SalesFlowSummaryVO> getSalesFlowSummary(@RequestBody @Valid SalesFlowQueryDTO dto) {

        return CommonRes.success(salesFlowService.getSalesFlowSummary(dto));

    }
}
