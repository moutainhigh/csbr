package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMflogisticsoutlet;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMflogisticsoutletMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMflogisticsoutletSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMflogisticsoutletService;

/**
 * 物流网点业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("transport")
public class TrMflogisticsoutletServiceImpl implements TrMflogisticsoutletService {

    /** 物流网点数据持久化对象 */
    @Autowired
    private TrMflogisticsoutletMapper trMflogisticsoutletMapper;


    @Override
    public List<TrMflogisticsoutlet> getList(TrMflogisticsoutletSO so) {
        return this.trMflogisticsoutletMapper.searchTrMflogisticsoutlets(so);
    }


}