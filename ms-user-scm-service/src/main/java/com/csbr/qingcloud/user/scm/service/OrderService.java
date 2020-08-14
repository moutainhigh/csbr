package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrderVO;

import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 订单服务
 * @author: yio
 * @create: 2020-08-11 15:39
 **/
public interface OrderService {

    /**
     * 订单新增服务
     * @param dto
     * @return
     */
    CommonRes<Boolean> addOrder(OrderAddDTO dto);

    /**
     * 修改订单
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateOrder(OrderUpdateDTO dto);

    /**
     * 删除订单
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeOrder(List<String> guids);

    /**
     * 查询订单详情
     * @param guid
     * @return
     */
    OrderVO getOrderDetail(String guid);

    /**
     * 查询订单
     * @param dto
     * @return
     */
    PageListVO<OrderVO> getOrder(OrderQueryDTO dto);
}
