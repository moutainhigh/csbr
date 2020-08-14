package com.csbr.qingcloud.common.admin.platform_auth.service.impl;


import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.datapermission.DataPermissionQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.DataPermissionVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfDataPermission;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleDataPermissionPOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfDataPermissionService;
import com.csbr.qingcloud.common.admin.platform_auth.service.DataPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 数据权限服务
 * @author: yio
 * @create: 2020-07-23 16:09
 **/
@Service
public class DataPermissionServiceImpl implements DataPermissionService {

    @Autowired
    private MfDataPermissionService mfDataPermissionService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Override
    public PageListVO<DataPermissionVO> getDataPermission(DataPermissionQueryDTO dto) {
        PageListVO<MfDataPermission> lst = mfDataPermissionService.csbrPageList(dto
                , mfDataPermissionService.csbrQueryWrapper(dto, MfDataPermission.class));
        return csbrBeanUtil.convert(lst, PageListVO.class);
    }

    /**
     * 通过角色获取数据权限
     *
     * @param roleGuid
     * @return
     */
    @Override
    public List<MfRoleDataPermissionPOJO> getDataPermissionByRole(String roleGuid) {
        return mfDataPermissionService.getDataPermissionsByRole(roleGuid);

    }
}
