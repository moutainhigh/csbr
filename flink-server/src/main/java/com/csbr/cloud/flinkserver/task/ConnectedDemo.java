package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author zhangheng
 * @date 2019/12/11 10:29
 * FLINK的connect算子
 */
@Slf4j
public class ConnectedDemo {

    public static void main(String[] args){
        try {
            // 1.初始化两个数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

            Properties consumerProps = new Properties();
            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            FlinkKafkaConsumer myConsumer1 = new FlinkKafkaConsumer("canal", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer1.setStartFromLatest();
//        myConsumer.assignTimestampsAndWatermarks(new CustomWatermarkEmitter());
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            FlinkKafkaConsumer myConsumer2 = new FlinkKafkaConsumer("canal01", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer2.setStartFromLatest();

            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            DataStream keyedStream1 = env.addSource(myConsumer1)
                    .map(new MapFunction<String, Tuple2<String,String>>() {
                        @Override
                        public Tuple2<String,String> map(String value) throws Exception {
                            Tuple2<String,String> map = new Tuple2<>();
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            map.setFields(msgEntity.getTable(), msgEntity.getType());
                            return map;
                        }
                    });

            DataStream keyedStream2 = env.addSource(myConsumer2)
                    .map(new MapFunction<String, Tuple2<String,Long>>() {
                        @Override
                        public Tuple2<String,Long> map(String value) throws Exception {
                            Tuple2<String,Long> map = new Tuple2<>();
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            map.setFields(msgEntity.getTable(), msgEntity.getEs());
                            return map;
                        }
                    });

            //3.connect
            SingleOutputStreamOperator<String> connectedStream  = keyedStream1.connect(keyedStream2)
                    .process(new CoProcessFunction<Tuple2<String,String>, Tuple2<String,Long>, String>() {
                        //connect的process可以分别对两个流进行不同的处理，并且在处理的过程中，可以通过context写入侧数据流中。
                        @Override
                        public void processElement1(Tuple2<String, String> input1,
                                                    Context context1,
                                                    Collector<String> output1) throws Exception {
//                    System.out.println("------processElement1:table:"+input1.f0+"；type:"+input1.f1+"。----------------");
//                    System.out.println("------processElement1:context1:"+context1.toString());
                            //可以通过CoProcessFunction往侧数据流里写数据
//                    System.out.println("------processElement1:currentProcessingTime:"+context1.timerService().currentProcessingTime()+";currentWatermark:"+context1.timerService().currentWatermark()+"。----------------");
//                    context1.output(table, input1.f0+input1.f1);
                            output1.collect("------processElement1:table:"+input1.f0+"；type:"+input1.f1+"。----------------");
                        }

                        @Override
                        public void processElement2(Tuple2<String, Long> input2,
                                                    Context context2,
                                                    Collector<String> output2) throws Exception {
//                    System.out.println("------processElement2:table:"+input2.f0+"；es:"+input2.f1+"。----------------");
//                    System.out.println("------processElement2:context2:"+context2.toString());
//                    context2.output(table, input2.f0+input2.f1);
                            output2.collect("------processElement2:table:"+input2.f0+"；es:"+input2.f1+"。----------------");
                        }});
//            DataStream<String> outOrderStream = connectedStream.getSideOutput(table);
//            outOrderStream.print();
//            ConnectedStreams<Tuple2<String, String>, Tuple2<String, Long>> keyedConnectedStream = dataStream1.connect(dataStream2).keyBy(0,0);

            //4.打印
            connectedStream.print();
            //执行数据流
            env.execute("csbr_etl_job");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }

}
