package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicatorsDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorsDetailSO;

/**
 * 人员销售指标明细实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-08-13
 */

@Component
@Mapper
@Repository
public interface TrStaffSalesIndicatorsDetailMapper extends BaseMapper<TrStaffSalesIndicatorsDetail> {
    /**
     * 分页查询人员销售指标明细信息.
     *
     * @param so 查询条件
     *
     * @return 人员销售指标明细查询结果
     */
    <D extends BasePageDTO> IPage<TrStaffSalesIndicatorsDetail> mapperPageTrStaffSalesIndicatorsDetails(Page<?> page, D so);
}
