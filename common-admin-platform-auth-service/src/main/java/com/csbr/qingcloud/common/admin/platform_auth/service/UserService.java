package com.csbr.qingcloud.common.admin.platform_auth.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserRemoveDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.user.UserUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserDetailVO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.UserVO;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: common-admin-platform-auth-service
 * @description: 用户服务
 * @author: yio
 * @create: 2020-07-28 13:43
 **/
public interface UserService {
    /**
     * 新增用户
     *
     * @param dto
     * @return
     */
    CommonRes<String> addUser(@Valid UserAddDTO dto);

    /**
     * 修改用户
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateUser(UserUpdateDTO dto);

    /**
     * 接收业务上传递的人员用户信息
     *
     * @param dto
     * @return
     */
    CommonRes<String> updateRegCodeOrAddUser(UserAddDTO dto);

    /**
     * 删除用户
     *
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeUser(List<String> guids);

    /**
     * 查询用户
     *
     * @param dto
     * @return
     */
    PageListVO<UserVO> getUser(UserQueryDTO dto);

    /**
     * 获取用户详情，包含对应的端与角色
     *
     * @param guid
     * @param tenantGuid
     * @return
     */
    UserDetailVO getUserDetail(String guid, String tenantGuid);

    /**
     * 通过移除人员移除用户
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> removeUserByStaff(UserRemoveDTO dto);

    /**
     * 获取用户角色MAP
     *
     * @param tenantGuid,userGuids
     * @return key:用户GUID，value:角色名称列表
     */
    Map<String, List<String>> getUserRoleNames(String tenantGuid, List<String> userGuids);

    /**
     * 使用平台GUID，身份证，电话获取用户
     *
     * @param mobileNos
     * @param platGuid
     * @return
     */
    CommonRes<Map<String, String>> getUsersByIdMobilePlat(List<String> mobileNos, String platGuid);

    /**
     * 批量添加用户
     *
     * @param dtos
     * @return
     */
    Map<String, String> batchAddUser(List<UserAddDTO> dtos);
}
