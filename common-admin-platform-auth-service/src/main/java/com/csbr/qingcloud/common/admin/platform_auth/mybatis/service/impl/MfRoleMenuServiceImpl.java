package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfRoleMenu;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfRoleMenuMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleMenuPOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色菜单业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Service
@Transactional
public class MfRoleMenuServiceImpl extends CsbrServiceImpl<MfRoleMenuMapper, MfRoleMenu> implements MfRoleMenuService {

    /** 角色菜单数据持久化对象 */
    @Autowired
    private MfRoleMenuMapper mfRoleMenuMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfRoleMenu> iPage = mfRoleMenuMapper.mapperPageMfRoleMenus(page, so);
        return new PageListVO().build(iPage);
    }

    @Override
    public List<MfRoleMenuPOJO> getRoleMenus(String guid){
        return mfRoleMenuMapper.getRoleMenus(guid);
    }

}