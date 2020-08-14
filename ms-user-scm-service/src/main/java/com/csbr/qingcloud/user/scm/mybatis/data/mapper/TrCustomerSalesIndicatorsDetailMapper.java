package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicatorsDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrCustomerSalesIndicatorsDetailSO;

/**
 * 客户销售指标明细实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Component
@Mapper
@Repository
public interface TrCustomerSalesIndicatorsDetailMapper extends BaseMapper<TrCustomerSalesIndicatorsDetail> {
    /**
     * 分页查询客户销售指标明细信息.
     *
     * @param so 查询条件
     *
     * @return 客户销售指标明细查询结果
     */
    <D extends BasePageDTO> IPage<TrCustomerSalesIndicatorsDetail> mapperPageTrCustomerSalesIndicatorsDetails(Page<?> page, D so);
}
