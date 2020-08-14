package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.List;
import java.util.Properties;

/**
 * @author zhangheng
 * @date 2019/12/11 10:37
 * flink的map\flatmap算子
 */
@Slf4j
public class CoMapDemo {

    private static final OutputTag<String> table = new OutputTag<String>("table") {};

    public static void main(String[] args){
        try {
            // 1.初始化两个数据源
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

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
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            FlinkKafkaConsumer myConsumer2 = new FlinkKafkaConsumer("canal01", new SimpleStringSchema(),
                    consumerProps);//test0是kafka中开启的topic
            //设置只读取最新数据
            myConsumer2.setStartFromLatest();
            DataStream keyedStream1 = env.addSource(myConsumer1)
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
                    .map(new MapFunction<String, Tuple2<String, List<JSONObject>>>() {
                        @Override
                        public Tuple2<String,List<JSONObject>> map(String value) throws Exception {
                            Tuple2<String,List<JSONObject>> map = new Tuple2<>();
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            map.setFields(msgEntity.getTable(), msgEntity.getData());
                            return map;
                        }
                    }).filter( new FilterFunction<Tuple2<String,List<JSONObject>>>() {

                @Override
                public boolean filter(Tuple2<String, List<JSONObject>> value) throws Exception {
                    if(null == value.f1) {
                        return false;
                    }else {
                        return true;
                    }

                }});

            DataStream keyedStream2 = env.addSource(myConsumer2)
                    .map(new MapFunction<String, Tuple2<String,List<JSONObject>>>() {
                        @Override
                        public Tuple2<String,List<JSONObject>> map(String value) throws Exception {
                            Tuple2<String,List<JSONObject>> map = new Tuple2<>();
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            map.setFields(msgEntity.getTable(), msgEntity.getMysqlType());
                            return map;
                        }
                    }).filter( new FilterFunction<Tuple2<String,List<JSONObject>>>() {

                @Override
                public boolean filter(Tuple2<String, List<JSONObject>> value) throws Exception {
                    if(null == value.f1) {
                        return false;
                    }else {
                        return true;
                    }

                }});

            //3.connect
            SingleOutputStreamOperator<List<JSONObject>> mapStream  = keyedStream1.connect(keyedStream2)
                    .map(new CoMapFunction<Tuple2<String,List<JSONObject>>, Tuple2<String,List<JSONObject>>, List<JSONObject>>() {

                        @Override
                        public List<JSONObject> map1(Tuple2<String, List<JSONObject>> value) throws Exception {
                            return  value.f1;
                        }

                        @Override
                        public List<JSONObject> map2(Tuple2<String, List<JSONObject>> value) throws Exception {
                            return  value.f1;
                        }});
            SingleOutputStreamOperator<JSONObject> flatMapStream = keyedStream1.connect(keyedStream2)
                    .flatMap(new CoFlatMapFunction<Tuple2<String,List<JSONObject>>, Tuple2<String,List<JSONObject>>, JSONObject>() {

                        @Override
                        public void flatMap1(Tuple2<String, List<JSONObject>> value, Collector<JSONObject> out)
                                throws Exception {
                            value.f1.forEach(j ->{out.collect(j);});

                        }

                        @Override
                        public void flatMap2(Tuple2<String, List<JSONObject>> value, Collector<JSONObject> out)
                                throws Exception {
                            value.f1.forEach(j ->{out.collect(j);});
                        }});
            //4.打印
            mapStream.print();
//            flatMapStream.print();
            //执行数据流
            env.execute("csbr_etl_job");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
