package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfAppSideRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfUserRolePOJO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUserRole;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserRoleSO;

import java.util.List;

/**
 * 用户角色实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Component
@Mapper
@Repository
public interface MfUserRoleMapper extends BaseMapper<MfUserRole> {
    /**
     * 分页查询用户角色信息.
     *
     * @param so 查询条件
     *
     * @return 用户角色查询结果
     */
    <D extends BasePageDTO> IPage<MfUserRole> mapperPageMfUserRoles(Page<?> page, D so);

    List<MfAppSideRolePOJO> getUserRoleDetail(String tenantGuid, String userGuid,String appSideGuid);

    /**
     * 删除用户时，盘点企业用户角色ID是否都不存在，如果都不存在，则删除用户
     * @param guids
     */
    void delUserByUserRole(List<String> guids);

    List<MfUserRolePOJO> getUserRoleNames(String tenantGuid,List<String> guids);
}
