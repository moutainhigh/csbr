package com.csbr.qingcloud.common.admin.platform_auth.service;

import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.MenuVO;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.BaseDO;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: common-admin-platform-auth-service
 * @description: 菜单服务
 * @author: yio
 * @create: 2020-07-21 13:58
 **/
public interface MenuService {

    /** 新增菜单
     * @param dto
     * @return
             */
    CommonRes<Boolean> addMenu(@Valid MenuAddDTO dto);

    /**
     * 修改菜单
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateMenu(MenuUpdateDTO dto);

    /**
     * 删除菜单
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeMenu(List<String> guids);

    /**
     * 查询菜单
     * @param dto
     * @return
     */
    List<MenuVO> getMenu(MenuQueryDTO dto) throws InvocationTargetException, IllegalAccessException;


}
