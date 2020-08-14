package com.csbr.cloud.flinkserver.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author zhangheng
 * @date 2019/12/12 15:33
 */
public class SourceKafka {
    /**
     * 封装flink 读取kafka010中的数据，便于调用
     *
     * @param env StreamExecutionEnvironment
     * @return
     */
    public static SingleOutputStreamOperator<BinLogMsgEntity> getFlinkkafkaSource(StreamExecutionEnvironment env) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstant.BROKER_LIST);
//        props.put("group.id", GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
        props.put("max.request.size", 15000000);
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = env
                .addSource(new FlinkKafkaConsumer<String>(KafkaConstant.TOPIC1, new SimpleStringSchema(), props))
                .map(new MapFunction<String, BinLogMsgEntity>() {
                    @Override
                    public BinLogMsgEntity map(String value) throws Exception {
                        return JSON.parseObject(value, BinLogMsgEntity.class);
                    }
                });
        return kafkaData;
    }

    /**
     * 采用java8的lambda表达式
     *
     * @param env
     * @return
     */
    public static SingleOutputStreamOperator<BinLogMsgEntity> getFlinkKafkaSourceV2(StreamExecutionEnvironment env) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaConstant.BROKER_LIST);
//        props.put("group.id", GROUP_ID);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
        props.put("max.request.size", 15000000);
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = env
                .addSource(new FlinkKafkaConsumer<String>(KafkaConstant.TOPIC1, new SimpleStringSchema(), props))
                .map(line -> JSON.parseObject(line, BinLogMsgEntity.class));   // lambda 表达式
        return kafkaData;
    }

    /**
     * 封装flink读取kafka数据
     */
    public static DataStream getFlinkDataStreamKafkaSource(String topic,StreamExecutionEnvironment env) {
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
        /* -------------设置checkpoint完成的超时时间*/
        env.getCheckpointConfig().setCheckpointTimeout(10000);
        /* -------------设置checkpoint的最大并行度 */
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        /*开启checkpoints的外部持久化，但是在job失败的时候不会自动清理，需要自己手工清理state
        DELETE_ON_CANCELLATION:在job canceled的时候会自动删除外部的状态数据，但是如果是FAILED的状态则会保留；
        RETAIN_ON_CANCELLATION:在job canceled的时候会保留状态数据*/
        env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
        /* -------------设置checkpoint上一个的结束点到下一个开始点之间的最短时间。
          因为checkpoint触发时，需要一定时间去完成整个checkpoint的过程，
          如果checkpoint的完成时间过程，导致前后两个checkpoint间的时间间隔过短，这是不合适的，没有必要。
          1、这里的时间间隔，指的是上一个checkpoint完成的时间点，到下一个checkpoint开始的时间点的间隔，如果过短，会导致频繁checkpoint，影响性能。假设这个间隔为T
          2、而上面设置的checkpoint时间间隔，指的是前一个checkpoint的开始时间到下一个checkpoint的开始时间。所以是始终大于1中的时间间隔的。假设这个间隔为 N
          如果T小于这里设置的值，那么无论N设置多少，下一个checkpoint的开始时间必须是500ms之后。如果T大于这里设置的值，那么正常按照N设置的间隔来触发下一个checkpoint，这里设置的间隔无关了。
        */
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(1000);
        /*当有更近的保存点时，优先采用savepoint来恢复成检查点*/
        env.getCheckpointConfig().setPreferCheckpointForRecovery(true);
//        int tolerableCheckpointFailureNumber = env.getCheckpointConfig().getTolerableCheckpointFailureNumber();
//        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(tolerableCheckpointFailureNumber);
        Properties consumerProps = new Properties();
        consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
        consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProps.put("auto.offset.reset", "latest");
        consumerProps.put("max.request.size", 15000000);
//            consumerProps.setProperty("transaction.max.timeout.ms", String.valueOf(1000*60*60*24));
        // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
        /*SimpleStringSchema可以获取到kafka消息，JSONKeyValueDeserializationSchema可以获取都消息的key,value，metadata:topic,partition，offset等信息*/
        FlinkKafkaConsumer myConsumer = new FlinkKafkaConsumer(topic, new SimpleStringSchema(),
                consumerProps);

        //数据整理映射为BinLogMsgEntity数据
        DataStream dataStream = env.addSource(myConsumer).map(new MapFunction<String, BinLogMsgEntity>() {
            @Override
            public BinLogMsgEntity map(String value) throws Exception {

                BinLogMsgEntity msgEntity = JSON.parseObject(value,
                        new TypeReference<BinLogMsgEntity>() {});
                return msgEntity;
            }
        });
        return dataStream;
    }

    /**
     * 从kafka中消费person  topic数据
     * @param env
     * @return
     */
//    public static SingleOutputStreamOperator<Person> getPersonKafka010(StreamExecutionEnvironment env) {
//        Properties props = new Properties();
//        props.put("bootstrap.servers", BROKER_LIST);
//        props.put("group.id", GROUP_ID);
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("auto.offset.reset", "latest");
//        SingleOutputStreamOperator<Person> personData = env
//                .addSource(new FlinkKafkaConsumer010<String>(TOPIC2, new SimpleStringSchema(), props))
//                .map(line -> JSON.parseObject(line, Person.class));
//        return personData;
//    }
}
