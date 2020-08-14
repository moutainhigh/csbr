package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.sink.CsbrSumMySQLSink;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.accumulators.DoubleCounter;
import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author zhangheng
 * @date 2019/12/18 16:32
 */
@Slf4j
public class CsbrSumAll {


    public static void main(String[] args){
//    public void getCsbrSumAll() {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//            SpringApplication application = new SpringApplication(CsbrSumAll.class);
//            application.setBannerMode(Banner.Mode.OFF);
//            ApplicationContext context = application.run(new String[]{});
//            dispatchOrderFeign = context.getBean(DispatchOrderFeign.class);
            //间隔一分钟检查一次
            env.enableCheckpointing(60000);
//            env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//            env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
//            env.getCheckpointConfig().setCheckpointTimeout(10000);
//            env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
//            env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
//            Properties consumerProps = new Properties();
//            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
////          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
//            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//            consumerProps.put("auto.offset.reset", "latest");
////            consumerProps.setProperty("transaction.max.timeout.ms", String.valueOf(1000*60*60*24));
//            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
//            /*SimpleStringSchema可以获取到kafka消息，JSONKeyValueDeserializationSchema可以获取都消息的key,value，metadata:topic,partition，offset等信息*/
//            FlinkKafkaConsumer myConsumer = new FlinkKafkaConsumer("canal", new SimpleStringSchema(),
//                    consumerProps);
//
//            //数据整理映射为BinLogMsgEntity数据
//            DataStream keyedStream = env.addSource(myConsumer).map(new MapFunction<String, BinLogMsgEntity>() {
//                @Override
//                public BinLogMsgEntity map(String value) throws Exception {
//
//                    BinLogMsgEntity msgEntity = JSON.parseObject(value,
//                            new TypeReference<BinLogMsgEntity>() {});
//                    return msgEntity;
//                }
//            });
            DataStream dataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC1,env);

