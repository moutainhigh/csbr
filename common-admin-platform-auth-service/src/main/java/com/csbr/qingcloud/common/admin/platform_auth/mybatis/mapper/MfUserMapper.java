package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUser;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserSO;

/**
 * 用户实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Component
@Mapper
@Repository
public interface MfUserMapper extends BaseMapper<MfUser> {
    /**
     * 分页查询用户信息.
     *
     * @param so 查询条件
     *
     * @return 用户查询结果
     */
    <D extends BasePageDTO> IPage<MfUser> mapperPageMfUsers(Page<?> page, D so);
}
