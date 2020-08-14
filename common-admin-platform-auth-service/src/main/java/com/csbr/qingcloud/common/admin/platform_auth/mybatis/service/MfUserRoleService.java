package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUserRole;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfAppSideRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfUserRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserRoleSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfUserRoleMapper;

import java.util.List;

/**
 * 用户角色业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

public interface MfUserRoleService extends CsbrService<MfUserRole> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 添加用户角色关系
     * @param tenantGuid
     * @param userGuid
     * @param roleGuids
     * @return
     */
    boolean addUserRole(String tenantGuid, String userGuid, List<String> roleGuids);

    /**
     * 获取用户角色关系详情
     * @param tenantGuid
     * @param userGuid
     * @return
     */
    List<MfAppSideRolePOJO> getUserRoleDetail(String tenantGuid, String userGuid,String appSideGuid);


    /**
     * 删除用户时，当用户对应的角色都存在了，那么用户的的删除标志被设置成已删除
     * @param guid
     */
    void delUserByUserRole(List<String> guid);

    /**
     * 获取用户角色名列表
     * @param tenantGuid
     * @param guids
     * @return
     */
    List<MfUserRolePOJO> getUserRoleNames(String tenantGuid, List<String> guids);

}