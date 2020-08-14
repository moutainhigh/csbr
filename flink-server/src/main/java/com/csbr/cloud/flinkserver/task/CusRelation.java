package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CusRelationEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
import com.csbr.cloud.flinkserver.model.MFCustomerInfoEntity;
import com.csbr.cloud.flinkserver.sink.CusRelationMySQLSink;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/8 17:04
 */
@Slf4j
public class CusRelation {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream yaodataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //医链云数据源
            DataStream yidataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC3, env);
            //四方云数据源
            DataStream sfdataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4, env);

            //过滤(药链云)
            DataStream filterYaoCusRelation = yaodataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mfmasterdata")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(医链云)
            DataStream filterYiCusRelation = yidataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mfmasterdata")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFCusRelation = sfdataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("mfmasterdata")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(药链云)
            DataStream<CusRelationEntity> writeYaoCusRelation = filterYaoCusRelation.map(new MapFunction<BinLogMsgEntity, CusRelationEntity>() {
                @Override
                public CusRelationEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusRelationEntity cusRelationEntity = new CusRelationEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ChineseName")!=null){
                        //根据名称判断唯一性
                        Boolean flag = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                        if(flag != null && !flag){
                            //根据名称调用api查询GUID
                            String result = FlinkApiServer.getCusGuid(jsonObject.get("ChineseName").toString());
                            if(StringUtils.isNotEmpty(result)){
                                cusRelationEntity.setGuid(result);
                            }
                            if(jsonObject.get("GUID")!=null){
                                cusRelationEntity.setSourceGuid(jsonObject.get("GUID")+"");
                            }
                            //四方云来源
                            cusRelationEntity.setSourceData("2");
                        }
                    }
                    return cusRelationEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoCusRelation.addSink(new CusRelationMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoCusRelation-flink");
            //转换对象为String
            DataStream<String> kafkaYaoResult = writeYaoCusRelation.map(new MapFunction<CusRelationEntity, String>() {
                @Override
                public String map(CusRelationEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);

            //过滤回写数据(医链云)
            DataStream<CusRelationEntity> writeYiCusRelation = filterYiCusRelation.map(new MapFunction<BinLogMsgEntity, CusRelationEntity>() {
                @Override
                public CusRelationEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusRelationEntity cusRelationEntity = new CusRelationEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ChineseName")!=null){
                        //根据名称判断唯一性
                        Boolean flag = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                        if(flag != null && !flag){
                            //根据名称调用api查询GUID
                            String result = FlinkApiServer.getCusGuid(jsonObject.get("ChineseName").toString());
                            if(StringUtils.isNotEmpty(result)){
                                cusRelationEntity.setGuid(result);
                            }
                            if(jsonObject.get("GUID")!=null){
                                cusRelationEntity.setSourceGuid(jsonObject.get("GUID")+"");
                            }
                            //四方云来源
                            cusRelationEntity.setSourceData("1");
                        }
                    }
                    return cusRelationEntity;
                }
            });

            //落地数据库(医链云)
            writeYiCusRelation.addSink(new CusRelationMySQLSink());

            //落地kafka(医链云)
            FlinkKafkaProducer myYiProducer = KafkaSink.flinkKafkaProducer("csbr-YiCusRelation-flink");
            //转换对象为String
            DataStream<String> kafkaYiResult = writeYiCusRelation.map(new MapFunction<CusRelationEntity, String>() {
                @Override
                public String map(CusRelationEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYiResult.addSink(myYiProducer);

            //过滤回写数据(四方云)
            DataStream<CusRelationEntity> writeSFCusRelation = filterSFCusRelation.map(new MapFunction<BinLogMsgEntity, CusRelationEntity>() {
                @Override
                public CusRelationEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusRelationEntity cusRelationEntity = new CusRelationEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ChineseName")!=null){
                        //根据名称判断唯一性
                        Boolean flag = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                        if(flag != null && !flag){
                            //根据名称调用api查询GUID
                            String result = FlinkApiServer.getCusGuid(jsonObject.get("ChineseName").toString());
                            if(StringUtils.isNotEmpty(result)){
                                cusRelationEntity.setGuid(result);
                            }
                            if(jsonObject.get("GUID")!=null){
                                cusRelationEntity.setSourceGuid(jsonObject.get("GUID")+"");
                            }
                            //四方云来源
                            cusRelationEntity.setSourceData("3");
                        }
                    }
                    return cusRelationEntity;
                }
            });

            //落地数据库(四方云)
            writeSFCusRelation.addSink(new CusRelationMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFCusRelation-flink");
            //转换对象为String
            DataStream<String> kafkaSFResult = writeSFCusRelation.map(new MapFunction<CusRelationEntity, String>() {
                @Override
                public String map(CusRelationEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeYaoCusRelation.print().setParallelism(1).name("药链云-CusRelation");
            writeYiCusRelation.print().setParallelism(1).name("医链云-CusRelation");
            writeSFCusRelation.print().setParallelism(1).name("四方云-CusRelation");

            //执行数据流
            env.execute("csbr_CusRelation_job");
        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
