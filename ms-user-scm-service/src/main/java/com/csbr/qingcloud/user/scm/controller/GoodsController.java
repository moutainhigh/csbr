package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerVO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 产品资料控制器
 * @author: yio
 * @create: 2020-08-06 16:58
 **/
@RestController
@RequestMapping("/goods")
@Api(tags = "产品资料控制器")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增产品接口")
    public CommonRes<Boolean> add(@RequestBody @Valid GoodsAddDTO dto) {

        return goodsService.addGoods(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改产品接口")
    public CommonRes<Boolean> update(@RequestBody @Valid GoodsUpdateDTO dto) {
        return goodsService.updateGoods(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除产品接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的产品关键字列表不能为空");
        }
        return goodsService.removeGoods(guids);

    }


    @PostMapping("/data/get")
    @ApiOperation(value = "获取产品接口")
    public CommonRes<PageListVO<GoodsVO>> getGoods(@RequestBody @Valid GoodsQueryDTO dto) {

        return CommonRes.success(goodsService.getGoods(dto));

    }
}
