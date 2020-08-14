package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrderDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrOrderDetailSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrOrderDetailMapper;

/**
 * 订单明细业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

public interface TrOrderDetailService extends CsbrService<TrOrderDetail> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}