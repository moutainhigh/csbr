package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CsbrSumInfo;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CsbrSumInfoMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CsbrSumInfoSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.CsbrSumInfoService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class CsbrSumInfoServiceImpl implements CsbrSumInfoService {

    /** 数据持久化对象 */
    @Autowired
    private CsbrSumInfoMapper csbrSumInfoMapper;


    @Override
    public List<CsbrSumInfo> getList(CsbrSumInfoSO so) {
        return this.csbrSumInfoMapper.searchCsbrSumInfos(so);
    }


}