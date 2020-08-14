package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlowDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrSalesFlowDetailMapper;

/**
 * 销售流量明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

public interface TrSalesFlowDetailService extends CsbrService<TrSalesFlowDetail> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}