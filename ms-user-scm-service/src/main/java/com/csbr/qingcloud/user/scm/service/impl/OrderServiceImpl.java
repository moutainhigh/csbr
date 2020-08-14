package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderDetailAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.order.OrderUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrderDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.OrderVO;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrder;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrderDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfCustomerService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfGoodsService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrOrderDetailService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrOrderService;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.RedisStaffPOJO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 订单服务
 * @author: yio
 * @create: 2020-08-11 15:59
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TrOrderService trOrderService;

    @Autowired
    private TrOrderDetailService trOrderDetailService;

    @Autowired
    private MfCustomerService mfCustomerService;

    @Autowired
    private MfGoodsService mfGoodsService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private StaffServiceImpl staffService;
    @Autowired
    private MfTenantService mfTenantService;


    private void checkDuplicate(String tenantGuid, String orderNo, String guid) {
        //查看编码与名称是否重复
        LambdaQueryWrapper<TrOrder> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TrOrder::getTenantGuid, tenantGuid)
                .eq(TrOrder::getOrderNo, orderNo)
                .select(TrOrder::getGuid);
        TrOrder ent = trOrderService.getOne(queryWrapper);
        if (ent != null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("订单(%s)已存在。", orderNo));

        }


    }

    private void checkExists(OrderAddDTO dto) {

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());
        if (CollectionUtils.isEmpty(dto.getOrderDetails())) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "订单明细行数不能为空。");

        }

    }

    private MfCustomer getCustomer(String customerGuid) {
        //查看客户是否存在
        MfCustomer customer = mfCustomerService.getById(customerGuid);
        if (customer == null) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("客户关键字(%s)不存在。", customerGuid));

        }
        return customer;
    }

    private TrOrder initOrder(OrderAddDTO dto) {

        MfCustomer customer = getCustomer(dto.getCustomerGuid());
        RedisStaffPOJO staffPOJO = staffService.getCurrentStaffInfoByRedis(dto.getTenantGuid());

        TrOrder order = csbrBeanUtil.convert(dto, TrOrder.class,true);
        order.setCustomerCode(customer.getCustomerCode());
        order.setCustomerName(customer.getCustomerName());
        order.setContactTel(customer.getContactTel());
        order.setProvince(customer.getProvince());
        order.setCity(customer.getCity());
        order.setDistrict(customer.getDistrict());
        order.setVenue(customer.getVenue());
        order.setAddress(customer.getAddress());
        order.setTerminalProperty(customer.getTerminalProperty());
        order.setOrganisationGuid(staffPOJO.getOrganisationGuid());

        return order;
    }

    private List<TrOrderDetail> initOrderDetails(List<OrderDetailAddDTO> detailAddDTOs, TrOrder order) {
        List<TrOrderDetail> orderDetails = new ArrayList<>();
        MfGoods goods = null;
        double totalQty = 0, totalAmount = 0;
        Set<String> goodsSpecs = new HashSet<>();
        List<String> goodsGuids = detailAddDTOs.stream().map(x -> x.getGoodsGuid()).collect(Collectors.toList());
        //查询产品
        List<MfGoods> goodsLst = (List<MfGoods>) mfGoodsService.listByIds(goodsGuids);
        if (CollectionUtils.isEmpty(goodsLst)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "订单产品不存在。");

        }
        Map<String, MfGoods> mfGoodsMap = goodsLst.stream().collect(Collectors.toMap(MfGoods::getGuid, gds -> gds));
        for (OrderDetailAddDTO detailDto : detailAddDTOs) {
            TrOrderDetail orderDetail = csbrBeanUtil.convert(detailDto, TrOrderDetail.class);
            goods = (MfGoods) mfGoodsMap.get(detailDto.getGoodsGuid());
            if (goods == null) {
                throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("产品关键字(%s)不存在。", detailDto.getGoodsGuid()));

            }
            orderDetail.setGoodsCode(goods.getGoodsCode());
            orderDetail.setGoodsSpec(goods.getGoodsSpec());
            orderDetail.setGoodsName(goods.getGoodsName());
            orderDetail.setGoodsSpec(goods.getGoodsSpec());
            orderDetail.setUnitStyle(goods.getUnitStyle());
            orderDetail.setBigUnitQty(goods.getBigUnitQty());
            orderDetail.setProducer(goods.getProducer());
            orderDetail.setPrice(goods.getPrice());
            orderDetail.setAmount(orderDetail.getQty() * orderDetail.getPrice());
            orderDetail.setOrderGuid(order.getGuid());
            trOrderDetailService.csbrAddEntity(orderDetail);
            totalQty += orderDetail.getQty();
            totalAmount += orderDetail.getAmount();
            goodsSpecs.add(orderDetail.getGoodsCode() + "_" + orderDetail.getGoodsSpec());
            orderDetails.add(orderDetail);
        }
        order.setDetailNum(orderDetails.size());
        order.setGoodsSpecNum(goodsSpecs.size());
        order.setTotalQty(totalQty);
        order.setTotalAmount(totalAmount);
        return orderDetails;
    }

    @Override
    public CommonRes<Boolean> addOrder(OrderAddDTO dto) {
        //查看岗位名称是否重复
        if (StringUtils.isNotEmpty(dto.getOrderNo())) {
            checkDuplicate(dto.getTenantGuid(), dto.getOrderNo(), null);
        } else {
            //自动生成订单号
        }
        checkExists(dto);

        TrOrder order = initOrder(dto);
        if (order.getInputTime() == null) {
            order.setInputTime(new Timestamp(System.currentTimeMillis()));
        }
        order.setGuid(CommonUtil.newGuid());
        trOrderService.csbrAddEntity(order);
        //初始化订单明细
        List<TrOrderDetail> orderDetails = initOrderDetails(dto.getOrderDetails(), order);

        boolean flag = trOrderService.save(order);
        flag = flag && trOrderDetailService.saveBatch(orderDetails);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("为客户(%s)新增的订单(%s)下单失败。", order.getCustomerName(), dto.getOrderNo()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改订单
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateOrder(OrderUpdateDTO dto) {
        //查找更新的数据是否存在
        if (!trOrderService.isExistsData(Arrays.asList(dto.getGuid()), TrOrder.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("订单(%s)不存在，无法更新。", dto.getOrderNo()));
        }
        //查看产品是否重复
        checkDuplicate(dto.getTenantGuid(), dto.getOrderNo(), dto.getGuid());

        checkExists(dto);

        //删除明细数据
        LambdaQueryWrapper<TrOrderDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TrOrderDetail::getOrderGuid, dto.getGuid());
        trOrderDetailService.remove(queryWrapper);

        //更新订单主表数据
        TrOrder order = initOrder(dto);
        trOrderService.csbrUpdateEntity(order);
        //初始化订单明细
        List<TrOrderDetail> orderDetails = initOrderDetails(dto.getOrderDetails(), order);
        //更新数据
        LambdaUpdateWrapper<TrOrder> updateWrapper = trOrderService.csbrUpdateWrapper(TrOrder.class);
        updateWrapper.eq(TrOrder::getGuid, dto.getGuid());

        boolean flag = trOrderService.update(order, updateWrapper);

        flag = flag && trOrderDetailService.saveBatch(orderDetails);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("为客户(%s)修改订单(%s)失败。", order.getCustomerName(), dto.getOrderNo()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 移除订单
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeOrder(List<String> guids) {
        //查找更新的数据是否存在
        if (!trOrderService.isExistsData(guids, TrOrder.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<TrOrder> updateWrapper = trOrderService.csbrUpdateWrapper(TrOrder.class);
        updateWrapper.in(TrOrder::getGuid, guids);
        boolean flag = trOrderService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    @Override
    public OrderVO getOrderDetail(String guid) {
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "订单关键字不能为空");

        }

        TrOrder order = trOrderService.getById(guid);
        OrderVO vo = null;
        if (order != null) {
            vo = csbrBeanUtil.convert(order, OrderVO.class);
            //查询明细数据
            LambdaQueryWrapper<TrOrderDetail> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(TrOrderDetail::getOrderGuid, guid)
                    .orderByAsc(TrOrderDetail::getCreateTime);
            List<TrOrderDetail> lst = trOrderDetailService.list(queryWrapper);
            vo.setOrderDetailVOs(csbrBeanUtil.convert(lst, OrderDetailVO.class));
        } else {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "订单数据不存在。");
        }
        return vo;
    }

    @Override
    public PageListVO<OrderVO> getOrder(OrderQueryDTO dto) {
        LambdaQueryWrapper<TrOrder> wrapper = trOrderService.csbrQueryWrapper(dto, TrOrder.class);
        if (StringUtils.isNotEmpty(dto.getGoodsName())) {
            //添加商品子查询
            wrapper.exists(String.format("select guid from tr_order_detail where order_guid=tr_order.guid and goods_name like '%s%'", dto.getGoodsName()));
        }
        wrapper.orderByDesc(TrOrder::getInputTime);
        PageListVO<TrOrder> lst = trOrderService.csbrPageList(dto , wrapper);

        //获取订单GUID列表
        List<String> orderGuids = lst.getRecords().stream().map(x -> x.getGuid()).collect(Collectors.toList());
        //通过订单GUID列表查询订单详情
        LambdaQueryWrapper<TrOrderDetail> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(TrOrderDetail::getOrderGuid, orderGuids);
        if (dto.getDispDetailCount() != null) {
            queryWrapper.exists(String.format("select count(1) from tr_order_detail a where a.order_guid=tr_order_detail.order_guid and tr_order_detail.guid<guid having count(1)<%s",dto.getDispDetailCount()));
        }
        queryWrapper.orderByAsc(TrOrderDetail::getOrderGuid, TrOrderDetail::getCreateTime);
        List<TrOrderDetail> details=trOrderDetailService.list(queryWrapper);
        //以订单GUID为KEY转换成MAP
        Map<String,List<TrOrderDetail>> map=details.stream().collect(
                Collectors.groupingBy(TrOrderDetail::getOrderGuid,
                Collectors.mapping(v->v,Collectors.toList())));
        PageListVO<OrderVO> pageListVO=csbrBeanUtil.convert(lst, PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),OrderVO.class));
        for (OrderVO vo:pageListVO.getRecords()) {
            vo.setOrderDetailVOs(csbrBeanUtil.convert(map.get(vo.getGuid()),OrderDetailVO.class));
        }
        return pageListVO;
    }
}
