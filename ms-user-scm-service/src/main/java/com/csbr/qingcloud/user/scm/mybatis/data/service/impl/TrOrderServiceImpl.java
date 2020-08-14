package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrder;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrOrderMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrOrderSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrOrderService;

/**
 * 订单业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Service
@Transactional
public class TrOrderServiceImpl extends CsbrServiceImpl<TrOrderMapper, TrOrder> implements TrOrderService {

    /** 订单数据持久化对象 */
    @Autowired
    private TrOrderMapper trOrderMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrOrder> iPage = trOrderMapper.mapperPageTrOrders(page, so);
        return new PageListVO().build(iPage);
    }


}