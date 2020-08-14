package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsGroupVO;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorSO;

import java.util.HashMap;
import java.util.List;

/**
 * 人员销售指标业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface TrStaffSalesIndicatorService extends CsbrService<TrStaffSalesIndicators> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);


    /**
     * 获取人员销售指标主与明细数据
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getStaffSalesIndicatorsWithDetail(D so);

    /**
     * 按条件获取销售流量的客户与核算金额
     *
     * @return 结果链表
     */
    List<HashMap> getStaffWithIndicators(TrStaffSalesIndicatorSO so);

    /**
     * 按条件获取产品数
     *
     * @return 结果链表
     */
    Integer getGoodsNum(TrStaffSalesIndicatorSO so);


}