package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplpomain;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrTrtplpomainMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplpomainSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrTrtplpomainService;

/**
 * 物流订单主业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("transport")
public class TrTrtplpomainServiceImpl implements TrTrtplpomainService {

    /** 物流订单主数据持久化对象 */
    @Autowired
    private TrTrtplpomainMapper trTrtplpomainMapper;


    @Override
    public List<TrTrtplpomain> getList(TrTrtplpomainSO so) {
        return this.trTrtplpomainMapper.searchTrTrtplpomains(so);
    }


}