package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PCustomerTypeSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PCustomerTypeSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PCustomerTypeSumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PCustomerTypeSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PCustomerTypeSum> getList(PCustomerTypeSumSO so);
}