package com.csbr.cloud.flink.api.mybatis.medicine.service;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeMfvehicletype;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeMfvehicletypeSO;

import java.util.List;

/**
 * 运输车辆类型业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */

public interface MeMfvehicletypeService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<MeMfvehicletype> getList(MeMfvehicletypeSO so);

    /**
     * mfvehicle 表关联 mfvehicletype 是否存在
     *
     * @param so
     * @return
     */
    Boolean checkVehicleType(MeMfvehicletypeSO so);
}