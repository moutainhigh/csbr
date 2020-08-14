package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfOrganisation;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfOrganisationMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfOrganisationSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfOrganisationService;

/**
 * 组织架构信息业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfOrganisationServiceImpl extends CsbrServiceImpl<MfOrganisationMapper, MfOrganisation> implements MfOrganisationService {

    /** 组织架构信息数据持久化对象 */
    @Autowired
    private MfOrganisationMapper mfOrganisationMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfOrganisation> iPage = mfOrganisationMapper.mapperPageMfOrganisations(page, so);
        return new PageListVO().build(iPage);
    }


}