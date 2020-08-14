package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrSalesFlowMapper;

import java.util.HashMap;
import java.util.List;

/**
 * 销售流量业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface TrSalesFlowService extends CsbrService<TrSalesFlow> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 获取销售流向主与明细数据
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getTrSalesFlowsWithDetail(D so);

    List<HashMap> getCustomerWithAmount(TrSalesFlowSO so);

    /**
     * 按条件获取产品数
     *
     * @return 结果链表
     */
    Integer getGoodsNum(TrSalesFlowSO so);
}