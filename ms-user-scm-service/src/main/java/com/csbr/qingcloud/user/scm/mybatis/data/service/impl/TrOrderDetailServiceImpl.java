package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrderDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrOrderDetailMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrOrderDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrOrderDetailService;

/**
 * 订单明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Service
@Transactional
public class TrOrderDetailServiceImpl extends CsbrServiceImpl<TrOrderDetailMapper, TrOrderDetail> implements TrOrderDetailService {

    /** 订单明细数据持久化对象 */
    @Autowired
    private TrOrderDetailMapper trOrderDetailMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrOrderDetail> iPage = trOrderDetailMapper.mapperPageTrOrderDetails(page, so);
        return new PageListVO().build(iPage);
    }


}