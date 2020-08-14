package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
import com.csbr.cloud.flinkserver.model.PDeliverySumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PDeliverySumMySQLSink;
import com.csbr.cloud.flinkserver.utils.CheckObjectIsNullUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.accumulators.LongCounter;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/7 15:25
 */
@Slf4j
public class PDeliverySum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云
            DataStream yaoDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云
            DataStream sfDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4,env);

            //过滤(药链云)
            DataStream filterYaoPDeliverySum = yaoDataStream.filter(new FilterFunction<BinLogMsgEntity>(){

                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfmasterdata")||binLogMsgEntity.getTable().equals("mfvehicle")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤(四方云)
            DataStream filterSFPDeliverySum = sfDataStream.filter(new FilterFunction<BinLogMsgEntity>(){

                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")||binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("mfvehicle")||binLogMsgEntity.getTable().equals("mflogisticsoutlets")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(药链云)
            DataStream writeYaoPDeliverySum = filterYaoPDeliverySum.map(new MapFunction<BinLogMsgEntity, PDeliverySumEntity>(){

                @Override
                public PDeliverySumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PDeliverySumEntity pDeliverySumEntity = new PDeliverySumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(binLogMsgEntity.getType().equals("INSERT") && binLogMsgEntity.getTable().equals("mfmasterdata")){
                        if(jsonObject.get("ChineseName") != null){
                            Boolean result = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                            if(result != null && !result){
                                if(jsonObject.get("EnterpriseClass")!=null){
                                    if(jsonObject.get("EnterpriseClass").equals("物流公司")){
                                        //平台运力
                                        pDeliverySumEntity.setTotleDelivery(1L);
                                        //物流公司总车辆
                                        if(jsonObject.get("CarSumCount")!=null){
                                            pDeliverySumEntity.setTotleCarSum(Long.parseLong(jsonObject.get("CarSumCount")+""));
                                        }
                                        //冷藏车总数统计
                                        if(jsonObject.get("CarColdCount")!=null){
                                            pDeliverySumEntity.setChillCarSum(Long.parseLong(jsonObject.get("CarColdCount")+""));
                                        }
                                        //分拨中心
                                        if(jsonObject.get("DistribeCenterCount")!=null){
                                            pDeliverySumEntity.setFenboNumber(Long.parseLong(jsonObject.get("DistribeCenterCount")+""));
                                        }
                                        //物流网点总数量
                                        if(jsonObject.get("OutletsCount")!=null){
                                            pDeliverySumEntity.setDotTotle(Long.parseLong(jsonObject.get("OutletsCount")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //在线运营车辆
                    if(binLogMsgEntity.getTable().equals("mfvehicle")){
                        if(jsonObject.get("IsDeleted")!=null && jsonObject.get("IsDeleted").equals("N")){
                            pDeliverySumEntity.setTotleCar(1L);
                            //在线运营冷藏车辆
                            if(jsonObject.get("VehicleTypeGuid")!=null){
                                //根据VehicleTypeGuid调用api查询true or false 并且mfvehicletype的`VehicleKind`=2
                                jsonObject.put("guid",jsonObject.get("VehicleTypeGuid")+"");
                                jsonObject.put("vehicleKind","2");
                                Boolean result = FlinkApiServer.meCheckVehicleType(jsonObject);
                                if(result != null && result){
                                    //true
                                    pDeliverySumEntity.setChillCar(1L);
                                }
                            }
                        }
                    }
                    return pDeliverySumEntity;
                }
            });

            //落地数据库(药链云)
            writeYaoPDeliverySum.addSink(new PDeliverySumMySQLSink());
            //落地kafka(药链云)
            FlinkKafkaProducer myYaoProducer = KafkaSink.flinkKafkaProducer("csbr-YaoPDeliverySum-flink");
            //转换对象为String
            DataStream<String> kafkaYaoResult = writeYaoPDeliverySum.map(new MapFunction<PDeliverySumEntity, String>() {
                @Override
                public String map(PDeliverySumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoResult.addSink(myYaoProducer);

            //过滤回写数据(四方云)
            DataStream writeSFPDeliverySum = filterSFPDeliverySum.map(new MapFunction<BinLogMsgEntity, PDeliverySumEntity>(){

                @Override
                public PDeliverySumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PDeliverySumEntity pDeliverySumEntity = new PDeliverySumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    //在线运营车辆
                    if(binLogMsgEntity.getTable().equals("mfvehicle")){
                        if(jsonObject.get("IsDeleted")!=null && jsonObject.get("IsDeleted").equals("N")){
                            pDeliverySumEntity.setTotleCar(1L);
                            //在线运营冷藏车辆
                            if(jsonObject.get("VehicleTypeGuid")!=null){
                                //根据VehicleTypeGuid调用api查询true or false 并且mfvehicletype的`VehicleKind`=2
                                jsonObject.put("guid",jsonObject.get("VehicleTypeGuid")+"");
                                jsonObject.put("vehicleKind","2");
                                Boolean result = FlinkApiServer.trCheckVehicleType(jsonObject);
                                if(result != null && result){
                                    //true
                                    pDeliverySumEntity.setChillCar(1L);
                                }
                            }
                        }
                    }

                    //在线物流网点数
                    if(binLogMsgEntity.getTable().equals("mflogisticsoutlets")){
                        if(jsonObject.get("IsDeleted")!=null && jsonObject.get("IsDeleted").equals("N")){
                            pDeliverySumEntity.setDotNumber(1L);
                        }
                    }
                    return pDeliverySumEntity;
                }
            });
            //落地数据库(四方云)
            writeSFPDeliverySum.addSink(new PDeliverySumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFPDeliverySum-flink");
            //转换对象为String
            DataStream<String> kafkaSFResult = writeSFPDeliverySum.map(new MapFunction<PDeliverySumEntity, String>() {
                @Override
                public String map(PDeliverySumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //累加器
//            DataStream<PDeliverySumEntity> sumPDeliverySum = writePDeliverySum.map(new RichMapFunction<PDeliverySumEntity,PDeliverySumEntity>() {
//                //定义累加器
//                //平台运力
//                private LongCounter totleDelivery = new LongCounter();
//                //物流公司总车辆
//                private LongCounter totleCarSum = new LongCounter();
//                //冷藏车总数统计
//                private LongCounter chillCarSum = new LongCounter();
//                //分拨中心
//                private LongCounter fenboNumber = new LongCounter();
//                //物流网点总数量
//                private LongCounter dotTotle = new LongCounter();
//                //在线运营车辆
//                private LongCounter totleCar = new LongCounter();
//                //在线运营冷藏车辆
//                private LongCounter chillCar = new LongCounter();
//                //在线物流网点数
//                private LongCounter dotNumber = new LongCounter();
//
//                @Override
//                public void open(Configuration parameters) throws Exception {
//                    super.open(parameters);
//
//                    //第二步：注册累加器
//                    getRuntimeContext().addAccumulator("totle-delivery", this.totleDelivery);
//
//                    getRuntimeContext().addAccumulator("totle-car-sum", this.totleCarSum);
//
//                    getRuntimeContext().addAccumulator("chill-car-sum", this.chillCarSum);
//
//                    getRuntimeContext().addAccumulator("fenbo-number", this.fenboNumber);
//
//                    getRuntimeContext().addAccumulator("dot-totle", this.dotTotle);
//
//                    getRuntimeContext().addAccumulator("totle-car", this.totleCar);
//
//                    getRuntimeContext().addAccumulator("chill-car", this.chillCar);
//
//                    getRuntimeContext().addAccumulator("dot-number", this.dotNumber);
//                }
//
//                @Override
//                public PDeliverySumEntity map(PDeliverySumEntity o) throws Exception {
//                    //累加
//                    if(o.getTotleDelivery() != null){
//                        this.totleDelivery.add(o.getTotleDelivery());
//                    }else{
//                        o.setTotleDelivery(this.totleDelivery.getLocalValue());
//                    }
//                    if(o.getTotleCarSum() != null){
//                        this.totleCarSum.add(o.getTotleCarSum());
//                    }else{
//                        o.setTotleCarSum(this.totleCarSum.getLocalValue());
//                    }
//                    if(o.getChillCarSum() != null){
//                        this.chillCarSum.add(o.getChillCarSum());
//                    }else{
//                        o.setChillCarSum(this.chillCarSum.getLocalValue());
//                    }
//                    if(o.getFenboNumber() != null){
//                        this.fenboNumber.add(o.getFenboNumber());
//                    }else{
//                        o.setFenboNumber(this.fenboNumber.getLocalValue());
//                    }
//                    if(o.getDotTotle() != null){
//                        this.dotTotle.add(o.getDotTotle());
//                    }else{
//                        o.setDotTotle(this.dotTotle.getLocalValue());
//                    }
//                    if(){
//
//                    }
//                    return o;
//                }
//            });

            //打印数据
            writeYaoPDeliverySum.print().setParallelism(1).name("药链云-PDeliverySum");
            writeSFPDeliverySum.print().setParallelism(1).name("四方云-PDeliverySum");
            //执行数据
            env.execute("csbr_PDeliverySum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
