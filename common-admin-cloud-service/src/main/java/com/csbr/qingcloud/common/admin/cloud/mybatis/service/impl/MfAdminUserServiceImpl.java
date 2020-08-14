package com.csbr.qingcloud.common.admin.cloud.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfAdminUser;
import com.csbr.qingcloud.common.admin.cloud.mybatis.mapper.MfAdminUserMapper;
import com.csbr.qingcloud.common.admin.cloud.mybatis.service.MfAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 平台用户业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Service
@Transactional
public class MfAdminUserServiceImpl extends CsbrServiceImpl<MfAdminUserMapper, MfAdminUser> implements MfAdminUserService {

    /** 平台用户数据持久化对象 */
    @Autowired
    private MfAdminUserMapper mfAdminUserMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfAdminUser> iPage = mfAdminUserMapper.mapperPageMfAdminUsers(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 查询平台用户，带平台名称
     * @param so
     * @param <D>
     * @return
     */
    @Override
    public <D extends BasePageDTO> PageListVO getDataWithPlatformName(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfAdminUser> iPage = mfAdminUserMapper.getDataWithPlatformName(page, so);
        return new PageListVO().build(iPage);
    }
}