package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.CustomerSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.service.CustomerSalesIndicatorsService;
import com.csbr.qingcloud.user.scm.service.StaffSalesIndicatorsService;
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
 * @description: 客户销售指标控制器
 * @author: yio
 * @create: 2020-08-07 12:14
 **/
@RestController
@RequestMapping("/customersalesindicator")
@Api(tags = "客户销售指标接口")
public class CustomerSalesIndicatorsController {
    @Autowired
    private CustomerSalesIndicatorsService customerSalesIndicatorsService;

    @PostMapping("/data/get")
    @ApiOperation(value = "获取客户销售指标接口")
    public CommonRes<PageListVO<CustomerSalesIndicatorsVO>> getCustomerSalesIndicators(@RequestBody @Valid CustomerSalesIndicatorsQueryDTO dto) {

        return CommonRes.success(customerSalesIndicatorsService.getCustomerSalesIndicators(dto));

    }

    @PostMapping("/data/get-indicators-summary")
    @ApiOperation(value = "获取客户销售指标汇总接口")
    public CommonRes<CustomerSalesIndicatorsSummaryVO> getCustomerSalesIndicatorsSummary(@RequestBody @Valid CustomerSalesIndicatorsQueryDTO dto) {

        return CommonRes.success(customerSalesIndicatorsService.getCustomerSalesIndicatorsSummary(dto));

    }
}
