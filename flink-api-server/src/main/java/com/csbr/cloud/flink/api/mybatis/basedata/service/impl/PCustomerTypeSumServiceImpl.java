package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PCustomerTypeSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PCustomerTypeSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PCustomerTypeSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PCustomerTypeSumService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PCustomerTypeSumServiceImpl implements PCustomerTypeSumService {

    /** 数据持久化对象 */
    @Autowired
    private PCustomerTypeSumMapper pCustomerTypeSumMapper;


    @Override
    public List<PCustomerTypeSum> getList(PCustomerTypeSumSO so) {
        return this.pCustomerTypeSumMapper.searchPCustomerTypeSums(so);
    }


}