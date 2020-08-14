package com.csbr.cloud.flinkserver.sink;

import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import java.util.Properties;

/**
 * @author zhangheng
 * @date 2019/12/26 12:07
 */
public class KafkaSink {

    /**
     * 落地kafkaSink
     * @param topic
     * @return
     */
    public static FlinkKafkaProducer flinkKafkaProducer(String topic){
        Properties consumerProps = new Properties();
        consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
        consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset", "latest");
        //落地kafkaSink
        FlinkKafkaProducer myProducer = new FlinkKafkaProducer<>(
                topic,// target topic
                new KafkaSerializationSchema<String>(){
                    @Override
                    public ProducerRecord<byte[], byte[]> serialize(String value, @Nullable Long aLong) {
                        ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<>(topic,value.getBytes());
                        return producerRecord;
                    }
                }, consumerProps, FlinkKafkaProducer.Semantic.AT_LEAST_ONCE, 5);   // serialization schema
        myProducer.setWriteTimestampToKafka(true);
        return myProducer;
    }
}
