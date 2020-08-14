package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csbr.cloud.hy.server.entity.InvLotStockSapEntity;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.csbr.cloud.hy.server.mapper.InvLotStockSapMapper;
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
 * @date 2020/5/20 15:59
 */
@Service
@Slf4j
@Transactional
public class InvLotStockSapKafkaServiceImpl {

    @Autowired
    private InvLotStockSapMapper invLotStockSapMapper;

    /**
     * 新增SAP库存
     */
    @Transactional
    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "kcid.zs.spring",
            topics = "CSBR20_ZS", errorHandler = "postLotStockSapInsertErrorHandler")
    public void postLotStockSapInsert(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack) {
        recordList.forEach(record -> {
            try {
                InvLotStockSapEntity invLotStockSapEntity = JSON.parseObject(record.value(), InvLotStockSapEntity.class);
                //先删在增加
                if(StringUtils.isNotEmpty(invLotStockSapEntity.getGuid())){
                    QueryWrapper<InvLotStockSapEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("guid",invLotStockSapEntity.getGuid());
                    InvLotStockSapEntity selectOne = invLotStockSapMapper.selectOne(queryWrapper);
                    if(selectOne != null){
                        //删除
                        invLotStockSapMapper.delete(queryWrapper);
                        //增加
                        invLotStockSapMapper.insert(invLotStockSapEntity);
                    }else{
                        //增加
                        invLotStockSapMapper.insert(invLotStockSapEntity);
                    }
                }
                ack.acknowledge();
            }catch (Exception e){
                log.error(String.valueOf(e.getStackTrace()));
            }

        });
    }

    @Bean
    public ConsumerAwareListenerErrorHandler postLotStockSapInsertErrorHandler() {
        return new ConsumerAwareListenerErrorHandler() {

            @Override
            public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
                log.info("postLotStockSapInsertErrorHandler receive : "+message.getPayload().toString());
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
