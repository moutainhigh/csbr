package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfwarehouse;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMfwarehouseMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfwarehouseSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrMfwarehouseService;

/**
 * 仓库资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("transport")
public class TrMfwarehouseServiceImpl implements TrMfwarehouseService {

    /** 仓库资料数据持久化对象 */
    @Autowired
    private TrMfwarehouseMapper trMfwarehouseMapper;


    @Override
    public List<TrMfwarehouse> getList(TrMfwarehouseSO so) {
        return this.trMfwarehouseMapper.searchTrMfwarehouses(so);
    }


}