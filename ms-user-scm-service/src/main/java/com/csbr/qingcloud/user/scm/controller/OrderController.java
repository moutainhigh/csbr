package com.csbr.qingcloud.user.scm.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrderVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffVO;
import com.csbr.qingcloud.user.scm.service.GoodsService;
import com.csbr.qingcloud.user.scm.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 订单控制器
 * @author: yio
 * @create: 2020-08-11 17:22
 **/
@RestController
@RequestMapping("/order")
@Api(tags = "订单控制器")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增订单接口")
    public CommonRes<Boolean> add(@RequestBody @Valid OrderAddDTO dto) {

        return orderService.addOrder(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改订单接口")
    public CommonRes<Boolean> update(@RequestBody @Valid OrderUpdateDTO dto) {
        return orderService.updateOrder(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除订单接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的订单关键字列表不能为空");
        }
        return orderService.removeOrder(guids);

    }

    @GetMapping("/data/get-order-detail")
    @ApiOperation(value = "获取订单明细接口")
    public CommonRes<OrderVO> getOrderDetail(String guid) {

        return CommonRes.success(orderService.getOrderDetail(guid));

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取订单接口")
    public CommonRes<PageListVO<OrderVO>> getOrder(@RequestBody @Valid OrderQueryDTO dto) {

        return CommonRes.success(orderService.getOrder(dto));

    }
}
