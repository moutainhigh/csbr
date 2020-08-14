package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfvehicle;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMfvehicleMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicleSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMfvehicleService;

/**
 * 运输车辆资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("transport")
public class TrMfvehicleServiceImpl implements TrMfvehicleService {

    /** 运输车辆资料数据持久化对象 */
    @Autowired
    private TrMfvehicleMapper trMfvehicleMapper;


    @Override
    public List<TrMfvehicle> getList(TrMfvehicleSO so) {
        return this.trMfvehicleMapper.searchTrMfvehicles(so);
    }


}