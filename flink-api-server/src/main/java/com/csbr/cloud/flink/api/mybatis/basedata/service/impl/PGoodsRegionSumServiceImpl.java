package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PGoodsRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PGoodsRegionSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PGoodsRegionSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PGoodsRegionSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PGoodsRegionSumServiceImpl implements PGoodsRegionSumService {

    /** 数据持久化对象 */
    @Autowired
    private PGoodsRegionSumMapper pGoodsRegionSumMapper;


    @Override
    public List<PGoodsRegionSum> getList(PGoodsRegionSumSO so) {
        return this.pGoodsRegionSumMapper.searchPGoodsRegionSums(so);
    }


}