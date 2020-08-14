package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrder;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrOrderSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrOrderMapper;

/**
 * 订单业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

public interface TrOrderService extends CsbrService<TrOrder> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}