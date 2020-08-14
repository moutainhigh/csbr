package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.SalesFlowWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.StaffSalesIndicatorsWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorSO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicators;

import java.util.HashMap;
import java.util.List;

/**
 * 人员销售指标实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface TrStaffSalesIndicatorMapper extends BaseMapper<TrStaffSalesIndicators> {
    /**
     * 分页查询人员销售指标信息.
     *
     * @param so 查询条件
     *
     * @return 人员销售指标查询结果
     */
    <D extends BasePageDTO> IPage<TrStaffSalesIndicators> mapperPageTrStaffSalesIndicators(Page<?> page, D so);

    /**
     * 分页查询人员销售指标与明细信息.
     *
     * @param so 查询条件
     *
     * @return 人员销售指标查询结果
     */
    <D extends BasePageDTO> IPage<StaffSalesIndicatorsWithDetailPOJO> getStaffSalesIndicatorsWithDetail(Page<?> page, D so);


    /**
     * 按条件获取人员销售指标的人员对应的销售指标
     * @param so
     * @return
     */
    List<HashMap> getStaffWithIndicators(TrStaffSalesIndicatorSO so);

    /**
     * 按条件获取产品数
     * @param so
     * @return
     */
    Integer getGoodsNum(TrStaffSalesIndicatorSO so);
}
