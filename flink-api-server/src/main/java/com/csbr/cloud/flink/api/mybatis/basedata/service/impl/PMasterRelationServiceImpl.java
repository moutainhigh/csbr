package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PMasterRelation;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PMasterRelationMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PMasterRelationSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.PMasterRelationService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class PMasterRelationServiceImpl implements PMasterRelationService {

    /** 数据持久化对象 */
    @Autowired
    private PMasterRelationMapper pMasterRelationMapper;


    @Override
    public List<PMasterRelation> getList(PMasterRelationSO so) {
        return this.pMasterRelationMapper.searchPMasterRelations(so);
    }


}