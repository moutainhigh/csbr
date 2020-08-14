package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CsbrSumInfo;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CsbrSumInfoSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CsbrSumInfoMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface CsbrSumInfoService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<CsbrSumInfo> getList(CsbrSumInfoSO so);
}