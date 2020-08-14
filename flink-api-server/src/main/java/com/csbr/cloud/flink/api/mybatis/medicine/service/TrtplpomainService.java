package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Trtplpomain;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.TrtplpomainSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.TrtplpomainMapper;

/**
 * 物流订单主业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrtplpomainService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Trtplpomain> getList(TrtplpomainSO so);
}