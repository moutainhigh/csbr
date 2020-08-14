package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
import com.csbr.cloud.flinkserver.model.PStorageSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PStorageSumMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/9 13:49
 * 平台仓储能力
 */
@Slf4j
public class PStorageSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream YaoDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云数据源
            DataStream SFDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4,env);
            //flink自有数据源
            DataStream FlinkDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC1,env);
            //过滤(药链云)
            DataStream filterYaoPStorageSum = YaoDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfwarehouse")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFPStorageSum = SFDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfwarehouse")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(flink自有云)
            DataStream filterFlinkPStorageSum = FlinkDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("p_storage_delivery_region_sum")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(药链云)
            DataStream<PStorageSumEntity> writeYaoPStorageSum = filterYaoPStorageSum.map(new MapFunction<BinLogMsgEntity, PStorageSumEntity>() {
                @Override
                public PStorageSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PStorageSumEntity pStorageSumEntity = new PStorageSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                        if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                            //GSP仓库汇总
                            pStorageSumEntity.setTotleGSP(1L);
                            if(jsonObject.get("TotalAreaAge")!=null){
                                pStorageSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                pStorageSumEntity.setShadeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                            }
                            //三方仓库汇总
                            if(jsonObject.get("ThreePartyWarehouse")!=null&&jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                pStorageSumEntity.setThreeGSPCount(1L);
                                if(jsonObject.get("TotalAreaAge")!=null){
                                    pStorageSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                }
                            }
                            //冷库体积汇总
                            if(jsonObject.get("StorageType")!=null){
                                if(jsonObject.get("StorageType").equals("03")||jsonObject.get("StorageType").equals("04")){
                                    if(jsonObject.get("TotalAreaAge")!=null){
                                        pStorageSumEntity.setChillArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                    }
                                }
                            }
                        }
                    }
                    return pStorageSumEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoPStorageSum.addSink(new PStorageSumMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoPStorageSum-flink");
            DataStream<String> kafkaYaoResult = writeYaoPStorageSum.map(new MapFunction<PStorageSumEntity, String>() {
                @Override
                public String map(PStorageSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);

            //过滤回写数据(四方云)
            DataStream<PStorageSumEntity> writeSFPStorageSum = filterSFPStorageSum.map(new MapFunction<BinLogMsgEntity, PStorageSumEntity>() {
                @Override
                public PStorageSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PStorageSumEntity pStorageSumEntity = new PStorageSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                        if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                            //GSP仓库汇总
                            pStorageSumEntity.setTotleGSP(1L);
                            if(jsonObject.get("TotalAreaAge")!=null){
                                pStorageSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                pStorageSumEntity.setShadeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                            }
                            //三方仓库汇总
                            if(jsonObject.get("ThreePartyWarehouse")!=null&&jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                pStorageSumEntity.setThreeGSPCount(1L);
                                if(jsonObject.get("TotalAreaAge")!=null){
                                    pStorageSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                }
                            }
                            //冷库体积汇总
                            if(jsonObject.get("StorageType")!=null){
                                if(jsonObject.get("StorageType").equals("03")||jsonObject.get("StorageType").equals("04")){
                                    if(jsonObject.get("TotalAreaAge")!=null){
                                        pStorageSumEntity.setChillArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                    }
                                }
                            }
                        }
                    }
                    return pStorageSumEntity;
                }
            });

            //落地数据库(四方云)
            writeSFPStorageSum.addSink(new PStorageSumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFPStorageSum-flink");
            DataStream<String> kafkaSFResult = writeSFPStorageSum.map(new MapFunction<PStorageSumEntity, String>() {
                @Override
                public String map(PStorageSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //过滤回写flink自有库
            DataStream<PStorageSumEntity> writeFlinkPStorageSum = filterFlinkPStorageSum.map(new MapFunction<BinLogMsgEntity,PStorageSumEntity>() {
                @Override
                public PStorageSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PStorageSumEntity pStorageSumEntity = new PStorageSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ThreeGSPCount")!=null){
                        if(Long.parseLong(jsonObject.get("ThreeGSPCount")+"") > 0){
                            pStorageSumEntity.setProCount(1L);
                            pStorageSumEntity.setCityCount(1L);
                        }
                    }
                    return pStorageSumEntity;
                }
            });

            //落地数据库(flink自有库)
            writeFlinkPStorageSum.addSink(new PStorageSumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer myFlinkProducer = KafkaSink.flinkKafkaProducer("csbr-FlinkPStorageSum-flink");
            DataStream<String> kafkaFlinkResult = writeFlinkPStorageSum.map(new MapFunction<PStorageSumEntity, String>() {
                @Override
                public String map(PStorageSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaFlinkResult.addSink(myFlinkProducer);

            //打印数据
            writeYaoPStorageSum.print().setParallelism(1).name("药链云-PStorageSum");
            writeSFPStorageSum.print().setParallelism(1).name("四方云-PStorageSum");
            writeFlinkPStorageSum.print().setParallelism(1).name("flink自有库-PStorageSum");

            //执行数据
            env.execute("csbr_PStorageSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
