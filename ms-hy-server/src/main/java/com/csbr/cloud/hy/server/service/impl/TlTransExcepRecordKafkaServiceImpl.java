package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.csbr.cloud.hy.server.entity.TlTransExcepRecordEntity;
import com.csbr.cloud.hy.server.mapper.TlTransExcepRecordMapper;
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
 * @date 2020/5/20 16:13
 */
@Service
@Slf4j
@Transactional
public class TlTransExcepRecordKafkaServiceImpl {

    @Autowired
    private TlTransExcepRecordMapper tlTransExcepRecordMapper;

    /**
     * 新增运输异常记录
     */
    @Transactional
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "kcid.zs.spring",
            topics = "CSBR20_ZS", errorHandler = "postTransExcepRecordInsertErrorHandler")
    public void postTransExcepRecordInsert(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack){
        recordList.forEach(record -> {

            try {
                TlTransExcepRecordEntity tlTransExcepRecordEntity = JSON.parseObject(record.value(), TlTransExcepRecordEntity.class);
                tlTransExcepRecordMapper.insert(tlTransExcepRecordEntity);
                ack.acknowledge();
            }catch (Exception e){
                log.error(String.valueOf(e.getStackTrace()));
            }

        });

    }

    @Bean
    public ConsumerAwareListenerErrorHandler postTransExcepRecordInsertErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                log.info("postTransExcepRecordInsertErrorHandler receive : "+message.getPayload().toString());
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
