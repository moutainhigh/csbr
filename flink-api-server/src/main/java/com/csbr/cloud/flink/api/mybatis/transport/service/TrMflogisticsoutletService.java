package com.csbr.cloud.flink.api.mybatis.transport.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMflogisticsoutlet;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMflogisticsoutletSO;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMflogisticsoutletMapper;

/**
 * 物流网点业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrMflogisticsoutletService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrMflogisticsoutlet> getList(TrMflogisticsoutletSO so);
}