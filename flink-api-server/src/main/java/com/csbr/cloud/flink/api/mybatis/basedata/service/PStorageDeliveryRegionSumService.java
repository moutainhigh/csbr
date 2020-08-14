package com.csbr.cloud.flink.api.mybatis.basedata.service;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageDeliveryRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PStorageDeliveryRegionSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDistributionDTO;

import java.util.List;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PStorageDeliveryRegionSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PStorageDeliveryRegionSum> getList(PStorageDeliveryRegionSumSO so);

    /**
     * 查询平台运力仓库分布
     *
     * @return
     */
    List<DeliveryDistributionDTO> getDeliveryDistribution();
}