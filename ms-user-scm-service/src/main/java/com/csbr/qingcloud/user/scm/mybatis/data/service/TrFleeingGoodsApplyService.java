package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrFleeingGoodsApply;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrFleeingGoodsApplySO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrFleeingGoodsApplyMapper;

/**
 * 举证窜货申请业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

public interface TrFleeingGoodsApplyService extends CsbrService<TrFleeingGoodsApply> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}