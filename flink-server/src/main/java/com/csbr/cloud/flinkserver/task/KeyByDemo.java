package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.config.RedisExampleMapper;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.constant.PropertiesConstants;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.sink.MySQLSink;
import com.csbr.cloud.flinkserver.sink.SourceFromMySQL;
import com.csbr.cloud.flinkserver.utils.ElasticSearchSinkUtil;
import com.csbr.cloud.flinkserver.utils.ExecutionEnvUtil;
import com.csbr.cloud.flinkserver.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.util.Collector;
import org.apache.http.HttpHost;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentType;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author zhangheng
 * @date 2019/12/11 9:30
 */
@Slf4j
public class KeyByDemo {


    public static void main(String[] args){
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            env.enableCheckpointing(5000);
            env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
            env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

            Properties consumerProps = new Properties();
            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            /*SimpleStringSchema可以获取到kafka消息，JSONKeyValueDeserializationSchema可以获取都消息的key,value，metadata:topic,partition，offset等信息*/
            FlinkKafkaConsumer myConsumer = new FlinkKafkaConsumer("canal", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer.setStartFromLatest();
//            //从数据库获取数据
//            env.addSource(new SourceFromMySQL()).print();
//            DataStream<BinLogMsgEntity> keyedStream = env.addSource(myConsumer);
//        myConsumer.assignTimestampsAndWatermarks(new CustomWatermarkEmitter());
            //数据整理映射为BinLogMsgEntity数据
            DataStream<BinLogMsgEntity> keyedStream = env.addSource(myConsumer).map(new MapFunction<String, BinLogMsgEntity>() {
                @Override
                public BinLogMsgEntity map(String value) throws Exception {

//                    log.info("测试数据:"+value);
                    BinLogMsgEntity msgEntity = JSON.parseObject(value,
                            new TypeReference<BinLogMsgEntity>() {});
//                    log.info("测试数据:"+msgEntity.getDatabase());
                    return msgEntity;
                }
            });

            //落地mysqlSink
//            keyedStream.addSink(new MySQLSink());
            //落地kafkaSink
//            FlinkKafkaProducer myProducer = new FlinkKafkaProducer(
//                    "test-kafka",// target topic
//                    (KafkaSerializationSchema)new SimpleStringSchema(), consumerProps, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);   // serialization schema
//            myProducer.setWriteTimestampToKafka(true);
//            keyedStream.addSink(myProducer);

            //落地ESSink
//            SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
//            final ParameterTool parameterTool = ExecutionEnvUtil.createParameterTool(args);
//            List<HttpHost> esAddresses = ElasticSearchSinkUtil.getEsAddresses(parameterTool.get(PropertiesConstants.ELASTICSEARCH_HOSTS));
//            int bulkSize = 40;
//            int sinkParallelism = 5;
//            ElasticSearchSinkUtil.addSink(esAddresses, bulkSize, sinkParallelism, kafkaData,
//                    (BinLogMsgEntity binLogMsgEntity, RuntimeContext runtimeContext, RequestIndexer requestIndexer) -> {
//                        requestIndexer.add(Requests.indexRequest()
//                                .index("CSBR" + "_" + binLogMsgEntity.getEs())  //es 索引名
//                                .type("csbr") //es type
//                                .source(GsonUtil.toJSONBytes(binLogMsgEntity), XContentType.JSON));
//                    },parameterTool);

            //落地redis
//            //实例化Flink和Redis关联类FlinkJedisPoolConfig，设置Redis端口
//            FlinkJedisPoolConfig conf = new FlinkJedisPoolConfig.Builder().setHost("127.0.0.1").setPort(6379).build();
//            //实例化RedisSink，并通过flink的addSink的方式将flink计算的结果插入到redis
//            keyedStream.addSink(new RedisSink<BinLogMsgEntity>(conf, new RedisExampleMapper()));
//
//            // 3.keyby的多种写法
//            // 3.1.通过自定义keySelect
            KeyedStream<BinLogMsgEntity,String> keyedStream1 =
                    keyedStream.keyBy(new KeySelector<BinLogMsgEntity, String>() {

                        @Override
                        public String getKey(BinLogMsgEntity value) throws Exception {
                            // TODO Auto-generated method stub
//                            log.info("result结果:"+value.getTable());
                            return value.getTable();
                        }
                    });
//////            // 3.2.通过pojo的字段名
//            KeyedStream<BinLogMsgEntity, Tuple> keyedStream2 = keyedStream.keyBy("table");
//////            // 3.3.通过指定tuple
//            KeyedStream<Tuple3<String, String, String>, Tuple> keyedStream3 =
//                    keyedStream.map(new MapFunction<BinLogMsgEntity, Tuple3<String, String, String>>() {
//                        @Override
//                        public Tuple3<String, String, String> map(BinLogMsgEntity value) throws Exception {
//                            Tuple3<String, String, String> tuple3 = new Tuple3<>();
//                            tuple3.setFields(value.getTable(), value.getDatabase(), value.getSql());
//                            return tuple3;
//                        }
//                    }).keyBy(0);
////
////            // 4.执行每5秒时间窗口操作，计数
            SingleOutputStreamOperator<Map<String, Integer>> o1 =keyedStream1.timeWindow(Time.seconds(5))
                    .apply(new WindowFunction<BinLogMsgEntity, Map<String, Integer>, String, TimeWindow>() {

                        @Override
                        public void apply(String key, TimeWindow window, Iterable<BinLogMsgEntity> input,
                                          Collector<Map<String, Integer>> out) throws Exception {
                            Map<String, Integer> result = new HashMap<>();

                            input.forEach(b -> {
                                int count = result.get(key) == null ? 0 : result.get(key);
                                count++;
                                result.put(key, count);
                            });
                            out.collect(result);
                        }
                    });
//            SingleOutputStreamOperator<Map<String, Integer>>  o2 =keyedStream2.timeWindow(Time.seconds(5))
//                    .apply(new WindowFunction<BinLogMsgEntity, Map<String, Integer>, Tuple, TimeWindow>() {
//
//                        @Override
//                        public void apply(Tuple key, TimeWindow window, Iterable<BinLogMsgEntity> input,
//                                          Collector<Map<String, Integer>> out) throws Exception {
//                            Map<String, Integer> result = new HashMap<>();
//                            input.forEach(b -> {
//                                int count = result.get(key.toString()) == null ? 0 : result.get(key.toString());
//                                count++;
//                                result.put(key.toString(), count);
//                            });
//                            out.collect(result);
////                            log.info("o2结果:"+result.get(key.toString()));
//                        }
//                    });
//
//            SingleOutputStreamOperator<Map<String, Integer>>  o3 = keyedStream3.timeWindow(Time.seconds(5))
//                    .apply(new WindowFunction<Tuple3<String, String, String>, Map<String, Integer>, Tuple, TimeWindow>() {
//
//                        @Override
//                        public void apply(Tuple key, TimeWindow window, Iterable<Tuple3<String, String, String>> input,
//                                          Collector<Map<String, Integer>> out) throws Exception {
//                            Map<String, Integer> result = new HashMap<>();
//                            input.forEach(b -> {
//                                int count = result.get(key.toString()) == null ? 0 : result.get(key.toString());
//                                count++;
//                                result.put(key.toString(), count);
//                            });
//                            out.collect(result);
////                            log.info("o3结果:"+key.toString());
//                        }
//                    });
            o1.print().setParallelism(1);
//            o2.print().setParallelism(1);
//            o3.print().setParallelism(1);

//            SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
//            KeyedStream<BinLogMsgEntity, ArrayList<String>> keyedDS2 = getKeyedDS2(kafkaData);
//            keyedDS2.print().setParallelism(1);
            // 执行数据流
            env.execute("csbr_etl_job");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }


    /**
     * 以goods_name/state为分组条件,还可以探索一下用其他的方式
     *
     * @param kafkaData
     * @return
     */
    private static KeyedStream<BinLogMsgEntity, ArrayList<String>> getKeyedDS2(SingleOutputStreamOperator<BinLogMsgEntity> kafkaData) {
        return kafkaData.keyBy(new KeySelector<BinLogMsgEntity, ArrayList<String>>() {
            @Override
            public ArrayList<String> getKey(BinLogMsgEntity binLogMsgEntity) throws Exception {
                ArrayList<String> list = new ArrayList<>();
                List<JSONObject> data = binLogMsgEntity.getData();
                JSONObject jsonObject = data.get(0);
                if(jsonObject.get("goods_name")!=null){
                    list.add(jsonObject.get("goods_name").toString());
                }
//                list.add(jsonObject.get("state").toString());
                return list;
            }
        });
    }

    /**
     * 以年龄为分组条件进行keyBy
     *
     * @param kafkaData
     * @return
     */
    private static KeyedStream<BinLogMsgEntity, Long> getKeyedDS(SingleOutputStreamOperator<BinLogMsgEntity> kafkaData) {
        //	lambda 表达式
        //kafkaData.keyBy(line -> line.age).print().setParallelism(1);
        return kafkaData.keyBy(new KeySelector<BinLogMsgEntity, Long>() {
            @Override
            public Long getKey(BinLogMsgEntity value) throws Exception {
                return value.getId();
            }
        });
    }
}
