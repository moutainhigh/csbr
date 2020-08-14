package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.SalesFlowWithDetailPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrSalesFlowMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSalesFlowService;

import java.util.HashMap;
import java.util.List;

/**
 * 销售流量业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class TrSalesFlowServiceImpl extends CsbrServiceImpl<TrSalesFlowMapper, TrSalesFlow> implements TrSalesFlowService {

    /** 销售流量数据持久化对象 */
    @Autowired
    private TrSalesFlowMapper trSalesFlowMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrSalesFlow> iPage = trSalesFlowMapper.mapperPageTrSalesFlows(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    /**
     * 获取销售流向主与明细数据
     *
     * @return 结果链表
     */
    public <D extends BasePageDTO> PageListVO getTrSalesFlowsWithDetail(D so){
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<SalesFlowWithDetailPOJO> iPage = trSalesFlowMapper.getTrSalesFlowsWithDetail(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    /**
     * 按条件获取销售流量的客户与核算金额
     *
     * @return 结果链表
     */
    public List<HashMap> getCustomerWithAmount(TrSalesFlowSO so){
        return trSalesFlowMapper.getCustomerWithAmount(so);

    }

    @Override
    /**
     * 按条件获取产品数
     *
     * @return 结果链表
     */
    public  Integer getGoodsNum(TrSalesFlowSO so){
        return trSalesFlowMapper.getGoodsNum(so);

    }
}