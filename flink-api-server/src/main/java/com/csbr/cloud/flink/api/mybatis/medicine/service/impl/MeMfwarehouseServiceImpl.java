package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeMfwarehouse;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MeMfwarehouseMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfwarehouseSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MeMfwarehouseService;

/**
 * 仓库资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("medicine")
public class MeMfwarehouseServiceImpl implements MeMfwarehouseService {

    /** 仓库资料数据持久化对象 */
    @Autowired
    private MeMfwarehouseMapper meMfwarehouseMapper;


    @Override
    public List<MeMfwarehouse> getList(MeMfwarehouseSO so) {
        return this.meMfwarehouseMapper.searchMeMfwarehouses(so);
    }


}