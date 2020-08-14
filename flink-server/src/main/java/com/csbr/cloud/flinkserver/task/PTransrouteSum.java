package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.PDeliverySumEntity;
import com.csbr.cloud.flinkserver.model.PTransrouteSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PTransrouteSumMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/10 10:28
 * 平台配送路线
 */
@Slf4j
public class PTransrouteSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream YaoDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云数据源
            DataStream SFDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4,env);

            //过滤(药链云)
            DataStream filterYaoPTransrouteSum = YaoDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mftransroute")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFPTransrouteSum = SFDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mftransroute")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤起运地回写数据(药链云)
            DataStream<PTransrouteSumEntity> writeYaoDispatchPTransrouteSum = filterYaoPTransrouteSum.map(new MapFunction<BinLogMsgEntity, PTransrouteSumEntity>() {
                @Override
                public PTransrouteSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PTransrouteSumEntity pTransrouteSumEntity = new PTransrouteSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("Dispatch")!=null){
                        //省 分组 调用api
                        jsonObject.getString("Dispatch").substring(jsonObject.getString("Dispatch").indexOf("/"),1);

                        //市 分组 调用api
                        jsonObject.getString("Dispatch").substring(jsonObject.getString("Dispatch").indexOf("/"),2);
                    }

                    return pTransrouteSumEntity;
                }
            });

            //落地数据库(药链云-起运地)
            writeYaoDispatchPTransrouteSum.addSink(new PTransrouteSumMySQLSink());
            //落地kafka(药链云-起运地)
            FlinkKafkaProducer myYaoDispatchProducer = KafkaSink.flinkKafkaProducer("csbr-YaoDispatchPTransrouteSum-flink");
            //转换对象为String
            DataStream<String> kafkaYaoDispatchResult = writeYaoDispatchPTransrouteSum.map(new MapFunction<PTransrouteSumEntity, String>() {
                @Override
                public String map(PTransrouteSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoDispatchResult.addSink(myYaoDispatchProducer);

            //过滤目的地回写数据(药链云)
            DataStream<PTransrouteSumEntity> writeYaoDestinationPTransrouteSum = filterYaoPTransrouteSum.map(new MapFunction<BinLogMsgEntity, PTransrouteSumEntity>() {
                @Override
                public PTransrouteSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PTransrouteSumEntity pTransrouteSumEntity = new PTransrouteSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("Destination")!=null){
                        //省 分组 调用api
                        jsonObject.getString("Destination").substring(jsonObject.getString("Destination").indexOf("/"),1);

                        //市 分组 调用api
                        jsonObject.getString("Destination").substring(jsonObject.getString("Destination").indexOf("/"),2);
                    }

                    return pTransrouteSumEntity;
                }
            });

            //落地数据库(药链云-目的地)
            writeYaoDestinationPTransrouteSum.addSink(new PTransrouteSumMySQLSink());
            //落地kafka(药链云-目的地)
            FlinkKafkaProducer myYaoDestinationProducer = KafkaSink.flinkKafkaProducer("csbr-YaoDestinationPTransrouteSum-flink");
            //转换对象为String
            DataStream<String> kafkaYaoDestinationResult = writeYaoDestinationPTransrouteSum.map(new MapFunction<PTransrouteSumEntity, String>() {
                @Override
                public String map(PTransrouteSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoDestinationResult.addSink(myYaoDestinationProducer);

            //过滤起运地回写数据(四方云)
            DataStream<PTransrouteSumEntity> writeSFDispatchPTransrouteSum = filterSFPTransrouteSum.map(new MapFunction<BinLogMsgEntity, PTransrouteSumEntity>() {
                @Override
                public PTransrouteSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PTransrouteSumEntity pTransrouteSumEntity = new PTransrouteSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("Dispatch")!=null){
                        //省 分组 调用api
                        jsonObject.getString("Dispatch").substring(jsonObject.getString("Dispatch").indexOf("/"),1);

                        //市 分组 调用api
                        jsonObject.getString("Dispatch").substring(jsonObject.getString("Dispatch").indexOf("/"),2);
                    }

                    return pTransrouteSumEntity;
                }
            });

            //落地数据库(四方云-起运地)
            writeSFDispatchPTransrouteSum.addSink(new PTransrouteSumMySQLSink());
            //落地kafka(四方云-起运地)
            FlinkKafkaProducer mySFDispatchProducer = KafkaSink.flinkKafkaProducer("csbr-SFDispatchPTransrouteSum-flink");
            //转换对象为String
            DataStream<String> kafkaSFDispatchResult = writeSFDispatchPTransrouteSum.map(new MapFunction<PTransrouteSumEntity, String>() {
                @Override
                public String map(PTransrouteSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFDispatchResult.addSink(mySFDispatchProducer);

            //过滤目的地回写数据(四方云)
            DataStream<PTransrouteSumEntity> writeSFDestinationPTransrouteSum = filterSFPTransrouteSum.map(new MapFunction<BinLogMsgEntity, PTransrouteSumEntity>() {
                @Override
                public PTransrouteSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PTransrouteSumEntity pTransrouteSumEntity = new PTransrouteSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("Destination")!=null){
                        //省 分组 调用api
                        jsonObject.getString("Destination").substring(jsonObject.getString("Destination").indexOf("/"),1);

                        //市 分组 调用api
                        jsonObject.getString("Destination").substring(jsonObject.getString("Destination").indexOf("/"),2);
                    }

                    return pTransrouteSumEntity;
                }
            });

            //落地数据库(四方云-目的地)
            writeSFDestinationPTransrouteSum.addSink(new PTransrouteSumMySQLSink());
            //落地kafka(四方云-起运地)
            FlinkKafkaProducer mySFDestinationProducer = KafkaSink.flinkKafkaProducer("csbr-SFDestinationPTransrouteSum-flink");
            //转换对象为String
            DataStream<String> kafkaSFDestinationResult = writeSFDestinationPTransrouteSum.map(new MapFunction<PTransrouteSumEntity, String>() {
                @Override
                public String map(PTransrouteSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFDestinationResult.addSink(mySFDestinationProducer);

            //打印数据
            writeYaoDispatchPTransrouteSum.print().setParallelism(1).name("药链云-DispatchPTransrouteSum");
            writeYaoDestinationPTransrouteSum.print().setParallelism(1).name("药链云-DestinationPTransrouteSum");
            writeSFDispatchPTransrouteSum.print().setParallelism(1).name("四方云-DispatchPTransrouteSum");
            writeSFDestinationPTransrouteSum.print().setParallelism(1).name("四方云-DestinationPTransrouteSum");

            //执行数据
            env.execute("csbr_PTransrouteSum_job");
        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
