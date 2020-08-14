package com.csbr.cloud.hy.server.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/5/20 14:56
 */
public interface InvLotStockWmsKafkaService {

    /**
     * 新增WMS库存
     */
    void postLotStockWmsInsert(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack);
}
