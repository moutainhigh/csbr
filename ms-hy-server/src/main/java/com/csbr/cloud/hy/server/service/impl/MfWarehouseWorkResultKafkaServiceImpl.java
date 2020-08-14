package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.csbr.cloud.hy.server.entity.MfWarehouseWorkResultEntity;
import com.csbr.cloud.hy.server.entity.TlTransExcepRecordEntity;
import com.csbr.cloud.hy.server.mapper.MfWarehouseWorkResultMapper;
import com.csbr.cloud.hy.server.service.MfWarehouseWorkResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/5/20 16:33
 */
@Service
@Slf4j
@Transactional
public class MfWarehouseWorkResultKafkaServiceImpl {

    @Autowired
    private MfWarehouseWorkResultMapper mfWarehouseWorkResultMapper;

    /**
     * 仓库作业信息
     */
    @Transactional
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "kcid.zs.spring",
            topics = "CSBR20_ZS", errorHandler = "postWareHouseWorkInsertErrorHandler")
    public void postWareHouseWorkInsert(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack){
        recordList.forEach(record -> {

            try {
                MfWarehouseWorkResultEntity mfWarehouseWorkResultEntity = JSON.parseObject(record.value(), MfWarehouseWorkResultEntity.class);
                mfWarehouseWorkResultMapper.insert(mfWarehouseWorkResultEntity);
                ack.acknowledge();
            }catch (Exception e){
                log.error(String.valueOf(e.getStackTrace()));
            }

        });

    }

    @Bean
    public ConsumerAwareListenerErrorHandler postWareHouseWorkInsertErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                log.info("postWareHouseWorkInsertErrorHandler receive : "+message.getPayload().toString());
                MessageHeaders headers = message.getHeaders();
                List<String> topics = headers.get(KafkaHeaders.RECEIVED_TOPIC, List.class);
                List<Integer> partitions = headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, List.class);
                List<Long> offsets = headers.get(KafkaHeaders.OFFSET, List.class);
                Map<TopicPartition, Long> offsetsToReset = new HashMap<>();

                return null;
            }
        };
    }
}
