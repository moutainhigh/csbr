package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.SalesFlowWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.StaffSalesIndicatorsWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorSO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrStaffSalesIndicatorMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrStaffSalesIndicatorService;

import java.util.HashMap;
import java.util.List;

/**
 * 人员销售指标业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class TrStaffSalesIndicatorServiceImpl extends CsbrServiceImpl<TrStaffSalesIndicatorMapper, TrStaffSalesIndicators> implements TrStaffSalesIndicatorService {

    /** 人员销售指标数据持久化对象 */
    @Autowired
    private TrStaffSalesIndicatorMapper trStaffSalesIndicatorMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrStaffSalesIndicators> iPage = trStaffSalesIndicatorMapper.mapperPageTrStaffSalesIndicators(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    /**
     * 获取人员销售指标主与明细数据
     *
     * @return 结果链表
     */
    public <D extends BasePageDTO> PageListVO getStaffSalesIndicatorsWithDetail(D so){
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<StaffSalesIndicatorsWithDetailPOJO> iPage = trStaffSalesIndicatorMapper.getStaffSalesIndicatorsWithDetail(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    /**
     * 按条件获取销售流量的客户与核算金额
     *
     * @return 结果链表
     */
    public List<HashMap> getStaffWithIndicators(TrStaffSalesIndicatorSO so){
        return trStaffSalesIndicatorMapper.getStaffWithIndicators(so);

    }

    @Override
    /**
     * 按条件获取产品数
     *
     * @return 结果链表
     */
    public  Integer getGoodsNum(TrStaffSalesIndicatorSO so){
        return trStaffSalesIndicatorMapper.getGoodsNum(so);

    }
}