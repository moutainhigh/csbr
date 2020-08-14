package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.MfGoodsMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfGoodsSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfGoodsService;

/**
 * 商品资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfGoodsServiceImpl extends CsbrServiceImpl<MfGoodsMapper, MfGoods> implements MfGoodsService {

    /** 商品资料数据持久化对象 */
    @Autowired
    private MfGoodsMapper mfGoodsMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfGoods> iPage = mfGoodsMapper.mapperPageMfGoods(page, so);
        return new PageListVO().build(iPage);
    }


}