            //平台签约GSP仓统计过滤
            DataStream filterCsbrSumAll = dataStream.filter(new FilterFunction<BinLogMsgEntity>() {
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("UPDATE") || binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mfwarehouse")) {
                            List<JSONObject> data = binLogMsgEntity.getData();
                            JSONObject jsonObject = data.get(0);
                            if (jsonObject.get("ISGSP") != null && jsonObject.get("ISGSP").equals("Y")) {
                                if (jsonObject.get("IsDeleted") != null && jsonObject.get("IsDeleted").equals("N")) {
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
            });


            DataStream writeCsbrSumAll = filterCsbrSumAll.map(new MapFunction<BinLogMsgEntity, CsbrSumEntity>() {
                @Override
                public CsbrSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CsbrSumEntity csbrSumEntity = new CsbrSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    //平台签约GSP仓统计
                    csbrSumEntity.setTotleGsp(1L);
                    // 获取物流单号
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
//                  MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//                  map.add("ruleName", "dispatch");

//                  HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
                    HttpEntity<String> request = new HttpEntity<>("dispatch", headers);
                    RestTemplate restTemplate = new RestTemplate();
                    ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://49.4.23.228:8082/order-business-server/order-no/create", request, String.class);
                    log.info("通过feign获取:"+responseEntity.getBody());

                    if (jsonObject.get("TotalAreaAge") != null) {
                        //总面积统计
                        csbrSumEntity.setTotleArea(jsonObject.getDouble("TotalAreaAge"));
                        //阴凉库面积
                        csbrSumEntity.setShadeArea(jsonObject.getDouble("TotalAreaAge"));
                        //冷库面积
                        if (jsonObject.get("StorageType") != null) {
                            if (jsonObject.get("StorageType").equals("03") || jsonObject.get("StorageType").equals("04")) {
                                csbrSumEntity.setChillArea(jsonObject.getDouble("TotalAreaAge"));
                            }
                        }
                    }
                    //三方库面积
                    if (jsonObject.get("TotalAreaAge") != null && jsonObject.get("ThreePartyWarehouse") != null && jsonObject.get("ThreePartyWarehouse").equals("Y")) {
                        csbrSumEntity.setThreeArea(jsonObject.getDouble("TotalAreaAge"));
                        RestTemplate restTemplate1 = new RestTemplate();
                        ResponseEntity<String> responseEntity1 = restTemplate1.postForEntity("http://49.4.23.228:8082/order-business-server/order-no/create", request, String.class);
                        log.info("通过feign获取:"+responseEntity1.getBody());
                    }
                    //创建时间
                    csbrSumEntity.setCreateDate(new Date());
                    //修改时间
                    csbrSumEntity.setUpdateDate(new Date());
                    return csbrSumEntity;
                }
            });
            //operate
            DataStream<CsbrSumEntity> sumTotleGsp = writeCsbrSumAll.map(new RichMapFunction<CsbrSumEntity, CsbrSumEntity>() {

                //第一步：定义累加器
                //平台签约GSP仓累加器
                private LongCounter totleGsp = new LongCounter();
                //总面积累加器
                private DoubleCounter totleArea = new DoubleCounter();
                //三方库累加器
                private DoubleCounter threeArea = new DoubleCounter();
                //阴凉库累加器
                private DoubleCounter shadeArea = new DoubleCounter();
                //冷库累加器
                private DoubleCounter chillArea = new DoubleCounter();

                @Override
                public void open(Configuration parameters) throws Exception {
                    super.open(parameters);

                    //第二步：注册累加器
                    getRuntimeContext().addAccumulator("totle-gsp", this.totleGsp);

                    getRuntimeContext().addAccumulator("totle-area", this.totleArea);

                    getRuntimeContext().addAccumulator("three-area", this.threeArea);

                    getRuntimeContext().addAccumulator("shade-area", this.shadeArea);

                    getRuntimeContext().addAccumulator("chill-area", this.chillArea);
                }

                @Override
                public CsbrSumEntity map(CsbrSumEntity s) throws Exception {
                    //第三步：累加
                    if (s.getTotleGsp() != null) {
                        this.totleGsp.add(s.getTotleGsp());
                        s.setTotleGsp(this.totleGsp.getLocalValue());
                    } else {
                        s.setTotleGsp(this.totleGsp.getLocalValue());
                    }
                    if (s.getTotleArea() != null) {
                        this.totleArea.add(s.getTotleArea());
                        s.setTotleArea(this.totleArea.getLocalValue());
                    } else {
                        s.setTotleArea(this.totleArea.getLocalValue());
                    }
                    if (s.getThreeArea() != null) {
                        this.threeArea.add(s.getThreeArea());
                        s.setThreeArea(this.threeArea.getLocalValue());
                    } else {
                        s.setThreeArea(this.threeArea.getLocalValue());
                    }
                    if (s.getShadeArea() != null) {
                        this.shadeArea.add(s.getShadeArea());
                        s.setShadeArea(this.shadeArea.getLocalValue());
                    } else {
                        s.setShadeArea(this.shadeArea.getLocalValue());
                    }
                    if (s.getChillArea() != null) {
                        this.chillArea.add(s.getChillArea());
                        s.setChillArea(this.chillArea.getLocalValue());
                    } else {
                        s.setChillArea(this.chillArea.getLocalValue());
                    }
                    return s;
                }
            });
            //落地数据库
            sumTotleGsp.addSink(new CsbrSumMySQLSink());
            //落地kafkaSink
//            FlinkKafkaProducer myProducer = new FlinkKafkaProducer<>(
//                    "csbr-sum-flink",// target topic
//                    new KafkaSerializationSchema<String>(){
//                        @Override
//                        public ProducerRecord<byte[], byte[]> serialize(String value, @Nullable Long aLong) {
//                            ProducerRecord<byte[], byte[]> producerRecord = new ProducerRecord<>("csbr-sum-flink",value.getBytes());
//                            return producerRecord;
//                        }
//                    }, consumerProps, FlinkKafkaProducer.Semantic.AT_LEAST_ONCE, 5);   // serialization schema
//            myProducer.setWriteTimestampToKafka(true);
            //落地kafka
            FlinkKafkaProducer myProducer = KafkaSink.flinkKafkaProducer("csbr-sum-flink");
            //转换对象为String
            DataStream<String> kafkaResult = sumTotleGsp.map(new MapFunction<CsbrSumEntity, String>() {
                @Override
                public String map(CsbrSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaResult.addSink(myProducer);

            //打印结果
            sumTotleGsp.print().setParallelism(1);
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

            // 执行数据流
            JobExecutionResult csbr_csbrSumAll_job = env.execute("csbr_CsbrSumAll_job");
//            //结果
//            Long totleGsp = csbr_csbrSumAll_job.getAccumulatorResult("totle-gsp");
//            Double totleArea = csbr_csbrSumAll_job.getAccumulatorResult("totle-area");
//            Double threeArea = csbr_csbrSumAll_job.getAccumulatorResult("three-area");
//            Double shadeArea = csbr_csbrSumAll_job.getAccumulatorResult("shade-area");
//            Double chillArea = csbr_csbrSumAll_job.getAccumulatorResult("chill-area");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
//    }
    }
}
