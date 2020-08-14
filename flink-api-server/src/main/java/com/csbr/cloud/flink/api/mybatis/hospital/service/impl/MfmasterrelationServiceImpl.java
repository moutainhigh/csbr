package com.csbr.cloud.flink.api.mybatis.hospital.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.hospital.entity.Mfmasterrelation;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.MfmasterrelationMapper;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.MfmasterrelationSO;
import com.csbr.cloud.flink.api.mybatis.hospital.service.MfmasterrelationService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("hospital")
public class MfmasterrelationServiceImpl implements MfmasterrelationService {

    /** 数据持久化对象 */
    @Autowired
    private MfmasterrelationMapper mfmasterrelationMapper;


    @Override
    public List<Mfmasterrelation> getList(MfmasterrelationSO so) {
        return this.mfmasterrelationMapper.searchMfmasterrelations(so);
    }


}