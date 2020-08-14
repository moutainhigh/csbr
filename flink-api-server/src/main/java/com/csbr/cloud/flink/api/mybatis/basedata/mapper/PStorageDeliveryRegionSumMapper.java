package com.csbr.cloud.flink.api.mybatis.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDistributionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageDeliveryRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PStorageDeliveryRegionSumSO;

/**
 * 实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

@Component
@Mapper
@Repository
public interface PStorageDeliveryRegionSumMapper extends BaseMapper<PStorageDeliveryRegionSum> {
    /**
     * 查询信息.
     *
     * @param so 查询条件
     * @return 查询结果
     */
    List<PStorageDeliveryRegionSum> searchPStorageDeliveryRegionSums(PStorageDeliveryRegionSumSO so);

    /**
     * 查询最后一条数据
     *
     * @param so
     * @return
     */
    PStorageDeliveryRegionSum searchPStorageDeliveryRegionSum(PStorageDeliveryRegionSumSO so);

    /**
     * 查询平台运力仓库分布
     *
     * @return
     */
    List<DeliveryDistributionDTO> searchDeliveryDistribution();
}
