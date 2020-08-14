package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageDeliveryRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PStorageDeliveryRegionSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PStorageDeliveryRegionSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDistributionDTO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PStorageDeliveryRegionSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PStorageDeliveryRegionSumServiceImpl implements PStorageDeliveryRegionSumService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private PStorageDeliveryRegionSumMapper pStorageDeliveryRegionSumMapper;


    @Override
    public List<PStorageDeliveryRegionSum> getList(PStorageDeliveryRegionSumSO so) {
        return this.pStorageDeliveryRegionSumMapper.searchPStorageDeliveryRegionSums(so);
    }

    @Override
    public List<DeliveryDistributionDTO> getDeliveryDistribution() {
        return pStorageDeliveryRegionSumMapper.searchDeliveryDistribution();
    }


}