package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusRelation;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusRelationMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusRelationSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CusRelationService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class CusRelationServiceImpl implements CusRelationService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private CusRelationMapper cusRelationMapper;


    @Override
    public List<CusRelation> getList(CusRelationSO so) {
        return this.cusRelationMapper.searchCusRelations(so);
    }

    @Override
    public String getGUIDBySource(CusRelationSO so) {
        String res = "";
        List<CusRelation> cusRelationList = cusRelationMapper.searchCusRelations(so);

        if (cusRelationList.size() > 0) {
            res = cusRelationList.get(0).getGuid();
        }

        return res;
    }


}