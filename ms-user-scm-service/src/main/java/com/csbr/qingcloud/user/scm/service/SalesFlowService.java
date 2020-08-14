package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesflow.SalesFlowQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.*;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向服务
 * @author: yio
 * @create: 2020-08-07 14:53
 **/
public interface SalesFlowService {
    /**
     * 查询销售流向
     * @param dto
     * @return
     */
    PageListVO<SalesFlowVO> getSalesFlow(SalesFlowQueryDTO dto);

    /**
     * 获取销售流向合计
     * @param dto
     * @return
     */
    SalesFlowSummaryVO getSalesFlowSummary(SalesFlowQueryDTO dto);

    /**
     * 获取销售与商品明细组合数据
     * @param dto
     * @return
     */
    PageListVO<SalesFlowGroupVO> getSalesFlowGroup(SalesFlowQueryDTO dto);
}
