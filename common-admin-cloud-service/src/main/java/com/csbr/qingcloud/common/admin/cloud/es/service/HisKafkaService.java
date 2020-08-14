//package com.csbr.qingcloud.common.admin.cloud.es.service;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.csbr.cloud.common.entity.BinLogMsg;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author zhangheng
// * @date 2020/7/21 11:15
// */
//@Service
//@Slf4j
//public class HisKafkaService {
//
//    //历史记录
//    @KafkaListener(containerFactory = "kafkaListenerContainerFactory", groupId = "kcid.zuul.spring",
//            topics = "canal", errorHandler = "consumerAwareErrorHandler")
//    public void csiListener(List<ConsumerRecord<String, String>> recordList, Acknowledgment ack){
//        recordList.forEach(record -> {
//
//            try {
//                //从上行消息端接受数据转换为实体
//                BinLogMsg binLogMsg = JSON.parseObject(record.value(), BinLogMsg.class);
//                List<JSONObject> data = binLogMsg.getData();
//                if(data != null && data.size() > 0){
//                    JSONObject jsonObject = data.get(0);
//                    log.info(binLogMsg.getData().toString());
//                    switch (binLogMsg.getType()){
//
//                        case "INSERT":
//                            break;
//                        case "UPDATE":
//                            break;
//                        case "DELETE":
//                            break;
//                        case "SELECT":
//                            break;
//                        default :
//                    }
//                }
//                ack.acknowledge();
//            }catch (Exception e){
//                log.error(String.valueOf(e.getStackTrace()));
//            }
//        });
//    }
//
//}
