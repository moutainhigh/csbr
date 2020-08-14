package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PDeliverySumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PDeliverySumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PDeliverySumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PDeliverySumServiceImpl implements PDeliverySumService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private PDeliverySumMapper pDeliverySumMapper;


    @Override
    public List<PDeliverySum> getList(PDeliverySumSO so) {
        return this.pDeliverySumMapper.searchPDeliverySums(so);
    }

    @Override
    public PDeliverySum getNewest(PDeliverySumSO so) {
        return pDeliverySumMapper.searchPDeliverySum(so);
    }


}