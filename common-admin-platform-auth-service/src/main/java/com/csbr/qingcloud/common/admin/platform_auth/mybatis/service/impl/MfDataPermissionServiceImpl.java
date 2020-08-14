package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfDataPermission;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleDataPermissionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfDataPermissionMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfDataPermissionService;

import java.util.List;

/**
 * 数据权限业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfDataPermissionServiceImpl extends CsbrServiceImpl<MfDataPermissionMapper, MfDataPermission> implements MfDataPermissionService {

    /** 数据权限数据持久化对象 */
    @Autowired
    private MfDataPermissionMapper mfDataPermissionMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfDataPermission> iPage = mfDataPermissionMapper.mapperPageMfDataPermissions(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 通过角色获取数据权限
     * @param roleGuid
     * @return
     */
    @Override
    public List<MfRoleDataPermissionPOJO> getDataPermissionsByRole(String roleGuid) {
        return mfDataPermissionMapper.getDataPermissionsByRole(roleGuid);
    }

}