package com.csbr.qingcloud.common.admin.cloud.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfAdminUser;

/**
 * 平台用户实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Component
@Mapper
@Repository
public interface MfAdminUserMapper extends BaseMapper<MfAdminUser> {
    /**
     * 分页查询平台用户信息.
     *
     * @param so 查询条件
     *
     * @return 平台用户查询结果
     */
    <D extends BasePageDTO> IPage<MfAdminUser> mapperPageMfAdminUsers(Page<?> page, D so);

    /**
     * 查询平台用户，显示平台名称
     * @param page
     * @param so
     * @param <D>
     * @return
     */
    <D extends BasePageDTO> IPage<MfAdminUser> getDataWithPlatformName(Page<?> page, D so);
}
