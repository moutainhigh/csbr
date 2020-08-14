package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CSGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.model.PMasterRelationEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PMasterRelationMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/10 15:52
 * 医疗机构供应商关系
 */
@Slf4j
public class PMasterRelation {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //四方云数据源
            DataStream sfDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4, env);
            //过滤(四方云)
            DataStream filterSFPMasterRelation = sfDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfmasterrelation")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(四方云)
            DataStream<PMasterRelationEntity> writeSFPMasterRelation = filterSFPMasterRelation.map(new MapFunction<BinLogMsgEntity, PMasterRelationEntity>() {
                @Override
                public PMasterRelationEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PMasterRelationEntity pMasterRelationEntity = new PMasterRelationEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("GUID")!=null){
                        pMasterRelationEntity.setGuid(jsonObject.get("GUID")+"");
                    }
                    if(jsonObject.get("EnterpriseGUID")!=null){
                        pMasterRelationEntity.setEnterpriseGUID(jsonObject.get("EnterpriseGUID")+"");
                    }
                    if(jsonObject.get("RelationEntGUID")!=null){
                        pMasterRelationEntity.setRelationEntGUID(jsonObject.get("RelationEntGUID")+"");
                    }
                    if(jsonObject.get("RelationType")!=null){
                        pMasterRelationEntity.setRelationType(jsonObject.get("RelationType")+"");
                    }
                    if(jsonObject.get("RelationCreateTime")!=null){
                        pMasterRelationEntity.setRelationCreateTime(jsonObject.get("RelationCreateTime")+"");
                    }
                    if(jsonObject.get("State")!=null){
                        pMasterRelationEntity.setState(jsonObject.get("State")+"");
                    }
                    return pMasterRelationEntity;
                }
            });

            //落地数据库(四方云)
            writeSFPMasterRelation.addSink(new PMasterRelationMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFPMasterRelation-flink");
            DataStream<String> kafkaSFResult = writeSFPMasterRelation.map(new MapFunction<PMasterRelationEntity, String>() {
                @Override
                public String map(PMasterRelationEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeSFPMasterRelation.print().setParallelism(1).name("四方云-PMasterRelation");

            //执行数据
            env.execute("csbr_PMasterRelation_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
