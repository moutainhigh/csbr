package com.csbr.cloud.flink.api.mybatis.transport.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfwarehouse;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfwarehouseSO;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMfwarehouseMapper;

/**
 * 仓库资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrMfwarehouseService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrMfwarehouse> getList(TrMfwarehouseSO so);
}