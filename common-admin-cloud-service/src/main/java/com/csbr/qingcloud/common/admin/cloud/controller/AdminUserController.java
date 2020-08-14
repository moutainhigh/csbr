package com.csbr.qingcloud.common.admin.cloud.controller;

import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminLoginDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.AdminUserVO;
import com.csbr.qingcloud.common.admin.cloud.service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 平台用户表的维护
 * @author: yio
 * @create: 2020-07-20 11:43
 **/
@RestController
@RequestMapping("/admin-user")
@Api(tags = "平台用户接口")
@Slf4j
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    // region 登陆接口
    @PostMapping("/login")
    @ApiOperation("管理员登陆接口(云管理员，平台管理员共用)")
    public CommonRes login(@RequestBody AdminLoginDTO dto) {
        return adminUserService.adminLogin(dto);
    }

    @PostMapping("/trigger")
    @ApiOperation("触发获取管理员信息")
    public void triggerInfo() {
        log.info("管理员信息触发");
    }
    // endregion

    @PostMapping("/data/add")
    @ApiOperation(value = "新增平台用户接口")
    public CommonRes<Boolean> add(@RequestBody AdminUserAddDTO dto) {
        return adminUserService.addAdminUser(dto);
    }

    @PutMapping("/data/update")
    @ApiOperation(value = "更新平台用户接口")
    public CommonRes<Boolean> update(@RequestBody AdminUserUpdateDTO dto) {
        return adminUserService.updateAdminUser(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除平台用户接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {
        if (guids == null) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的平台用户关键字列表不能为空");
        }
        return adminUserService.removeAdminUser(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取平台用户接口")
    public CommonRes<PageListVO<AdminUserVO>> getAdminUser(@RequestBody @Valid AdminUserQueryDTO dto) {
        return CommonRes.success(adminUserService.getAdminUser(dto));
    }

}
