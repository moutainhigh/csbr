package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.mybatis.basedata.model.WarehouseDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusStorageSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusStorageSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CusStorageSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class CusStorageSumServiceImpl implements CusStorageSumService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private CusStorageSumMapper cusStorageSumMapper;


    @Override
    public List<CusStorageSum> getList(CusStorageSumSO so) {
        return this.cusStorageSumMapper.searchCusStorageSums(so);
    }

    @Override
    public List<WarehouseDetailsDTO> getWarehouseDetails() {
        return cusStorageSumMapper.searchWarehouseDetails();
    }
}