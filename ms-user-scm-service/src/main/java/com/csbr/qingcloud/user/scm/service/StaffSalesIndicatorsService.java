package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsGroupVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsVO;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标服务
 * @author: yio
 * @create: 2020-08-06 18:18
 **/
public interface StaffSalesIndicatorsService {

    /**
     * 查询人员销售指标
     * @param dto
     * @return
     */
    PageListVO<StaffSalesIndicatorsVO> getStaffSalesIndicators(StaffSalesIndicatorsQueryDTO dto);

    /**
     * 获取人员销售指标合计
     * @param dto
     * @return
     */
    StaffSalesIndicatorsSummaryVO getStaffSalesIndicatorsSummary(StaffSalesIndicatorsQueryDTO dto);

    /**
     * 获取人员指标与明细组合数据
     * @param dto
     * @return
     */
    PageListVO<StaffSalesIndicatorsGroupVO> getStaffSalesIndicatorsGroup(StaffSalesIndicatorsQueryDTO dto);
}
