package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PPurchaseGoodsSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PPurchaseGoodsSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PPurchaseGoodsSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PPurchaseGoodsSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PPurchaseGoodsSumServiceImpl implements PPurchaseGoodsSumService {

    /** 数据持久化对象 */
    @Autowired
    private PPurchaseGoodsSumMapper pPurchaseGoodsSumMapper;


    @Override
    public List<PPurchaseGoodsSum> getList(PPurchaseGoodsSumSO so) {
        return this.pPurchaseGoodsSumMapper.searchPPurchaseGoodsSums(so);
    }


}