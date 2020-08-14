package com.csbr.qingcloud.common.admin.platform_auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.util.ListToHierarchical;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.menu.MenuUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.MenuVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfAppSide;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfMenu;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfProduct;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfSideProduct;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfMenuSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfAppSideService;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfMenuService;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfProductService;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfSideProductService;
import com.csbr.qingcloud.common.admin.platform_auth.service.MenuService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: common-admin-platform-auth-service
 * @description: 菜单服务
 * @author: yio
 * @create: 2020-07-21 14:01
 **/
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MfMenuService mfMenuService;

    @Autowired
    private MfProductService mfProductService;

    @Autowired
    private MfAppSideService mfAppSideService;

    @Autowired
    private MfSideProductService mfSideProductService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;


    /**
     * 新增菜单
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addMenu(@Valid MenuAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
        //检查产品是否存在
        if (!mfProductService.isExistsData(Arrays.asList(dto.getProductGuid()), MfProduct.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("菜单(%s)对应的产品不存在。", dto.getMenuName()));

        }
        //检查应用端是否存在
        if (!mfAppSideService.isExistsData(Arrays.asList(dto.getAppSideGuid()), MfAppSide.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("菜单(%s)对应的应用端不存在。", dto.getMenuName()));

        }

        //检查父节点ID是否存在
        if (StringUtils.isNotEmpty(dto.getParentGuid())) {
            MfMenuSO so = new MfMenuSO();
            so.setParentGuid(dto.getParentGuid());
            LambdaQueryWrapper<MfMenu> menuQueryWrapper = mfMenuService.csbrQueryWrapper(so, MfMenu.class);
            if (mfMenuService.count(menuQueryWrapper) == 0) {
                throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("菜单(%s)对应的父节点不存在。", dto.getMenuName()));
            }
        }
        //添加应用端产品关系
        LambdaQueryWrapper<MfSideProduct> sideProductWrapper = Wrappers.lambdaQuery();
        sideProductWrapper.eq(MfSideProduct::getAppSideGuid, dto.getAppSideGuid());
        sideProductWrapper.eq(MfSideProduct::getProductGuid, dto.getProductGuid());
        if (mfSideProductService.count() == 0) {
            MfSideProduct sideProduct = new MfSideProduct();
            sideProduct.setAppSideGuid(dto.getAppSideGuid());
            sideProduct.setProductGuid(dto.getProductGuid());
            mfSideProductService.csbrAddEntity(sideProduct);
            mfSideProductService.save(sideProduct);
        }
        MfMenu menu = csbrBeanUtil.convert(dto, MfMenu.class);
        mfMenuService.csbrAddEntity(menu);
        //添加菜单
        boolean flag = mfMenuService.save(menu);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("菜单(%s)新增失败。", dto.getMenuName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改菜单
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateMenu(MenuUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }
        //查找更新的数据是否存在
        MfMenu menu = mfMenuService.getById(dto.getGuid());
        if (menu == null) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("菜单(%s)不存在，无法更新。", dto.getMenuName()));
        }
        //检查产品、应用端与父节点是否发生了变化，不允许变化
        if (StringUtils.isNotEmpty(dto.getAppSideGuid()) && !dto.getAppSideGuid().equals(menu.getAppSideGuid())) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("不允许修改菜单(%s)的应用端。", dto.getMenuName()));

        }
        if (StringUtils.isNotEmpty(dto.getProductGuid()) && !dto.getProductGuid().equals(menu.getProductGuid())) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("不允许修改菜单(%s)的产品。", dto.getMenuName()));

        }
        if (StringUtils.isNotEmpty(dto.getParentGuid()) && !dto.getParentGuid().equals(menu.getParentGuid())) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("不允许修改菜单(%s)的父节点。", dto.getMenuName()));

        }

        //更新数据
        LambdaUpdateWrapper<MfMenu> updateWrapper = mfMenuService.csbrUpdateWrapper(MfMenu.class);
        updateWrapper.eq(MfMenu::getGuid, dto.getGuid());

        menu = csbrBeanUtil.convert(dto, MfMenu.class);
        boolean flag = mfMenuService.update(menu, updateWrapper);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("菜单(%s)更新失败。", dto.getMenuName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 删除菜单
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeMenu(List<String> guids) {
        //查找更新的数据是否存在
        List<MfMenu> lst = (List<MfMenu>) mfMenuService.listByIds(guids);
        if (CollectionUtils.isEmpty(lst)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //检查应用端、产品是否是同一个
        List<String> sp = lst.stream().map(x -> x.getAppSideGuid() + "_" + x.getProductGuid()).collect(Collectors.toList());

        List<String> distinctSP = sp.stream().distinct().collect(Collectors.toList());
        if (distinctSP.size() > 0) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "只能批量删除同一应用端产品下的菜单");

        }

        //检查菜单有无子节点，如果存在无法删除
        LambdaQueryWrapper<MfMenu> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(MfMenu::getParentGuid, lst.stream().map(x -> x.getGuid()).collect(Collectors.toList()));
        if (mfMenuService.count(queryWrapper) > 0) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, "菜单存在子节点，无法删除。");

        }

        //删除数据
        LambdaUpdateWrapper<MfMenu> updateWrapper=mfMenuService.csbrUpdateWrapper(MfMenu.class);
        updateWrapper.in(MfMenu::getGuid,guids);
        boolean flag = mfMenuService.remove(updateWrapper);

        //检查菜单标钟应用端产品关系是否不存在，如果不存在，那么清除应用端产品关系数据
        LambdaQueryWrapper<MfMenu> menuQueryWrapper = Wrappers.lambdaQuery();
        menuQueryWrapper.eq(MfMenu::getAppSideGuid, lst.get(0).getAppSideGuid())
                .eq(MfMenu::getProductGuid, lst.get(0).getProductGuid());
        if (mfMenuService.count(menuQueryWrapper) == 0) {
            //删除应用端产品关系
            LambdaUpdateWrapper<MfSideProduct> sideProductWrapper = mfSideProductService.csbrUpdateWrapper(MfSideProduct.class);
            sideProductWrapper.eq(MfSideProduct::getAppSideGuid, lst.get(0).getAppSideGuid())
                    .eq(MfSideProduct::getProductGuid, lst.get(0).getProductGuid());
            flag = flag && mfSideProductService.remove(sideProductWrapper);
        }

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }


        return CommonRes.success(flag);

    }

    /**
     * 查询菜单
     *
     * @param dto
     * @return
     */
    @Override
    public List<MenuVO> getMenu(MenuQueryDTO dto) throws InvocationTargetException, IllegalAccessException {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }

        LambdaQueryWrapper<MfMenu> queryWrapper = mfMenuService.csbrQueryWrapper(dto, MfMenu.class);
        queryWrapper.orderByAsc(MfMenu::getProductGuid, MfMenu::getParentGuid, MfMenu::getOrderNum, MfMenu::getCreateTime);

        List<MfMenu> lst = mfMenuService.list(queryWrapper);

        //遍历列表，构建父子关系
        return ListToHierarchical.getHierarchical(lst,"menuType","F",MenuVO.class);


    }

}
