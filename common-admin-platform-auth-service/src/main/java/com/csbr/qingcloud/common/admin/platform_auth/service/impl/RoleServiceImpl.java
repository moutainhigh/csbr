package com.csbr.qingcloud.common.admin.platform_auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ListToHierarchical;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleDataPermissionAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.role.RoleUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.MenuVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.*;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleMenuPOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.*;
import com.csbr.qingcloud.common.admin.platform_auth.service.MenuService;
import com.csbr.qingcloud.common.admin.platform_auth.service.RoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: common-admin-platform-auth-service
 * @description: 角色服务
 * @author: yio
 * @create: 2020-07-22 15:54
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private MfRoleService mfRoleService;

    @Autowired
    private MfAppSideService mfAppSideService;

    @Autowired
    private MfRoleMenuService mfRoleMenuService;

    @Autowired
    private MfMenuService mfMenuService;


    @Autowired
    private MfDataPermissionService mfDataPermissionService;
    @Autowired
    private MfRoleDataPermissionService mfRoleDataPermissionService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;


    @Autowired
    private ValidatorUtil validatorUtil;


    private boolean check(RoleAddDTO dto,String guid) {
        //检查应用端是否存在
        if (!mfAppSideService.isExistsData(Arrays.asList(dto.getAppSideGuid()), MfAppSide.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("角色(%s)对应的应用端不存在。", dto.getRoleName()));

        }
        //同一应用端下的角色名称不允许重复
        LambdaQueryWrapper<MfRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfRole::getAppSideGuid, dto.getAppSideGuid())
                .eq(MfRole::getRoleName, dto.getRoleName())
                .select(MfRole::getGuid);
        MfRole ent=mfRoleService.getOne(queryWrapper);
        if(ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))){
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("当前应用端下的角色(%s)已存在。", dto.getRoleName()));

        }
        //检查菜单是否都存在
        if (CollectionUtils.isNotEmpty(dto.getMenuGuids())) {
            if (!mfMenuService.isExistsData(dto.getMenuGuids(), MfMenu.class)) {
                throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("给角色(%s)添加的功能菜单中存在未定义的功能数据。", dto.getRoleName()));

            }
        } else {
            return false;
        }
        return true;
    }

    private void addRoleMenu(RoleAddDTO dto, String roleGuid) {
        //添加角色菜单关系
        List<MfRoleMenu> roleMenus = new ArrayList<>();
        MfRoleMenu roleMenu = null;
        for (String menuGuid : dto.getMenuGuids()) {
            roleMenu = new MfRoleMenu();
            roleMenu.setMenuGuid(menuGuid);
            roleMenu.setRoleGuid(roleGuid);
            mfRoleMenuService.csbrAddEntity(roleMenu);
            roleMenus.add(roleMenu);
        }
        mfRoleMenuService.saveBatch(roleMenus);
    }

    /**
     * 新增角色
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addRole(@Valid RoleAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }


        String roleGuid = CommonUtil.newGuid();
        //查看赋予的功能列表是否都存在
        if (check(dto,null)) {
            //添加角色菜单关系
            addRoleMenu(dto, roleGuid);
        }
        MfRole role = csbrBeanUtil.convert(dto, MfRole.class);
        mfRoleService.csbrAddEntity(role);
        //添加角色
        role.setGuid(roleGuid);
        boolean flag = mfRoleService.save(role);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("角色(%s)新增失败。", dto.getRoleName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改角色
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateRole(RoleUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }
        //查找更新的数据是否存在
        if (mfRoleService.isExistsData(Arrays.asList(dto.getGuid()), MfRole.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("角色(%s)不存在，无法更新。", dto.getRoleName()));

        }

        if (check(dto,dto.getGuid())) {
            //删除角色菜单
            LambdaQueryWrapper<MfRoleMenu> roleMenuWrapper = Wrappers.lambdaQuery();
            roleMenuWrapper.eq(MfRoleMenu::getRoleGuid, dto.getGuid());
            mfRoleMenuService.remove(roleMenuWrapper);
            //添加角色菜单关系
            addRoleMenu(dto, dto.getGuid());
        }

        //更新数据
        LambdaUpdateWrapper<MfRole> updateWrapper = mfRoleService.csbrUpdateWrapper(MfRole.class);
        updateWrapper.eq(MfRole::getGuid, dto.getGuid());

        MfRole role = csbrBeanUtil.convert(dto, MfRole.class);
        boolean flag = mfRoleService.update(role, updateWrapper);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("角色(%s)更新失败。", dto.getRoleName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 删除角色
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeRole(List<String> guids) {
        //查找更新的数据是否存在
        List<MfRole> lst = (List<MfRole>) mfRoleService.listByIds(guids);
        if (CollectionUtils.isEmpty(lst)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //检查应用端、角色是否是同一个
        List<String> sp = lst.stream().map(x -> x.getAppSideGuid()).collect(Collectors.toList());

        List<String> distinctSP = sp.stream().distinct().collect(Collectors.toList());
        if (distinctSP.size() > 0) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "只能批量删除同一应用端下的角色");

        }

        //删除数据
        LambdaUpdateWrapper<MfRole> updateWrapper=mfRoleService.csbrUpdateWrapper(MfRole.class);
        updateWrapper.in(MfRole::getGuid,guids);
        boolean flag = mfRoleService.remove(updateWrapper);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }


        return CommonRes.success(flag);

    }

    /**
     * 添加角色数据权限
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addRoleDataPermission(RoleDataPermissionAddDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }
        //检查角色是否存在
        if (!mfRoleService.isExistsData(Arrays.asList(dto.getRoleGuid()), MfRole.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, "角色关键字不存在，无法更新数据权限。");
        }
        //检查数据权限是否存在
        if (!mfDataPermissionService.isExistsData(dto.getDataPermissionGuids(), MfDataPermission.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, "数据权限关键字列表不存在，无法更新数据权限。");

        }
        //删除角色数据权限关系
        LambdaQueryWrapper<MfRoleDataPermission> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfRoleDataPermission::getRoleGuid, dto.getRoleGuid());
        mfRoleDataPermissionService.remove(queryWrapper);
        //新建角色数据权限关系
        MfRoleDataPermission rdp = null;
        List<MfRoleDataPermission> lst = new ArrayList<>();
        for (String str : dto.getDataPermissionGuids()) {
            rdp = new MfRoleDataPermission();
            rdp.setRoleGuid(dto.getRoleGuid());
            rdp.setDataPermissionGuid(str);
            mfRoleDataPermissionService.csbrAddEntity(rdp);
            lst.add(rdp);
        }
        //角色数据权限
        if(StringUtils.isNotEmpty(dto.getCustomDataPermissionGuid())) {
            rdp = new MfRoleDataPermission();
            rdp.setRoleGuid(dto.getRoleGuid());
            rdp.setDataPermissionGuid(dto.getCustomDataPermissionGuid());
            rdp.setCustomData(dto.getCustomData());
            mfRoleDataPermissionService.csbrAddEntity(rdp);
            lst.add(rdp);
        }
        boolean flag = false;
        if (CollectionUtils.isNotEmpty(lst)) {
            flag = mfRoleDataPermissionService.saveBatch(lst);
        }
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, "数据权限更新失败。");
        }

        return CommonRes.success(flag);
    }

    /**
     * 获取角色列表
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<RoleVO> getRoleList(RoleQueryDTO dto) {

        PageListVO<MfRole> lst = mfRoleService.csbrPageList(dto
                , mfRoleService.csbrQueryWrapper(dto, MfRole.class));
        return csbrBeanUtil.convert(lst, PageListVO.class);

    }

    /**
     * 查询角色详细
     *
     * @param guid
     * @return
     */
    @Override
    public RoleDetailVO getRoleDetail(String guid) throws InvocationTargetException, IllegalAccessException {

        //非空验证
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "角色Guid不能为空。");
        }

        //查询角色数据
        MfRole role = mfRoleService.getById(guid);
        RoleDetailVO vo = csbrBeanUtil.convert(role, RoleDetailVO.class);
        //根据角色Guid获取功能菜单
        List<MfRoleMenuPOJO> pojos = mfRoleMenuService.getRoleMenus(guid);

        List<MenuVO> menuVOS = ListToHierarchical.getHierarchical(pojos, "menuType", "F", MenuVO.class);

        List<? extends HierarchicalVO> hierarchicalVOS = menuVOS;
        vo.setChildren((List<HierarchicalVO>) hierarchicalVOS);

        return vo;
    }


}
