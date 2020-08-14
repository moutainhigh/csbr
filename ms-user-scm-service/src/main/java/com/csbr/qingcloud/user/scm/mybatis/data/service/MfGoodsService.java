package com.csbr.qingcloud.user.scm.mybatis.data.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfGoodsSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.MfGoodsMapper;

/**
 * 商品资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfGoodsService extends CsbrService<MfGoods> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);
}