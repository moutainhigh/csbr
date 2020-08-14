package com.csbr.qingcloud.common.admin.platform_auth.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.*;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserVO;
import com.csbr.qingcloud.common.admin.platform_auth.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户控制器
 * @author: yio
 * @create: 2020-07-29 13:31
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增用户接口")
    public CommonRes<String> add(@RequestBody @Valid UserAddDTO dto) {

        return userService.addUser(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改用户接口")
    public CommonRes<Boolean> update(@RequestBody @Valid UserUpdateDTO dto) {
        return userService.updateUser(dto);


    }

    @PutMapping("/data/updateregcode-or-adduser")
    @ApiOperation(value = "接收业务上传递的人员用户信息,返回用户GUID")
    public CommonRes<String> updateRegCodeOrAddUser(@RequestBody @Valid UserAddDTO dto) {
        return userService.updateRegCodeOrAddUser(dto);


    }


    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除用户接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的用户关键字列表不能为空");

        }
        return userService.removeUser(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取用户接口")
    public CommonRes<PageListVO<UserVO>> getUser(@RequestBody @Valid UserQueryDTO dto) {

        return CommonRes.success(userService.getUser(dto));

    }

    @GetMapping("/data/get-user-detail")
    @ApiOperation(value = "获取用户明细接口")
    public CommonRes<UserDetailVO> getUserDetail(String userGuid, String tenantGuid) {

        return CommonRes.success(userService.getUserDetail(userGuid, tenantGuid));

    }

    @PostMapping("/data/remove-staff-by-user")
    @ApiOperation(value = "通过移除人员移除用户接口")
    CommonRes<Boolean> removeUserByStaff(@RequestBody @Valid UserRemoveDTO dto) {
        return userService.removeUserByStaff(dto);
    }

    @PostMapping("/data/get-user-role-names")
    @ApiOperation(value = "获取用户角色名接口")
    public Map<String, List<String>> getUserRoleNames(@RequestBody @Valid UserRoleNameDTO dto) {

        return userService.getUserRoleNames(dto.getTenantGuid(), dto.getUserGuids());
    }

    @PostMapping("/data/get-users-by-idcode-mobile-plat")
    @ApiOperation(value = "依据平台GUID，身份证，电话获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobileNos", value = "电话列表"),
            @ApiImplicitParam(name = "platGuid", value = "平台GUID")
    })
    public CommonRes<Map<String, String>> getUsersByIdMobilePlat(@RequestParam List<String> mobileNos,
                                                                 @RequestParam String platGuid) {
        return userService.getUsersByIdMobilePlat(mobileNos, platGuid);
    }

    @PostMapping("/data/batch-add-user")
    @ApiOperation(value = "批量添加用户")
    public CommonRes<Map<String, String>> batchAddUser(@RequestBody List<UserAddDTO> dtos) {
        return CommonRes.success(userService.batchAddUser(dtos));
    }
}
