package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CSGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.model.CusDeliverySumEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
import com.csbr.cloud.flinkserver.sink.CusDeliverySumMySQLSink;
import com.csbr.cloud.flinkserver.sink.CusStorageSumMySQLSink;
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
 * @date 2020/1/9 16:20
 * 用户在线运力统计
 */
@Slf4j
public class CusDeliverySum {
    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream yaodataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云数据源
            DataStream sfdataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4, env);
            //过滤(药链云)
            DataStream filterYaoCusDeliverySum = yaodataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfvehicle")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFCusDeliverySum = sfdataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfvehicle")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(药链云)
            DataStream<CusDeliverySumEntity> writeYaoCusDeliverySum = filterYaoCusDeliverySum.map(new MapFunction<BinLogMsgEntity, CusDeliverySumEntity>() {
                @Override
                public CusDeliverySumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusDeliverySumEntity cusDeliverySumEntity = new CusDeliverySumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("IsDeleted")!=null){
                        //在线运营车辆汇总
                        if(jsonObject.get("IsDeleted").equals("N")){
                            if(jsonObject.get("CarrierGuid")!=null){
                                //根据CarrierGuid调用api获取GUID
                                jsonObject.put("sourceData","2");
                                jsonObject.put("sourceGuid",jsonObject.get("CarrierGuid")+"");
                                String result = FlinkApiServer.getGuidBySource(jsonObject);
                                if(StringUtils.isNotEmpty(result)){
                                    cusDeliverySumEntity.setGuid(result);
                                    cusDeliverySumEntity.setTotleCar(1L);
                                }
                            }
                        }
                    }
                    return cusDeliverySumEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoCusDeliverySum.addSink(new CusDeliverySumMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoCusDeliverySum-flink");
            DataStream<String> kafkaYaoResult = writeYaoCusDeliverySum.map(new MapFunction<CusDeliverySumEntity, String>() {
                @Override
                public String map(CusDeliverySumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);

            //过滤回写数据(四方云)
            DataStream<CusDeliverySumEntity> writeSFCusDeliverySum = filterSFCusDeliverySum.map(new MapFunction<BinLogMsgEntity, CusDeliverySumEntity>() {
                @Override
                public CusDeliverySumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CusDeliverySumEntity cusDeliverySumEntity = new CusDeliverySumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("IsDeleted")!=null){
                        //在线运营车辆汇总
                        if(jsonObject.get("IsDeleted").equals("N")){
                            if(jsonObject.get("CarrierGuid")!=null){
                                //根据CarrierGuid调用api获取GUID
                                jsonObject.put("sourceData","3");
                                jsonObject.put("sourceGuid",jsonObject.get("CarrierGuid")+"");
                                String result = FlinkApiServer.getGuidBySource(jsonObject);
                                if(StringUtils.isNotEmpty(result)){
                                    cusDeliverySumEntity.setGuid(result);
                                    cusDeliverySumEntity.setTotleCar(1L);
                                }
                            }
                        }
                    }
                    return cusDeliverySumEntity;
                }
            });

            //落地数据库(四方云)
            writeSFCusDeliverySum.addSink(new CusDeliverySumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFCusDeliverySum-flink");
            DataStream<String> kafkaSFResult = writeSFCusDeliverySum.map(new MapFunction<CusDeliverySumEntity, String>() {
                @Override
                public String map(CusDeliverySumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeYaoCusDeliverySum.print().setParallelism(1).name("药链云-CusDeliverySum");
            writeSFCusDeliverySum.print().setParallelism(1).name("四方云-CusDeliverySum");

            //执行数据
            env.execute("csbr_CusDeliverySum_job");
        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
