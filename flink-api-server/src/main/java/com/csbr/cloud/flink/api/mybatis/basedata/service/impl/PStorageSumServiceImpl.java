package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PStorageSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PStorageSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PStorageSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PStorageSumServiceImpl implements PStorageSumService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private PStorageSumMapper pStorageSumMapper;


    @Override
    public List<PStorageSum> getList(PStorageSumSO so) {
        return this.pStorageSumMapper.searchPStorageSums(so);
    }

    @Override
    public PStorageSum getNewest(PStorageSumSO so) {
        return pStorageSumMapper.searchPStorageSum(so);
    }


}