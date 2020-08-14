package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.PDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.PDeliverySumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.PDeliverySumMapper;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface PDeliverySumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<PDeliverySum> getList(PDeliverySumSO so);

    /**
     * 获取最新的数据
     *
     * @param so
     * @return
     */
    PDeliverySum getNewest(PDeliverySumSO so);
}