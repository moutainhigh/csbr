package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenantLic;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfTenantLicMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfTenantLicSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantLicService;

/**
 * 平台会员企业证照业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfTenantLicServiceImpl extends CsbrServiceImpl<MfTenantLicMapper, MfTenantLic> implements MfTenantLicService {

    /** 平台会员企业证照数据持久化对象 */
    @Autowired
    private MfTenantLicMapper mfTenantLicMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfTenantLic> iPage = mfTenantLicMapper.mapperPageMfTenantLics(page, so);
        return new PageListVO().build(iPage);
    }


}