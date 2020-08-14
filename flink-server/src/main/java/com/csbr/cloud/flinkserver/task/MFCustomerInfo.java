package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.constant.PropertiesConstants;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.model.MFCustomerInfoEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.MFCustomerInfoMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @author zhangheng
 * @date 2020/1/7 10:27
 * 用户信息计算
 */
@Slf4j
public class MFCustomerInfo {

    public static void main(String[] args) {
        try {
            // 1.初始化两个数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
            env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);
            env.getCheckpointConfig().setCheckpointTimeout(10000);
            env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
            env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);
            Properties consumerProps = new Properties();
            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.put("auto.offset.reset", "latest");
            //药链云
            FlinkKafkaConsumer myConsumer = new FlinkKafkaConsumer(KafkaConstant.TOPIC2, new SimpleStringSchema(), consumerProps);
            //数据整理映射为BinLogMsgEntity数据
            DataStream dataStream = env.addSource(myConsumer).map(new MapFunction<String, BinLogMsgEntity>() {
                @Override
                public BinLogMsgEntity map(String value) throws Exception {

                    BinLogMsgEntity msgEntity = JSON.parseObject(value,
                            new TypeReference<BinLogMsgEntity>() {});
                    return msgEntity;
                }
            });

            //过滤
            //平台签约GSP仓统计过滤
            DataStream filterMFCustomerInfo = dataStream.filter(new FilterFunction<BinLogMsgEntity>() {
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

            //写入用户数据
            DataStream<MFCustomerInfoEntity> writeMFCustomerInfo = filterMFCustomerInfo.map(new MapFunction<BinLogMsgEntity, MFCustomerInfoEntity>(){

                @Override
                public MFCustomerInfoEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    MFCustomerInfoEntity mfCustomerInfoEntity = new MFCustomerInfoEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ChineseName")!=null){
                        Boolean result = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                        if(result != null){
                            //为false则添加
                            if(!result){
                                //自动生成UUID
                                mfCustomerInfoEntity.setGuid(String.valueOf(UUID.randomUUID()).replace("-", ""));
                                mfCustomerInfoEntity.setChineseName(jsonObject.getString("ChineseName"));
                                if(jsonObject.get("EnterpriseType")!=null){
                                    mfCustomerInfoEntity.setEnterpriseType(jsonObject.getString("EnterpriseType"));
                                }
                                if(jsonObject.get("EnterpriseClass")!=null){
                                    mfCustomerInfoEntity.setEnterpriseClass(jsonObject.getString("EnterpriseClass"));
                                }
                                //医疗机构等级默认为空
                                mfCustomerInfoEntity.setMedLevel(null);
                                if(jsonObject.get("Province")!=null){
                                    mfCustomerInfoEntity.setProvince(jsonObject.getString("Province"));
                                }
                                if(jsonObject.get("City")!=null){
                                    mfCustomerInfoEntity.setCity(jsonObject.getString("City"));
                                }
                                if(jsonObject.get("District")!=null){
                                    mfCustomerInfoEntity.setDistrict(jsonObject.getString("District"));
                                }
                                if(jsonObject.get("Address")!=null){
                                    mfCustomerInfoEntity.setAddress(jsonObject.getString("Address"));
                                }
                                if(jsonObject.get("Contacts")!=null){
                                    mfCustomerInfoEntity.setContacts(jsonObject.getString("Contacts"));
                                }
                                if(jsonObject.get("ContactTel")!=null){
                                    mfCustomerInfoEntity.setContactTel(jsonObject.getString("ContactTel"));
                                }
                                if(jsonObject.get("CREATE_TIME")!=null){
                                    mfCustomerInfoEntity.setCreateTime(jsonObject.getDate("CREATE_TIME"));
                                }
                            }
                        }
                    }
                    return mfCustomerInfoEntity;
                }
            });
            //落地数据库
            writeMFCustomerInfo.addSink(new MFCustomerInfoMySQLSink());

            //落地kafka
            FlinkKafkaProducer myProducer = KafkaSink.flinkKafkaProducer("csbr-MFCustomerInfo-flink");
            //转换对象为String
            DataStream<String> kafkaResult = writeMFCustomerInfo.map(new MapFunction<MFCustomerInfoEntity, String>() {
                @Override
                public String map(MFCustomerInfoEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaResult.addSink(myProducer);
            //打印数据
            writeMFCustomerInfo.print().setParallelism(1).name("药链云-MFCustomerInfo");

            env.execute("csbr_MFCustomerInfo_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
