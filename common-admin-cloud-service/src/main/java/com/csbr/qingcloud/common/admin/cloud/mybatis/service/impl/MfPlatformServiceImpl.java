package com.csbr.qingcloud.common.admin.cloud.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfPlatform;
import com.csbr.qingcloud.common.admin.cloud.mybatis.mapper.MfPlatformMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.cloud.mybatis.service.MfPlatformService;

/**
 * 平台资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Service
@Transactional
public class MfPlatformServiceImpl extends CsbrServiceImpl<MfPlatformMapper, MfPlatform> implements MfPlatformService {

    /** 平台资料数据持久化对象 */
    @Autowired
    private MfPlatformMapper mfPlatformMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfPlatform> iPage = mfPlatformMapper.mapperPageMfPlatforms(page, so);
        return new PageListVO().build(iPage);
    }


}