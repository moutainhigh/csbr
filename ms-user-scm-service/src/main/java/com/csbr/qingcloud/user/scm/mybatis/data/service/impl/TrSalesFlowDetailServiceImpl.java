package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlowDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrSalesFlowDetailMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSalesFlowDetailService;

/**
 * 销售流量明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Service
@Transactional
public class TrSalesFlowDetailServiceImpl extends CsbrServiceImpl<TrSalesFlowDetailMapper, TrSalesFlowDetail> implements TrSalesFlowDetailService {

    /** 销售流量明细数据持久化对象 */
    @Autowired
    private TrSalesFlowDetailMapper trSalesFlowDetailMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrSalesFlowDetail> iPage = trSalesFlowDetailMapper.mapperPageTrSalesFlowDetails(page, so);
        return new PageListVO().build(iPage);
    }


}