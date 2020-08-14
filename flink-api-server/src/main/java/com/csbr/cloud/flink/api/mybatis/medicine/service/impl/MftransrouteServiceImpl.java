package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mftransroute;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MftransrouteMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MftransrouteSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MftransrouteService;

/**
 * 配送路线资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("medicine")
public class MftransrouteServiceImpl implements MftransrouteService {

    /** 配送路线资料数据持久化对象 */
    @Autowired
    private MftransrouteMapper mftransrouteMapper;


    @Override
    public List<Mftransroute> getList(MftransrouteSO so) {
        return this.mftransrouteMapper.searchMftransroutes(so);
    }


}