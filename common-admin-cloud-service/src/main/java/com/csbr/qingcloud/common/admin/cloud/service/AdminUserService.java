package com.csbr.qingcloud.common.admin.cloud.service;

import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminLoginDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser.AdminUserUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.AdminUserVO;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 平台用户服务
 * @author: yio
 * @create: 2020-07-20 11:44
 **/
public interface AdminUserService {
    /**
     * 新增平台用户
     * @param dto
     * @return
     */
    CommonRes<Boolean> addAdminUser(@Valid AdminUserAddDTO dto);

    /**
     * 新增平台用户
     * @param dto
     * @return
     */
    CommonRes<Boolean> addAdminUser(@Valid AdminUserAddDTO dto,boolean isInit);
    /**
     * 更新平台用户信息
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateAdminUser(AdminUserUpdateDTO dto);

    /**
     * 删除平台用户信息
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeAdminUser(List<String> guids);

    /**
     * 查询平台用户信息
     * @param dto
     * @return
     */
    PageListVO<AdminUserVO> getAdminUser(AdminUserQueryDTO dto);

    /**
     * 管理员登陆
     *
     * @param dto
     * @return
     */
    CommonRes adminLogin(AdminLoginDTO dto);
}
