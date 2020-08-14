package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.SalesFlowWithDetailPOJO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;

import java.util.HashMap;
import java.util.List;

/**
 * 销售流量实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface TrSalesFlowMapper extends BaseMapper<TrSalesFlow> {
    /**
     * 分页查询销售流量信息.
     *
     * @param so 查询条件
     *
     * @return 销售流量查询结果
     */
    <D extends BasePageDTO> IPage<TrSalesFlow> mapperPageTrSalesFlows(Page<?> page, D so);

    /**
     * 分页查询销售流量与明细信息.
     *
     * @param so 查询条件
     *
     * @return 销售流量查询结果
     */
    <D extends BasePageDTO> IPage<SalesFlowWithDetailPOJO> getTrSalesFlowsWithDetail(Page<?> page, D so);


    /**
     * 按条件获取销售流量的客户与核算金额
     * @param so
     * @return
     */
    List<HashMap> getCustomerWithAmount(TrSalesFlowSO so);

    /**
     * 按条件获取产品数
     * @param so
     * @return
     */
    Integer getGoodsNum(TrSalesFlowSO so);
}
