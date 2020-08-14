package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrCustomerSalesIndicatorMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrCustomerSalesIndicatorService;

/**
 * 客户销售指标业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class TrCustomerSalesIndicatorServiceImpl extends CsbrServiceImpl<TrCustomerSalesIndicatorMapper, TrCustomerSalesIndicators> implements TrCustomerSalesIndicatorService {

    /** 客户销售指标数据持久化对象 */
    @Autowired
    private TrCustomerSalesIndicatorMapper trCustomerSalesIndicatorMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrCustomerSalesIndicators> iPage = trCustomerSalesIndicatorMapper.mapperPageTrCustomerSalesIndicators(page, so);
        return new PageListVO().build(iPage);
    }


}