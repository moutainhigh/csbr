package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUserRole;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUser;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfUserMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Service
@Transactional
public class MfUserServiceImpl extends CsbrServiceImpl<MfUserMapper, MfUser> implements MfUserService {

    /** 用户数据持久化对象 */
    @Autowired
    private MfUserMapper mfUserMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfUser> iPage = mfUserMapper.mapperPageMfUsers(page, so);
        return new PageListVO().build(iPage);
    }



}