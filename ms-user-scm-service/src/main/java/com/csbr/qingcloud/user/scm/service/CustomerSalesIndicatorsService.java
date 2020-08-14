package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.CustomerSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsVO;
import org.springframework.stereotype.Service;

/**
 * @program: ms-user-scm-service
 * @description: 客户销售指标服务
 * @author: yio
 * @create: 2020-08-07 13:31
 **/
@Service
public interface CustomerSalesIndicatorsService {


    /**
     * 查询客户销售指标
     * @param dto
     * @return
     */
    PageListVO<CustomerSalesIndicatorsVO> getCustomerSalesIndicators(CustomerSalesIndicatorsQueryDTO dto);

    /**
     * 获取客户销售指标合计
     * @param dto
     * @return
     */
    CustomerSalesIndicatorsSummaryVO getCustomerSalesIndicatorsSummary(CustomerSalesIndicatorsQueryDTO dto);
}
