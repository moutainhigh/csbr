package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.model.MFCustomerInfoEntity;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PCustomerTypeSumMySQLSink;
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
 * @date 2020/1/7 14:03
 *
 */
@Slf4j
public class PCustomerTypeSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            DataStream dataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //过滤
            DataStream filterPCustomerTypeSum = dataStream.filter(new FilterFunction<BinLogMsgEntity>(){

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

            //
            DataStream writePCustomerTypeSum = filterPCustomerTypeSum.map(new MapFunction<BinLogMsgEntity, PCustomerTypeSumEntity>(){

                @Override
                public PCustomerTypeSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PCustomerTypeSumEntity pCustomerTypeSumEntity = new PCustomerTypeSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("ChineseName") != null){
                        Boolean result = FlinkApiServer.checkCusExist(jsonObject.get("ChineseName").toString());
                        if(result != null && !result){
                            if(jsonObject.get("EnterpriseClass")!=null){
                                //医疗机构统计
                                if(jsonObject.get("EnterpriseClass").equals("医疗机构")){
                                    pCustomerTypeSumEntity.setTotleMedIns(1L);
                                }
                                if(jsonObject.get("EnterpriseType")!=null){
                                    //生产企业统计
                                    if(jsonObject.get("EnterpriseClass").equals("医药公司")&&jsonObject.get("EnterpriseType").equals("1")){
                                        pCustomerTypeSumEntity.setTotlePro(1L);
                                    }
                                    //流通企业统计
                                    if(jsonObject.get("EnterpriseClass").equals("医药公司")&&jsonObject.get("EnterpriseType").equals("2")){
                                        pCustomerTypeSumEntity.setTotleDelivery(1L);
                                    }
                                }
                            }
                        }
                    }
                    return pCustomerTypeSumEntity;
                }
            });

            //累加器
//            DataStream<PCustomerTypeSumEntity> sumPCustomerTypeSum = writePCustomerTypeSum.map(new RichMapFunction<PCustomerTypeSumEntity,PCustomerTypeSumEntity>() {
//                //定义累加器
//                //生产机构统计
//                private LongCounter totlePro = new LongCounter();
//                //流通企业统计
//                private LongCounter totleDelivery = new LongCounter();
//                //医疗机构统计
//                private LongCounter totleMedIns = new LongCounter();
//
//                @Override
//                public void open(Configuration parameters) throws Exception {
//                    super.open(parameters);
//
//                    //第二步：注册累加器
//                    getRuntimeContext().addAccumulator("totle-pro", this.totlePro);
//
//                    getRuntimeContext().addAccumulator("totle-delivery", this.totleDelivery);
//
//                    getRuntimeContext().addAccumulator("totle-medIns", this.totleMedIns);
//                }
//
//                @Override
//                public PCustomerTypeSumEntity map(PCustomerTypeSumEntity o) throws Exception {
//                    //累加
//                    if(o.getTotlePro() != null){
//                        this.totlePro.add(o.getTotlePro());
//                    }else{
//                        o.setTotlePro(this.totlePro.getLocalValue());
//                    }
//                    if(o.getTotleDelivery() != null){
//                        this.totleDelivery.add(o.getTotleDelivery());
//                    }else{
//                        o.setTotleDelivery(this.totleDelivery.getLocalValue());
//                    }
//                    if(o.getTotleMedIns() != null){
//                        this.totleMedIns.add(o.getTotleMedIns());
//                    }else{
//                        o.setTotleMedIns(this.totleMedIns.getLocalValue());
//                    }
//                    return o;
//                }
//            });

            //落地数据库
            writePCustomerTypeSum.addSink(new PCustomerTypeSumMySQLSink());
            //落地kafka
            FlinkKafkaProducer myProducer = KafkaSink.flinkKafkaProducer("csbr-PCustomerTypeSum-flink");
            //转换对象为String
            DataStream<String> kafkaResult = writePCustomerTypeSum.map(new MapFunction<PCustomerTypeSumEntity, String>() {
                @Override
                public String map(PCustomerTypeSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaResult.addSink(myProducer);
            //打印数据
            writePCustomerTypeSum.print().setParallelism(1).name("药链云-PCustomerTypeSum");
            //执行数据
            env.execute("csbr_PCustomerTypeSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }

}
