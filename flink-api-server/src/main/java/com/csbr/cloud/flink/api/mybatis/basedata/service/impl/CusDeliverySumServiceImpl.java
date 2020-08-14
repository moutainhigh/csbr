package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusDeliverySumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusDeliverySumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CusDeliverySumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class CusDeliverySumServiceImpl implements CusDeliverySumService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private CusDeliverySumMapper cusDeliverySumMapper;


    @Override
    public List<CusDeliverySum> getList(CusDeliverySumSO so) {
        return this.cusDeliverySumMapper.searchCusDeliverySums(so);
    }

    @Override
    public List<DeliveryDetailsDTO> getDeliveryDetails() {
        return cusDeliverySumMapper.searchDeliveryDetails();
    }


}