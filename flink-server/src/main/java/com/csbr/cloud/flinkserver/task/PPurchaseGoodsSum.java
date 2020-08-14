package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.PGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.model.PPurchaseGoodsSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PPurchaseGoodsSumMySQLSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/10 12:05
 * 平台医疗机构采购量汇总
 */
@Slf4j
public class PPurchaseGoodsSum {
    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //医链云
            DataStream yiDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC3,env);
            //过滤(医链云)
            DataStream filterYiPPurchaseGoodsSum = yiDataStream.filter(new FilterFunction<BinLogMsgEntity>(){

                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("INSERT")) {
                        if (binLogMsgEntity.getTable().equals("trsupplierpo")) {
                            return true;
                        }
                    }
                    return false;
                }
            });

            //过滤回写数据(医链云)
            DataStream<PPurchaseGoodsSumEntity> writeYiPPurchaseGoodsSum = filterYiPPurchaseGoodsSum.map(new MapFunction<BinLogMsgEntity, PPurchaseGoodsSumEntity>() {
                @Override
                public PPurchaseGoodsSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PPurchaseGoodsSumEntity pPurchaseGoodsSumEntity = new PPurchaseGoodsSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("DetailSum")!=null){
                        //采购金额
                        if(jsonObject.get("state")!=null){
                            if(jsonObject.get("state").equals("D")){
                                //删除订单 增加负数
                                pPurchaseGoodsSumEntity.setTotlePurchaseAmount(-Double.parseDouble(jsonObject.get("DetailSum")+""));
                            }else{
                                pPurchaseGoodsSumEntity.setTotlePurchaseAmount(Double.parseDouble(jsonObject.get("DetailSum")+""));
                            }
                        }

                    }
                    if(jsonObject.get("BillDate")!=null){
                        //年
                        pPurchaseGoodsSumEntity.setYear(jsonObject.get("BillDate").toString().substring(0,4));
                        //月
                        pPurchaseGoodsSumEntity.setMonth(jsonObject.get("BillDate").toString().substring(4,6));
                    }
                    return pPurchaseGoodsSumEntity;
                }
            });

            //落地数据库(医链云)
            writeYiPPurchaseGoodsSum.addSink(new PPurchaseGoodsSumMySQLSink());
            //落地kafka(医链云)
            FlinkKafkaProducer myYiProducer = KafkaSink.flinkKafkaProducer("csbr-YiPPurchaseGoodsSum-flink");
            //转换对象为String
            DataStream<String> kafkaYiResult = writeYiPPurchaseGoodsSum.map(new MapFunction<PPurchaseGoodsSumEntity, String>() {
                @Override
                public String map(PPurchaseGoodsSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYiResult.addSink(myYiProducer);

            //打印数据
            writeYiPPurchaseGoodsSum.print().setParallelism(1).name("医链云-PPurchaseGoodsSum");

            //执行数据
            env.execute("csbr_PPurchaseGoodsSum_job");
        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
