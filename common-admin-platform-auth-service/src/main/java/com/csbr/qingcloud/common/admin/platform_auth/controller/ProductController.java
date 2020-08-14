package com.csbr.qingcloud.common.admin.platform_auth.controller;

import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.ProductVO;
import com.csbr.qingcloud.common.admin.platform_auth.service.ProductService;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 产品控制器
 * @author: yio
 * @create: 2020-07-21 09:24
 **/
@RestController
@RequestMapping("/product")
@Api(tags = "产品接口")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增产品接口")
    public CommonRes<Boolean> add(@RequestBody @Valid ProductAddDTO dto) {

        return productService.addProduct(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改产品接口")
    public CommonRes<Boolean> update(@RequestBody @Valid ProductUpdateDTO dto) {
        return productService.updateProduct(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除产品接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的产品关键字列表不能为空");

        }
        return productService.removeProduct(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取产品接口")
    public CommonRes<PageListVO<ProductVO>> getProduct(@RequestBody @Valid ProductQueryDTO dto) {

        return CommonRes.success(productService.getProduct(dto));

    }
}
