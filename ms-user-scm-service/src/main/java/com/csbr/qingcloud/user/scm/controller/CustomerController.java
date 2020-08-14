package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerVO;
import com.csbr.qingcloud.user.scm.service.CustomerService;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 客户控制器
 * @author: yio
 * @create: 2020-08-06 15:28
 **/
@RestController
@RequestMapping("/customer")
@Api(tags = "客户控制器")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @PostMapping("/data/add")
    @ApiOperation(value = "新增客户接口")
    public CommonRes<Boolean> add(@RequestBody @Valid CustomerAddDTO dto) {

        return customerService.addCustomer(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改客户接口")
    public CommonRes<Boolean> update(@RequestBody @Valid CustomerUpdateDTO dto) {
        return customerService.updateCustomer(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除客户接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的客户关键字列表不能为空");
        }
        return customerService.removeCustomer(guids);

    }


    @PostMapping("/data/get")
    @ApiOperation(value = "获取客户接口")
    public CommonRes<PageListVO<CustomerVO>> getCustomer(@RequestBody @Valid CustomerQueryDTO dto) {

        return CommonRes.success(customerService.getCustomer(dto));

    }
}
