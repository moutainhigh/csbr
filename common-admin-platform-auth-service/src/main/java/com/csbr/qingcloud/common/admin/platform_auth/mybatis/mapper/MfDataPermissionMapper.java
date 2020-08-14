package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfDataPermission;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfRoleDataPermissionPOJO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * 数据权限实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Component
@Mapper
@Repository
public interface MfDataPermissionMapper extends BaseMapper<MfDataPermission> {
    /**
     * 分页查询数据权限信息.
     *
     * @param so 查询条件
     *
     * @return 数据权限查询结果
     */
    <D extends BasePageDTO> IPage<MfDataPermission> mapperPageMfDataPermissions(Page<?> page, D so);

    /**
     * 通过角色获取数据权限
     * @param roleGuid
     * @return
     */
    List<MfRoleDataPermissionPOJO> getDataPermissionsByRole(String roleGuid);

}
