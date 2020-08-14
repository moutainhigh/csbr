package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.csbr.cloud.hy.server.mapper.InvLotStockWmsMapper;
import com.csbr.cloud.hy.server.service.InvLotStockWmsKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2020/5/20 14:58
 */
@Service
@Slf4j
@Transactional
public class InvLotStockWmsKafkaServiceImpl implements InvLotStockWmsKafkaService {

    @Autowired
    private InvLotStockWmsMapper invLotStockWmsMapper;

    /**
     * 新增WMS库存
     *
     * @param recordList
     * @param ack
     */
    @Override
    @Transactional
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "kcid.zs.spring",
            topics = "CSBR20_ZS", errorHandler = "postLotStockWmsInsertErrorHandler")
    public void postLotStockWmsInsert(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack) {
        recordList.forEach(record -> {
            try {
                InvLotStockWmsEntity invLotStockWmsEntity = JSON.parseObject(record.value(), InvLotStockWmsEntity.class);
                //先删在增加
                if(StringUtils.isNotEmpty(invLotStockWmsEntity.getGuid())){
                    QueryWrapper<InvLotStockWmsEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("guid",invLotStockWmsEntity.getGuid());
                    InvLotStockWmsEntity selectOne = invLotStockWmsMapper.selectOne(queryWrapper);
                    if(selectOne != null){
                        //删除
                        invLotStockWmsMapper.delete(queryWrapper);
                        //增加
                        invLotStockWmsMapper.insert(invLotStockWmsEntity);
                    }else{
                        //增加
                        invLotStockWmsMapper.insert(invLotStockWmsEntity);
                    }
                }
                ack.acknowledge();
            }catch (Exception e){
                log.error(String.valueOf(e.getStackTrace()));
            }

        });
    }

    @Bean
    public ConsumerAwareListenerErrorHandler postLotStockWmsInsertErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                log.info("postLotStockWmsInsertErrorHandler receive : "+message.getPayload().toString());
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
