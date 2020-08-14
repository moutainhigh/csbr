package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author zhangheng
 * @date 2019/12/12 10:45
 * 将两个数据源合并在一起然后输出string
 */
@Slf4j
public class UnionDemo {



    public static void main(String[] args){
        try {
            // 1.初始化两个数据源
            // 1.初始化数据源
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

            Properties consumerProps = new Properties();
            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//        properties.setProperty("group.id", flinkProperties().getGroupId());
            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            /*SimpleStringSchema可以获取到kafka消息，JSONKeyValueDeserializationSchema可以获取都消息的key,value，metadata:topic,partition，offset等信息*/
            FlinkKafkaConsumer myConsumer1 = new FlinkKafkaConsumer("canal", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer1.setStartFromLatest();

            //监听另一个topic数据源
            FlinkKafkaConsumer myConsumer2 = new FlinkKafkaConsumer("canal01", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer2.setStartFromLatest();

            DataStream<String> dataStream1 = env.addSource(myConsumer1)
                    .map(new MapFunction<String, String>() {
                        @Override
                        public String map(String value) throws Exception {
                            StringBuffer str = new StringBuffer("source1:");
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            str.append(msgEntity.getDatabase());
                            return str.toString();
                        }
                    });
            DataStream<String> dataStream2 = env.addSource(myConsumer2)
                    .map(new MapFunction<String, String>() {
                        @Override
                        public String map(String value) throws Exception {
                            StringBuffer str = new StringBuffer("source2:");
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            str.append(msgEntity.getDatabase());
                            return str.toString();
                        }
                    });

            dataStream2.print();
            dataStream1.print();
            //3.将两个数据流合并
            DataStream<String> unionStream  = dataStream2.union(dataStream1).map(new MapFunction<String, String>() {

                @Override
                public String map(String value) throws Exception {
                    String str = value.replaceAll("source1", "unionSource").replaceAll("source2", "unionSource");
                    return str;
                }});

            //4.打印
            unionStream.print();

            //执行数据流
            env.execute("csbr_etl_job");
            //另一种实例
//            SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
//            DataStream<BinLogMsgEntity> unionDS = kafkaData.union(kafkaData,kafkaData);
//            unionDS.countWindowAll(5).max("age")
//                    .print().setParallelism(1);
//            env.execute("kafka010 demo");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }

}
