package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Trtplpomain;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.TrtplpomainMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.TrtplpomainSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.TrtplpomainService;

/**
 * 物流订单主业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("medicine")
public class TrtplpomainServiceImpl implements TrtplpomainService {

    /** 物流订单主数据持久化对象 */
    @Autowired
    private TrtplpomainMapper trtplpomainMapper;


    @Override
    public List<Trtplpomain> getList(TrtplpomainSO so) {
        return this.trtplpomainMapper.searchTrtplpomains(so);
    }


}