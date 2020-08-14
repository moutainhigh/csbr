package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfRoleDataPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 角色数据权限实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Component
@Mapper
@Repository
public interface MfRoleDataPermissionMapper extends BaseMapper<MfRoleDataPermission> {
    /**
     * 分页查询角色数据权限信息.
     *
     * @param so 查询条件
     *
     * @return 角色数据权限查询结果
     */
    <D extends BasePageDTO> IPage<MfRoleDataPermission> mapperPageMfRoleDataPermissions(Page<?> page, D so);
}
