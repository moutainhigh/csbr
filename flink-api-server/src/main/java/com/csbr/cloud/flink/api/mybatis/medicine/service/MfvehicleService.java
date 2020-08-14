package com.csbr.cloud.flink.api.mybatis.medicine.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.medicine.entity.Mfvehicle;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MfvehicleSO;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MfvehicleMapper;

/**
 * 运输车辆资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface MfvehicleService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<Mfvehicle> getList(MfvehicleSO so);
}