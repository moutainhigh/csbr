package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mfmasterdata;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MfmasterdataMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfmasterdataSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MfmasterdataService;

/**
 * 主数据资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("medicine")
public class MfmasterdataServiceImpl implements MfmasterdataService {

    /** 主数据资料数据持久化对象 */
    @Autowired
    private MfmasterdataMapper mfmasterdataMapper;


    @Override
    public List<Mfmasterdata> getList(MfmasterdataSO so) {
        return this.mfmasterdataMapper.searchMfmasterdatas(so);
    }


}