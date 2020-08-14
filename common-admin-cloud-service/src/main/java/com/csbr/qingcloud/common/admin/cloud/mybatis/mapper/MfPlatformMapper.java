package com.csbr.qingcloud.common.admin.cloud.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.common.admin.cloud.mybatis.entity.MfPlatform;

/**
 * 平台资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Component
@Mapper
@Repository
public interface MfPlatformMapper extends BaseMapper<MfPlatform> {
    /**
     * 分页查询平台资料信息.
     *
     * @param so 查询条件
     *
     * @return 平台资料查询结果
     */
    <D extends BasePageDTO> IPage<MfPlatform> mapperPageMfPlatforms(Page<?> page, D so);
}
