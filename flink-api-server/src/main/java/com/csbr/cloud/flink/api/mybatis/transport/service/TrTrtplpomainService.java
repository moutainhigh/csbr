package com.csbr.cloud.flink.api.mybatis.transport.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplpomain;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplpomainSO;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrTrtplpomainMapper;

/**
 * 物流订单主业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrTrtplpomainService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrTrtplpomain> getList(TrTrtplpomainSO so);
}