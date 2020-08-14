package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfSideProduct;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfSideProductMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfSideProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 应用端产品关系业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfSideProductServiceImpl extends CsbrServiceImpl<MfSideProductMapper, MfSideProduct> implements MfSideProductService {

    /** 应用端产品关系数据持久化对象 */
    @Autowired
    private MfSideProductMapper mfSideProductMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfSideProduct> iPage = mfSideProductMapper.mapperPageMfSideProducts(page, so);
        return new PageListVO().build(iPage);
    }


}