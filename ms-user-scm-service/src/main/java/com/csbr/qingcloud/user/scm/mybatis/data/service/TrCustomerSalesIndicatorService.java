package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicators;

/**
 * 客户销售指标业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface TrCustomerSalesIndicatorService extends CsbrService<TrCustomerSalesIndicators> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}