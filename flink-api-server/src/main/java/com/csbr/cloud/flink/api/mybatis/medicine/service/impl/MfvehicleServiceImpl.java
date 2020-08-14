package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mfvehicle;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MfvehicleMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfvehicleSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MfvehicleService;

/**
 * 运输车辆资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("medicine")
public class MfvehicleServiceImpl implements MfvehicleService {

    /** 运输车辆资料数据持久化对象 */
    @Autowired
    private MfvehicleMapper mfvehicleMapper;


    @Override
    public List<Mfvehicle> getList(MfvehicleSO so) {
        return this.mfvehicleMapper.searchMfvehicles(so);
    }


}