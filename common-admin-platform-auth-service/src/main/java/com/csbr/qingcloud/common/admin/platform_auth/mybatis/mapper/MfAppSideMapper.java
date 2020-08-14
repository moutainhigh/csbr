package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfAppSide;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 应用端实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Component
@Mapper
@Repository
public interface MfAppSideMapper extends BaseMapper<MfAppSide> {
    /**
     * 分页查询应用端信息.
     *
     * @param so 查询条件
     *
     * @return 应用端查询结果
     */
    <D extends BasePageDTO> IPage<MfAppSide> mapperPageMfAppSides(Page<?> page, D so);
}
