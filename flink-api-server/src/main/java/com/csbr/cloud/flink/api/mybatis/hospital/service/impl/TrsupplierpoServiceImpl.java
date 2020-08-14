package com.csbr.cloud.flink.api.mybatis.hospital.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.hospital.entity.Trsupplierpo;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.TrsupplierpoMapper;
import com.csbr.cloud.flink.api.mybatis.hospital.mapper.so.TrsupplierpoSO;
import com.csbr.cloud.flink.api.mybatis.hospital.service.TrsupplierpoService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("hospital")
public class TrsupplierpoServiceImpl implements TrsupplierpoService {

    /** 数据持久化对象 */
    @Autowired
    private TrsupplierpoMapper trsupplierpoMapper;


    @Override
    public List<Trsupplierpo> getList(TrsupplierpoSO so) {
        return this.trsupplierpoMapper.searchTrsupplierpos(so);
    }


}