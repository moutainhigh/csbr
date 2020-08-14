package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CSGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.model.PPurchaseGoodsSumEntity;
import com.csbr.cloud.flinkserver.sink.CSGoodsRegionSumMySQLSink;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/1/10 14:53
 * 发货量汇总
 */
@Slf4j
public class CSGoodsRegionSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //四方云数据源
            DataStream sfDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4, env);
            //过滤(药链云)
            DataStream filterSFCSGoodsRegionSum = sfDataStream.filter(new FilterFunction<BinLogMsgEntity>(){
                @Override
                public boolean filter(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    if (binLogMsgEntity.getType().equals("UPDATE")) {
                        if (binLogMsgEntity.getTable().equals("trtplpomain")) {
                            return true;
                        }
                    }
                    return false;
                }
            });
            DataStream<CSGoodsRegionSumEntity> writeSFCSGoodsRegionSum = filterSFCSGoodsRegionSum.map(new MapFunction<BinLogMsgEntity, CSGoodsRegionSumEntity>() {
                @Override
                public CSGoodsRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    CSGoodsRegionSumEntity csGoodsRegionSumEntity = new CSGoodsRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);

                    if(jsonObject.get("TPLPoType")!=null && jsonObject.get("state")!=null && jsonObject.get("TenantGuid")!=null){
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("12") && jsonObject.get("TenantGuid").equals("20180104131756210001")){
                            if(jsonObject.get("GUID")!=null){
                                //传入GUID 和 trtplbb表的MainPoGuid比较 获取api的 true(相同) or false(不相同) 并查处日期
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.trCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("trTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("trTrtplbb").getTimestamp("billDate"));
                                                //年
                                                csGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                csGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //发货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.trCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            //trtplbb的IsMedOrder='Y'
                                            csGoodsRegionSumEntity.setMedicineDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        //省
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            csGoodsRegionSumEntity.setProvince(jsonObject.get("DepartureProvince")+"");
                                        }
                                        //市
                                        if(jsonObject.get("DepartureCity")!=null){
                                            csGoodsRegionSumEntity.setCity(jsonObject.get("DepartureCity")+"");
                                        }
                                        //区
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            csGoodsRegionSumEntity.setDistrict(jsonObject.get("DepartureDistrict")+"");
                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            csGoodsRegionSumEntity.setTotleDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                        //订单删除时
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("D") && jsonObject.get("TenantGuid").equals("20180104131756210001")){
                            if(jsonObject.get("GUID")!=null){
                                //传入GUID 和 trtplbb表的MainPoGuid比较 获取api的 true(相同) or false(不相同) 并查处日期
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.trCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("trTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("trTrtplbb").getTimestamp("billDate"));
                                                //年
                                                csGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                csGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //发货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.trCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            //trtplbb的IsMedOrder='Y'
                                            csGoodsRegionSumEntity.setMedicineDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        //省
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            csGoodsRegionSumEntity.setProvince(jsonObject.get("DepartureProvince")+"");
                                        }
                                        //市
                                        if(jsonObject.get("DepartureCity")!=null){
                                            csGoodsRegionSumEntity.setCity(jsonObject.get("DepartureCity")+"");
                                        }
                                        //区
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            csGoodsRegionSumEntity.setDistrict(jsonObject.get("DepartureDistrict")+"");
                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            csGoodsRegionSumEntity.setTotleDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return csGoodsRegionSumEntity;
                }
            });

            //落地数据库(四方云)
            writeSFCSGoodsRegionSum.addSink(new CSGoodsRegionSumMySQLSink());
            //落地kafka(四方云)
            FlinkKafkaProducer mySFProducer = KafkaSink.flinkKafkaProducer("csbr-SFCSGoodsRegionSum-flink");
            DataStream<String> kafkaSFResult = writeSFCSGoodsRegionSum.map(new MapFunction<CSGoodsRegionSumEntity, String>() {
                @Override
                public String map(CSGoodsRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFResult.addSink(mySFProducer);

            //打印数据
            writeSFCSGoodsRegionSum.print().setParallelism(1).name("四方云-CSGoodsRegionSum");

            //执行数据
            env.execute("csbr_CSGoodsRegionSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
