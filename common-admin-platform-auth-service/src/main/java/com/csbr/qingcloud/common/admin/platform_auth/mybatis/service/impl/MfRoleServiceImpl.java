package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfRole;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfRoleMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfRoleServiceImpl extends CsbrServiceImpl<MfRoleMapper, MfRole> implements MfRoleService {

    /** 角色数据持久化对象 */
    @Autowired
    private MfRoleMapper mfRoleMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfRole> iPage = mfRoleMapper.mapperPageMfRoles(page, so);
        return new PageListVO().build(iPage);
    }


}