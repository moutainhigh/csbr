package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PTransrouteSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PTransrouteSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PTransrouteSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PTransrouteSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PTransrouteSumServiceImpl implements PTransrouteSumService {

    /** 数据持久化对象 */
    @Autowired
    private PTransrouteSumMapper pTransrouteSumMapper;


    @Override
    public List<PTransrouteSum> getList(PTransrouteSumSO so) {
        return this.pTransrouteSumMapper.searchPTransrouteSums(so);
    }


}