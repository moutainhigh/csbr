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
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserRemoveDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.AppSideRoleVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.RoleVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUser;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUserRole;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfAppSideRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfUserRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfUserRoleService;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfUserService;
import com.csbr.qingcloud.common.admin.platform_auth.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户服务
 * @author: yio
 * @create: 2020-07-28 14:12
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MfUserService mfUserService;

    @Autowired
    private MfUserRoleService mfUserRoleService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;

    /**
     * 新增用户账号
     *
     * @param dto
     * @return 返回添加成功的GUID
     */
    @Override
    public CommonRes<String> addUser(@Valid UserAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
//        //查看账号是否重复
//        LambdaQueryWrapper<MfUser> queryWrapper = Wrappers.lambdaQuery();
//        queryWrapper.eq(MfUser::getLogonUser, dto.getLogonUser());
//        queryWrapper.eq(MfUser::getPlatformGuid, dto.getPlatformGuid());
//        int cnt = mfUserService.count(queryWrapper);
//
//        if (cnt > 0) {
//            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("用户账号(%s)已存在。", dto.getLogonUser()));
//        }
//        //查看手机号是否重复
//        queryWrapper = Wrappers.lambdaQuery();
//        queryWrapper.eq(MfUser::getMobileNo, dto.getMobileNo());
//        queryWrapper.eq(MfUser::getPlatformGuid, dto.getPlatformGuid());
//        cnt = mfUserService.count(queryWrapper);
//        if (cnt > 0) {
//            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("用户手机号(%s)已存在。", dto.getMobileNo()));
//        }
        this.checkAddUsers(Arrays.asList(dto));

        MfUser user = csbrBeanUtil.convert(dto, MfUser.class);
        user.setPwd(CommonUtil.md5ForPwd(dto.getPwd()));
        user.setGuid(CommonUtil.newGuid());
        mfUserService.csbrAddEntity(user);
        boolean flag = mfUserService.save(user);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("用户账号(%s)新增失败。", dto.getLogonUser()));
        }
        //添加用户角色关系
        mfUserRoleService.addUserRole(dto.getTenantGuid(), user.getGuid(), dto.getRoleGuids());
        return CommonRes.success(user.getGuid());
    }


    /**
     * 修改用户账号
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateUser(UserUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }

        //查找更新的数据是否存在
        if (!mfUserService.isExistsData(Arrays.asList(dto.getGuid()), MfUser.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("用户账号(%s)不存在，无法更新。", dto.getLogonUser()));
        }

        //删除用户角色关系
        if (StringUtils.isNotEmpty(dto.getTenantGuid())) {
            LambdaQueryWrapper<MfUserRole> delQueryWrapper = Wrappers.lambdaQuery();
            delQueryWrapper.eq(MfUserRole::getTenantGuid, dto.getTenantGuid());
            delQueryWrapper.eq(MfUserRole::getUserGuid, dto.getGuid());
            mfUserRoleService.remove(delQueryWrapper);
        }

        //更新数据
        LambdaUpdateWrapper<MfUser> updateWrapper = mfUserService.csbrUpdateWrapper(MfUser.class);
        updateWrapper.eq(MfUser::getGuid, dto.getGuid());

        MfUser user = csbrBeanUtil.convert(dto, MfUser.class);
        user.setPwd(CommonUtil.md5ForPwd(dto.getPwd()));
        boolean flag = mfUserService.update(user, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("用户账号(%s)更新失败。", dto.getLogonUser()));
        }
        //添加用户角色关系
        mfUserRoleService.addUserRole(dto.getTenantGuid(), user.getGuid(), dto.getRoleGuids());
        return CommonRes.success(flag);
    }

    /**
     * 接收业务上传递的人员用户信息
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<String> updateRegCodeOrAddUser(UserAddDTO dto) {

        //查找更新的数据是否存在
        LambdaQueryWrapper<MfUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfUser::getName, dto.getName())
                .eq(MfUser::getMobileNo, dto.getMobileNo())
                .eq(MfUser::getPlatformGuid, dto.getPlatformGuid());
        MfUser user = mfUserService.getOne(queryWrapper);

        if (user == null) {
            //新增用户信息
            return addUser(dto);
        } else {
            //修改用户信息
            //删除用户角色关系
            if (StringUtils.isNotEmpty(dto.getTenantGuid())) {
                LambdaQueryWrapper<MfUserRole> delQueryWrapper = Wrappers.lambdaQuery();
                delQueryWrapper.eq(MfUserRole::getTenantGuid, dto.getTenantGuid());
                delQueryWrapper.eq(MfUserRole::getUserGuid, user.getGuid());
                mfUserRoleService.remove(delQueryWrapper);
            }

            //更新数据
            LambdaUpdateWrapper<MfUser> updateWrapper = mfUserService.csbrUpdateWrapper(MfUser.class);
            updateWrapper.eq(MfUser::getGuid, user.getGuid());

            MfUser updateUser = new MfUser();
            updateUser.setRegistrationCode(dto.getRegistrationCode());
            updateUser.setUpdateTime(user.getUpdateTime());
            user.setPwd(CommonUtil.md5ForPwd(dto.getPwd()));
            boolean flag = mfUserService.update(updateUser, updateWrapper);
            if (!flag) {
                throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("人员(%s)更新失败，可能已在其他终端更新，请重试。", dto.getName()));
            }
            //添加用户角色关系
            mfUserRoleService.addUserRole(dto.getTenantGuid(), user.getGuid(), dto.getRoleGuids());
            return CommonRes.success(user.getGuid());
        }

    }

    /**
     * 移除用户账号
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeUser(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfUserService.isExistsData(guids, MfUser.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfUser> updateWrapper = mfUserService.csbrUpdateWrapper(MfUser.class);
        updateWrapper.in(MfUser::getGuid, guids);
        boolean flag = mfUserService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 通过移除人员移除用户
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> removeUserByStaff(UserRemoveDTO dto) {
        //删除当前企业用户角色关系
        LambdaQueryWrapper<MfUserRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(MfUserRole::getUserGuid, dto.getGuids());
        queryWrapper.eq(MfUserRole::getTenantGuid, dto.getTenantGuid());
        mfUserRoleService.remove(queryWrapper);

        //如果用户没有关联角色，那么删除用户
        mfUserRoleService.delUserByUserRole(dto.getGuids());

        return CommonRes.success(true);

    }

    /**
     * 查询用户账号数据
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<UserVO> getUser(UserQueryDTO dto) {
        MfUserSO userQuery = csbrBeanUtil.convert(dto, MfUserSO.class);
        PageListVO<MfUser> lst = mfUserService.getMapperPageList(userQuery);
        return csbrBeanUtil.convert(lst, PageListVO.class);
    }

    /**
     * 获取用户详情，包含对应的端与角色
     *
     * @param guid
     * @param tenantGuid
     * @return
     */
    @Override
    public UserDetailVO getUserDetail(String guid, String tenantGuid) {
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "用户关键字不能为空");

        }
        if (StringUtils.isEmpty(tenantGuid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "企业关键字不能为空");

        }

        MfUser user = mfUserService.getById(guid);
        UserDetailVO vo = null;
        if (user != null) {
            vo = csbrBeanUtil.convert(user, UserDetailVO.class);
            //查询用户对应的端与角色
            List<MfAppSideRolePOJO> pojos = mfUserRoleService.getUserRoleDetail(tenantGuid, user.getGuid(), null);

            //获取到的集合已经按应用端排序了，循环集合组合对象
            if (CollectionUtils.isNotEmpty(pojos)) {
                List<AppSideRoleVO> lst = new ArrayList<>();
                vo.setAppSideRoleVOs(lst);
                String appSideGuid = "";
                MfAppSideRolePOJO pojo;
                for (int i = 0; i < pojos.size() - 1; i++) {
                    pojo = pojos.get(i);
                    AppSideRoleVO appSideRoleVO = null;
                    if (!appSideGuid.equals(pojo.getAppSideGuid())) {
                        appSideRoleVO = new AppSideRoleVO();
                        appSideRoleVO.setGuid(pojo.getAppSideGuid());
                        appSideRoleVO.setAppSideName(pojo.getAppSideName());
                        appSideRoleVO.setRoleVOs(new ArrayList<RoleVO>());
                        lst.add(appSideRoleVO);
                    } else {
                        appSideRoleVO = lst.get(lst.size() - 1);
                    }
                    appSideRoleVO.getRoleVOs().add(csbrBeanUtil.convert(pojo, RoleVO.class));
                }
            }

        } else {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "查看的用户数据不存在。");
        }
        return vo;
    }

    /**
     * 获取用户角色MAP
     *
     * @param tenantGuid,userGuids
     * @return key:用户GUID，value:角色名称列表
     */
    @Override
    public Map<String, List<String>> getUserRoleNames(String tenantGuid, List<String> userGuids) {
        List<MfUserRolePOJO> lst = mfUserRoleService.getUserRoleNames(tenantGuid, userGuids);
        return lst.stream().collect(Collectors.groupingBy(MfUserRolePOJO::getUserGuid,
                Collectors.mapping(MfUserRolePOJO::getRoleName, Collectors.toList())));
    }

    /**
     * 使用平台GUID，身份证，电话获取用户
     *
     * @param mobileNos
     * @param platGuid
     * @return
     */
    @Override
    public CommonRes<Map<String, String>> getUsersByIdMobilePlat(List<String> mobileNos, String platGuid) {
        LambdaQueryWrapper<MfUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfUser::getPlatformGuid, platGuid)
                .in(MfUser::getMobileNo, mobileNos)
                .select(MfUser::getGuid, MfUser::getIdCode, MfUser::getMobileNo);
        List<MfUser> users = mfUserService.list(queryWrapper);
        // 用手机号作为key，guid作为value，构造map
        Map<String, String> res = users.stream().collect(Collectors.toMap(MfUser::getMobileNo, MfUser::getGuid));
        return CommonRes.success(res);
    }

    /**
     * 批量添加用户
     *
     * @param dtos
     * @return
     */
    @Override
    public Map<String, String> batchAddUser(List<UserAddDTO> dtos) {
        this.checkAddUsers(dtos);

        List<MfUser> addUsers = csbrBeanUtil.convert(dtos, MfUser.class);

        Map<String, String> res = new HashMap<>();
        for (MfUser addUser : addUsers) {
            mfUserService.csbrAddEntity(addUser);
            // 初始密码6个8
            addUser.setPwd(CommonUtil.md5ForPwd("888888"));
            // 设为初始账号
            addUser.setIsInit("Y");
            // 登陆账号设为手机号
            addUser.setLogonUser(addUser.getMobileNo());
            // 构造返回map，key为手机号，value为用户GUID
            String guid = CommonUtil.newGuid();
            addUser.setGuid(guid);
            res.put(addUser.getMobileNo(), guid);
        }

        Boolean saveSuccess = mfUserService.saveBatch(addUsers);
        if (!saveSuccess) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, "批量添加用户失败");
        }

        return res;
    }

    // region 私有方法

    /**
     * 验证新增用户信息
     *
     * @param users
     */
    private void checkAddUsers(List<UserAddDTO> users) {
        List<String> logons = new ArrayList<>();
        List<String> mobiles = new ArrayList<>();
        List<String> platGuids = new ArrayList<>();
        for (UserAddDTO user : users) {
            logons.add(user.getLogonUser());
            mobiles.add(user.getMobileNo());
            platGuids.add(user.getPlatformGuid());
        }

        // 账号已存在信息
        LambdaQueryWrapper<MfUser> logonQuery = Wrappers.lambdaQuery();
        logonQuery.in(MfUser::getLogonUser, logons).in(MfUser::getPlatformGuid, platGuids)
                .select(MfUser::getLogonUser, MfUser::getPlatformGuid);
        List<MfUser> logonDatas = mfUserService.list(logonQuery);
        Set<String> logonExist = logonDatas.stream().map((u) -> u.getLogonUser() + u.getPlatformGuid())
                .collect(Collectors.toSet());
        // 手机号已存在信息
        LambdaQueryWrapper<MfUser> mobileQuery = Wrappers.lambdaQuery();
        mobileQuery.in(MfUser::getMobileNo, mobiles).in(MfUser::getPlatformGuid, platGuids)
                .select(MfUser::getMobileNo, MfUser::getPlatformGuid);
        List<MfUser> mobileDatas = mfUserService.list(mobileQuery);
        Set<String> mobileExist = mobileDatas.stream().map((u) -> u.getMobileNo() + u.getPlatformGuid())
                .collect(Collectors.toSet());

        List<String> errMsgs = new ArrayList<>();
        for (UserAddDTO user : users) {
            // 找出同一平台重复的账号
            if (logonExist.contains(user.getLogonUser() + user.getPlatformGuid())) {
                errMsgs.add(String.format("平台(%s)的账号(%s)重复", user.getPlatformGuid(), user.getLogonUser()));
            }
            // 找出同一平台重复的手机号
            if (mobileExist.contains(user.getMobileNo() + user.getPlatformGuid())) {
                errMsgs.add(String.format("平台(%s)的手机号(%s)重复", user.getPlatformGuid(), user.getMobileNo()));
                break;
            }
        }

        // 存在错误则抛出错误
        if (errMsgs.size() > 0) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.join("; ", errMsgs));
        }
    }
// endregion 私有方法
}
