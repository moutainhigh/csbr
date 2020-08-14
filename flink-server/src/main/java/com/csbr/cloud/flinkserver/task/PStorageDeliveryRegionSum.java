package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
import com.csbr.cloud.flinkserver.model.PStorageDeliveryRegionSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PStorageDeliveryRegionSumMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/9 10:29
 * 平台仓库运力分布
 */
@Slf4j
public class PStorageDeliveryRegionSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云数据源
            DataStream YaoDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云数据源
            DataStream SFDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4,env);
            //过滤(药链云)
            DataStream filterYaoPStorageDeliveryRegionSum = YaoDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfwarehouse")||binLogMsgEntity.getTable().equals("mfmasterdata")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFPStorageDeliveryRegionSum = SFDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
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
            DataStream<PStorageDeliveryRegionSumEntity> writeYaoPStorageDeliveryRegionSum = filterYaoPStorageDeliveryRegionSum.map(new MapFunction<BinLogMsgEntity, PStorageDeliveryRegionSumEntity>() {
                @Override
                public PStorageDeliveryRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PStorageDeliveryRegionSumEntity pStorageDeliveryRegionSumEntity = new PStorageDeliveryRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(binLogMsgEntity.getTable().equals("mfwarehouse")){
                        if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                            //GSP仓库汇总
                            if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                                //根据ID获取省(调用api接口)
//                            pStorageDeliveryRegionSumEntity.setProvince();
                                //根据ID获取市(调用api接口)
                                //根据ID获取区(调用api接口)
                                //GSP仓库总数量
                                pStorageDeliveryRegionSumEntity.setTotleGSP(1L);
                                //仓库面积
                                if(jsonObject.get("TotalAreaAge")!=null){
                                    pStorageDeliveryRegionSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                }
                                //三方仓库汇总
                                if(jsonObject.get("ThreePartyWarehouse")!=null&&jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                    //根据ID获取省(调用api接口)
//                            pStorageDeliveryRegionSumEntity.setProvince();
                                    //根据ID获取市(调用api接口)
                                    //根据ID获取区(调用api接口)
                                    //GSP仓库总数量
                                    pStorageDeliveryRegionSumEntity.setThreeGSPCount(1L);
                                    if(jsonObject.get("TotalAreaAge")!=null){
                                        pStorageDeliveryRegionSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                    }
                                }
                            }
                        }
                    }

                    //运力数量
                    if(binLogMsgEntity.getTable().equals("mfmasterdata")){
                        if(jsonObject.get("ChineseName") != null){
                            Boolean result = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                            if(result != null && !result){
                                if(jsonObject.get("EnterpriseClass")!=null && jsonObject.get("EnterpriseClass").equals("物流公司")){
                                    //省
                                    if(jsonObject.get("Province")!=null){
                                        pStorageDeliveryRegionSumEntity.setProvince(jsonObject.get("Province")+"");
                                    }
                                    //市
                                    if(jsonObject.get("City")!=null){
                                        pStorageDeliveryRegionSumEntity.setCity(jsonObject.get("City")+"");
                                    }
                                    //区
                                    if(jsonObject.get("District")!=null){
                                        pStorageDeliveryRegionSumEntity.setDistrict(jsonObject.get("District")+"");
                                    }
                                    pStorageDeliveryRegionSumEntity.setTotleDelivery(1L);
                                }
                            }
                        }
                    }
                    return pStorageDeliveryRegionSumEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoPStorageDeliveryRegionSum.addSink(new PStorageDeliveryRegionSumMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoPStorageDeliveryRegionSum-flink");
            DataStream<String> kafkaYaoResult = writeYaoPStorageDeliveryRegionSum.map(new MapFunction<PStorageDeliveryRegionSumEntity, String>() {
                @Override
                public String map(PStorageDeliveryRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);

            //过滤回写数据(四方云)
            DataStream<PStorageDeliveryRegionSumEntity> writeSFPStorageDeliveryRegionSum = filterSFPStorageDeliveryRegionSum.map(new MapFunction<BinLogMsgEntity, PStorageDeliveryRegionSumEntity>() {
                @Override
                public PStorageDeliveryRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PStorageDeliveryRegionSumEntity pStorageDeliveryRegionSumEntity = new PStorageDeliveryRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ISGSP")!=null&&jsonObject.get("IsDeleted")!=null){
                        //GSP仓库汇总
                        if(jsonObject.get("ISGSP").equals("Y")&&jsonObject.get("IsDeleted").equals("N")){
                            //根据ID获取省(调用api接口)
//                            pStorageDeliveryRegionSumEntity.setProvince();
                            //根据ID获取市(调用api接口)
                            //根据ID获取区(调用api接口)
                            //GSP仓库总数量
                            pStorageDeliveryRegionSumEntity.setTotleGSP(1L);
                            //仓库面积
                            if(jsonObject.get("TotalAreaAge")!=null){
                                pStorageDeliveryRegionSumEntity.setTotleArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                            }
                            //三方仓库汇总
                            if(jsonObject.get("ThreePartyWarehouse")!=null&&jsonObject.get("ThreePartyWarehouse").equals("Y")){
                                //根据ID获取省(调用api接口)
//                            pStorageDeliveryRegionSumEntity.setProvince();
                                //根据ID获取市(调用api接口)
                                //根据ID获取区(调用api接口)
                                //GSP仓库总数量
                                pStorageDeliveryRegionSumEntity.setThreeGSPCount(1L);
                                if(jsonObject.get("TotalAreaAge")!=null){
                                    pStorageDeliveryRegionSumEntity.setThreeArea(Double.parseDouble(jsonObject.get("TotalAreaAge")+""));
                                }
                            }
                        }
                    }
                    return pStorageDeliveryRegionSumEntity;
                }
            });

            //落地数据库(四方云)
            writeSFPStorageDeliveryRegionSum.addSink(new PStorageDeliveryRegionSumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFPStorageDeliveryRegionSum-flink");
            DataStream<String> kafkaSFResult = writeSFPStorageDeliveryRegionSum.map(new MapFunction<PStorageDeliveryRegionSumEntity, String>() {
                @Override
                public String map(PStorageDeliveryRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeYaoPStorageDeliveryRegionSum.print().setParallelism(1).name("药链云-PStorageDeliveryRegionSum");
            writeSFPStorageDeliveryRegionSum.print().setParallelism(1).name("四方云-PStorageDeliveryRegionSum");

            //执行数据
            env.execute("csbr_PStorageDeliveryRegionSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
