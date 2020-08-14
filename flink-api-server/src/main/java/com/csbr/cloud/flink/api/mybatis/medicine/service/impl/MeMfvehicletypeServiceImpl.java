package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeMfvehicletype;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MeMfvehicletypeMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfvehicletypeSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MeMfvehicletypeService;

/**
 * 运输车辆类型业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */
@Service
@DS("medicine")
public class MeMfvehicletypeServiceImpl implements MeMfvehicletypeService {

    /**
     * 运输车辆类型数据持久化对象
     */
    @Autowired
    private MeMfvehicletypeMapper meMfvehicletypeMapper;


    @Override
    public List<MeMfvehicletype> getList(MeMfvehicletypeSO so) {
        return this.meMfvehicletypeMapper.searchMeMfvehicletypes(so);
    }

    @Override
    public Boolean checkVehicleType(MeMfvehicletypeSO so) {
        List<MeMfvehicletype> mfvehicletypeList = meMfvehicletypeMapper.searchMeMfvehicletypes(so);

        return mfvehicletypeList.size() > 0 ? true : false;
    }


}