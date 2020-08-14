package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfvehicletype;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMfvehicletypeMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicletypeSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMfvehicletypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 运输车辆类型业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */
@Service
@DS("transport")
public class TrMfvehicletypeServiceImpl implements TrMfvehicletypeService {

    /** 运输车辆类型数据持久化对象 */
    @Autowired
    private TrMfvehicletypeMapper trMfvehicletypeMapper;


    @Override
    public List<TrMfvehicletype> getList(TrMfvehicletypeSO so) {
        return this.trMfvehicletypeMapper.searchTrMfvehicletypes(so);
    }

    @Override
    public Boolean checkVehicleType(TrMfvehicletypeSO so) {

        List<TrMfvehicletype> mfvehicletypeList = trMfvehicletypeMapper.searchTrMfvehicletypes(so);

        return mfvehicletypeList.size() > 0 ? true : false;
    }


}