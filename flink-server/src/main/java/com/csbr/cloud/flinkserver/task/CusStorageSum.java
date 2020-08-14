package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CusDeliverySumEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
import com.csbr.cloud.flinkserver.sink.CusStorageSumMySQLSink;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/8 10:23
 * 用户在线仓库统计
 */
@Slf4j
public class CusStorageSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream yaodataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云数据源
            DataStream sfdataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4, env);
            //过滤(药链云)
            DataStream filterYaoCusStorageSum = yaodataStream.filter(new FilterFunction<BinLogMsgEntity>(){
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
            DataStream filterSFCusStorageSum = sfdataStream.filter(new FilterFunction<BinLogMsgEntity>(){
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

            //过滤回写数据(药链云)
            DataStream<CusStorageSumEntity> writeYaoCusStorageSum = filterYaoCusStorageSum.map(new MapFunction<BinLogMsgEntity, CusStorageSumEntity>() {
                @Override
                public CusStorageSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusStorageSumEntity cusStorageSumEntity = new CusStorageSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                        //gsp厂库汇总
                        if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                            if(jsonObject.get("CorpName")!=null){
                                //根据名称调用api获取GUID
                                //根据GUID查询数据库是否有相同记录
                            }
                            cusStorageSumEntity.setTotleGSP(1L);
                            if(jsonObject.get("TotalAreaAge")!=null){
                                cusStorageSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                            }
                            //三方仓库汇总
                            if(jsonObject.get("ThreePartyWarehouse")!=null){
                                if(jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                    cusStorageSumEntity.setThreeGSPCount(1L);
                                    if(jsonObject.get("TotalAreaAge")!=null){
                                        cusStorageSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                    }
                                }
                            }
                        }
                    }
                    return cusStorageSumEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoCusStorageSum.addSink(new CusStorageSumMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoCusStorageSum-flink");
            DataStream<String> kafkaYaoResult = writeYaoCusStorageSum.map(new MapFunction<CusStorageSumEntity, String>() {
                @Override
                public String map(CusStorageSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);


            //过滤回写数据(四方云)
            DataStream<CusStorageSumEntity> writeSFCusStorageSum = filterSFCusStorageSum.map(new MapFunction<BinLogMsgEntity, CusStorageSumEntity>() {
                @Override
                public CusStorageSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusStorageSumEntity cusStorageSumEntity = new CusStorageSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                        //gsp厂库汇总
                        if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                            if(jsonObject.get("CorpName")!=null){
                                //根据名称调用api获取GUID
                                //根据GUID查询数据库是否有相同记录
                            }
                            cusStorageSumEntity.setTotleGSP(1L);
                            if(jsonObject.get("TotalAreaAge")!=null){
                                cusStorageSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                            }
                            //三方仓库汇总
                            if(jsonObject.get("ThreePartyWarehouse")!=null){
                                if(jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                    cusStorageSumEntity.setThreeGSPCount(1L);
                                    if(jsonObject.get("TotalAreaAge")!=null){
                                        cusStorageSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                    }
                                }
                            }
                        }
                    }
                    return cusStorageSumEntity;
                }
            });

            //落地数据库(四方云)
            writeSFCusStorageSum.addSink(new CusStorageSumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFCusStorageSum-flink");
            DataStream<String> kafkaSFResult = writeSFCusStorageSum.map(new MapFunction<CusStorageSumEntity, String>() {
                @Override
                public String map(CusStorageSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeYaoCusStorageSum.print().setParallelism(1).name("药链云-CusStorageSum");
            writeSFCusStorageSum.print().setParallelism(1).name("四方云-CusStorageSum");
            //执行数据流
            env.execute("csbr_CusStorageSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
