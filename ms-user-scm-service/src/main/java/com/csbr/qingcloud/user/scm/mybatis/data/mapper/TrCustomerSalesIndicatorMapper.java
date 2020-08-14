package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicators;

/**
 * 客户销售指标实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface TrCustomerSalesIndicatorMapper extends BaseMapper<TrCustomerSalesIndicators> {
    /**
     * 分页查询客户销售指标信息.
     *
     * @param so 查询条件
     *
     * @return 客户销售指标查询结果
     */
    <D extends BasePageDTO> IPage<TrCustomerSalesIndicators> mapperPageTrCustomerSalesIndicators(Page<?> page, D so);
}
