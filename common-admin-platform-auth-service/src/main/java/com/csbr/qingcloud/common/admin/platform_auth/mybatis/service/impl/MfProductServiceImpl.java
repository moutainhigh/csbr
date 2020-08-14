package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfProductMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfProductService;

/**
 * 产品信息业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfProductServiceImpl extends CsbrServiceImpl<MfProductMapper, MfProduct> implements MfProductService {

    /** 产品信息数据持久化对象 */
    @Autowired
    private MfProductMapper mfProductMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfProduct> iPage = mfProductMapper.mapperPageMfProducts(page, so);
        return new PageListVO().build(iPage);
    }


}