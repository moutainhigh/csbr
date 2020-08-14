package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfAppSide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfAppSideMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfAppSideService;

/**
 * 应用端业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfAppSideServiceImpl extends CsbrServiceImpl<MfAppSideMapper, MfAppSide> implements MfAppSideService {

    /** 应用端数据持久化对象 */
    @Autowired
    private MfAppSideMapper mfAppSideMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfAppSide> iPage = mfAppSideMapper.mapperPageMfAppSides(page, so);
        return new PageListVO().build(iPage);
    }


}