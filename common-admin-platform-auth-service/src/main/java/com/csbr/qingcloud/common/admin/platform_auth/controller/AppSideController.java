package com.csbr.qingcloud.common.admin.platform_auth.controller;

import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.appside.AppSideUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.AppSideVO;
import com.csbr.qingcloud.common.admin.platform_auth.service.AppSideService;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端控制器
 * @author: yio
 * @create: 2020-07-21 09:24
 **/
@RestController
@RequestMapping("/appside")
@Api(tags = "应用端接口")
public class AppSideController {
    @Autowired
    private AppSideService appSideService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增应用端接口")
    public CommonRes<Boolean> add(@RequestBody @Valid AppSideAddDTO dto) {

        return appSideService.addAppSide(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改应用端接口")
    public CommonRes<Boolean> update(@RequestBody @Valid AppSideUpdateDTO dto) {
        return appSideService.updateAppSide(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除应用端接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的平台用户关键字列表不能为空");

        }
        return appSideService.removeAppSide(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取应用端接口")
    public CommonRes<PageListVO<AppSideVO>> getAdminUser(@RequestBody @Valid AppSideQueryDTO dto) {

        return CommonRes.success(appSideService.getAppSide(dto));

    }
}
