package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicatorsDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrStaffSalesIndicatorsDetailMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorsDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrStaffSalesIndicatorsDetailService;

/**
 * 人员销售指标明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Service
@Transactional
public class TrStaffSalesIndicatorsDetailServiceImpl extends CsbrServiceImpl<TrStaffSalesIndicatorsDetailMapper, TrStaffSalesIndicatorsDetail> implements TrStaffSalesIndicatorsDetailService {

    /** 人员销售指标明细数据持久化对象 */
    @Autowired
    private TrStaffSalesIndicatorsDetailMapper trStaffSalesIndicatorsDetailMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrStaffSalesIndicatorsDetail> iPage = trStaffSalesIndicatorsDetailMapper.mapperPageTrStaffSalesIndicatorsDetails(page, so);
        return new PageListVO().build(iPage);
    }


}