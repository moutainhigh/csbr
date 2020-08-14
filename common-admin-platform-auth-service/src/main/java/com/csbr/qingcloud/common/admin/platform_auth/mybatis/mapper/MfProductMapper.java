package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * 产品信息实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Component
@Mapper
@Repository
public interface MfProductMapper extends BaseMapper<MfProduct> {
    /**
     * 分页查询产品信息信息.
     *
     * @param so 查询条件
     *
     * @return 产品信息查询结果
     */
    <D extends BasePageDTO> IPage<MfProduct> mapperPageMfProducts(Page<?> page, D so);
}
