package com.csbr.qingcloud.common.admin.platform_auth.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleDataPermissionAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleVO;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色服务
 * @author: yio
 * @create: 2020-07-22 15:52
 **/
public interface RoleService {
    /** 新增菜单
     * @param dto
     * @return
     */
    CommonRes<Boolean> addRole(@Valid RoleAddDTO dto);
    /**
     * 修改角色
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateRole(RoleUpdateDTO dto);

    /**
     * 删除角色
     * @param guids
     * @return
     */
     CommonRes<Boolean> removeRole(List<String> guids);

    /**
     * 获取角色列表
     * @param dto
     * @return
     */
     PageListVO<RoleVO> getRoleList(RoleQueryDTO dto);

    /**
     * 通过角色Guid获取角色详细
     * @param guid
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
     RoleDetailVO getRoleDetail(String guid) throws InvocationTargetException, IllegalAccessException;

    CommonRes<Boolean> addRoleDataPermission(RoleDataPermissionAddDTO dto);
}
