package com.csbr.qingcloud.common.admin.platform_auth.controller;

import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.MenuVO;
import com.csbr.qingcloud.common.admin.platform_auth.service.MenuService;
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
 * @description: 菜单控制器
 * @author: yio
 * @create: 2020-07-22 11:59
 **/
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单接口")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/data/add")
    @ApiOperation(value = "新增菜单接口")
    public CommonRes<Boolean> add(@RequestBody @Valid MenuAddDTO dto) {

        return menuService.addMenu(dto);

    }

    @PutMapping("/data/update")
    @ApiOperation(value = "修改菜单接口")
    public CommonRes<Boolean> update(@RequestBody @Valid MenuUpdateDTO dto) {
        return menuService.updateMenu(dto);


    }

    @DeleteMapping("/data/delete")
    @ApiOperation(value = "删除菜单接口")
    public CommonRes<Boolean> remove(@RequestBody List<String> guids) {

        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "需要删除的产品关键字列表不能为空");

        }
        return menuService.removeMenu(guids);

    }

    @PostMapping("/data/get")
    @ApiOperation(value = "获取菜单接口")
    public CommonRes<List<MenuVO>> getMenu(@RequestBody @Valid MenuQueryDTO dto) {

        try {
            return CommonRes.success(menuService.getMenu(dto));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.ERROR, e.getMessage(), e);

        }

    }
}
