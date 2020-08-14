package com.csbr.cloud.flink.api.mybatis.basedata.service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.CusDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.CusDeliverySumSO;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.CusDeliverySumMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDetailsDTO;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */

public interface CusDeliverySumService {
    /**
     * 获取查询链表
     *
     * @return 结果链表
     */
    List<CusDeliverySum> getList(CusDeliverySumSO so);

    /**
     * 查询运力明细
     *
     * @return
     */
    List<DeliveryDetailsDTO> getDeliveryDetails();
}