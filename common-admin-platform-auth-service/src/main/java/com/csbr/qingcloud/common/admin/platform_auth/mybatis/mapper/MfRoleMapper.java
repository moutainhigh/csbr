package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 角色实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Component
@Mapper
@Repository
public interface MfRoleMapper extends BaseMapper<MfRole> {
    /**
     * 分页查询角色信息.
     *
     * @param so 查询条件
     *
     * @return 角色查询结果
     */
    <D extends BasePageDTO> IPage<MfRole> mapperPageMfRoles(Page<?> page, D so);

}
