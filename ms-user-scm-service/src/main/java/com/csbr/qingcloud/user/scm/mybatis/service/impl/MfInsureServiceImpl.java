package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfInsure;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfInsureMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfInsureSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfInsureService;

/**
 * 保险资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfInsureServiceImpl extends CsbrServiceImpl<MfInsureMapper, MfInsure> implements MfInsureService {

    /** 保险资料数据持久化对象 */
    @Autowired
    private MfInsureMapper mfInsureMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfInsure> iPage = mfInsureMapper.mapperPageMfInsures(page, so);
        return new PageListVO().build(iPage);
    }


}