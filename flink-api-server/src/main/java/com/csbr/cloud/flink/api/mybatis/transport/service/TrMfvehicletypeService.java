package com.csbr.cloud.flink.api.mybatis.transport.service;

import com.csbr.cloud.flink.api.mybatis.transport.entity.TrMfvehicletype;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrMfvehicletypeSO;

import java.util.List;

/**
 * 运输车辆类型业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

public interface TrMfvehicletypeService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<TrMfvehicletype> getList(TrMfvehicletypeSO so);

    /**
     * mfvehicle 表关联 mfvehicletype 是否存在
     *
     * @param so
     * @return
     */
    Boolean checkVehicleType(TrMfvehicletypeSO so);
}