package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusStorageSumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusStorageSumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDetailsDTO;
import com.csbr.cloud.flink.api.mybatis.basedata.model.WarehouseDetailsDTO;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface CusStorageSumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<CusStorageSum> getList(CusStorageSumSO so);

    /**
     * 查询运力明细
     *
     * @return
     */
    List<WarehouseDetailsDTO> getWarehouseDetails();
}