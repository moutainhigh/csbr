package com.csbr.cloud.flink.api.mybatis.transport.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfvehicle;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicleSO;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrMfvehicleMapper;

/**
 * 运输车辆资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface TrMfvehicleService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrMfvehicle> getList(TrMfvehicleSO so);
}