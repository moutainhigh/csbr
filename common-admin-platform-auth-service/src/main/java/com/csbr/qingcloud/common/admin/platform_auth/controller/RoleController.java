package com.csbr.qingcloud.common.admin.platform_auth.controller;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleDataPermissionAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleVO;
import com.csbr.qingcloud.common.admin.platform_auth.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色控制器
 * @author: yio
 * @create: 2020-07-24 13:28
 **/
@RestController
@RequestMapping("/role")
@Api(tags = "角色控制器")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增角色接口")
    public CommonRes<Boolean> add(@RequestBody @Valid RoleAddDTO dto) {

        return roleService.addRole(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改角色接口")
    public CommonRes<Boolean> update(@RequestBody @Valid RoleUpdateDTO dto) {
        return roleService.updateRole(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除角色接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的角色关键字列表不能为空");

        }
        return roleService.removeRole(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取角色接口")
    public CommonRes<PageListVO<RoleVO>> getRole(@RequestBody @Valid RoleQueryDTO dto) {

        return CommonRes.success(roleService.getRoleList(dto));

    }

    @PostMapping("/data/get-detail")
    @ApiOperation(value = "获取角色接口")
    public CommonRes<RoleDetailVO> getRoleDetail(@RequestBody String guid) {

        try {
            return CommonRes.success(roleService.getRoleDetail(guid));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);

        }

    }

    @PostMapping("/data/add-data-permission")
    @ApiOperation(value = "为角色添加数据权限")
    public CommonRes<Boolean> addDataPermission(@RequestBody @Valid RoleDataPermissionAddDTO dto) {

        return roleService.addRoleDataPermission(dto);

    }
}
