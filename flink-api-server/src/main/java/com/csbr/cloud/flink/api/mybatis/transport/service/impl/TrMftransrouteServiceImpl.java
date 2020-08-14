package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMftransroute;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMftransrouteMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMftransrouteSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMftransrouteService;

/**
 * 配送路线资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("transport")
public class TrMftransrouteServiceImpl implements TrMftransrouteService {

    /** 配送路线资料数据持久化对象 */
    @Autowired
    private TrMftransrouteMapper trMftransrouteMapper;


    @Override
    public List<TrMftransroute> getList(TrMftransrouteSO so) {
        return this.trMftransrouteMapper.searchTrMftransroutes(so);
    }


}