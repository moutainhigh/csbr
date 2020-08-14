package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CsGoodsRegionSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CsGoodsRegionSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CsGoodsRegionSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CsGoodsRegionSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class CsGoodsRegionSumServiceImpl implements CsGoodsRegionSumService {

    /** 数据持久化对象 */
    @Autowired
    private CsGoodsRegionSumMapper csGoodsRegionSumMapper;


    @Override
    public List<CsGoodsRegionSum> getList(CsGoodsRegionSumSO so) {
        return this.csGoodsRegionSumMapper.searchCsGoodsRegionSums(so);
    }


}