package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.flinkserver.config.KafkaConstant;
import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.feign.FlinkApiServer;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.PGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.sink.KafkaSink;
import com.csbr.cloud.flinkserver.sink.PGoodsRegionSumMySQLSink;
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
 * @date 2020/1/10 17:03
 * 平台货量区域分布
 */
@Slf4j
public class PGoodsRegionSum {

    public static void main(String[] args) {
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //药链云
            DataStream yaoDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC2,env);
            //四方云
            DataStream sfDataStream = SourceKafka.getFlinkDataStreamKafkaSource(KafkaConstant.TOPIC4,env);

            //过滤(药链云)
            DataStream filterYaoPGoodsRegionSum = yaoDataStream.filter(new FilterFunction<BinLogMsgEntity>(){

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

            //过滤(四方云)
            DataStream filterSFPGoodsRegionSum = sfDataStream.filter(new FilterFunction<BinLogMsgEntity>(){

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

            //过滤回写发货量(药链云)
            DataStream<PGoodsRegionSumEntity> writeYaoDeliverPGoodsRegionSum = filterYaoPGoodsRegionSum.map(new MapFunction<BinLogMsgEntity, PGoodsRegionSumEntity>() {
                @Override
                public PGoodsRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PGoodsRegionSumEntity pGoodsRegionSumEntity = new PGoodsRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("TPLPoType")!=null && jsonObject.get("state")!=null){
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("12")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.meCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("meTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("meTrtplbb").getTimestamp("billDate"));
                                                //年
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //调用api 获取 trtplbb IsMedOrder='Y'
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.meCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            //发货量(医药)
                                            pGoodsRegionSumEntity.setMedicineDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("DepartureCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                        //订单删除时
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("D")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.meCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("meTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("meTrtplbb").getTimestamp("billDate"));
                                                //年
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //调用api 获取 trtplbb IsMedOrder='Y'
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.meCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            //发货量(医药)
                                            pGoodsRegionSumEntity.setMedicineDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("DepartureCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return pGoodsRegionSumEntity;
                }
            });

            //落地数据库(发货量--药链云)
            writeYaoDeliverPGoodsRegionSum.addSink(new PGoodsRegionSumMySQLSink());
            //落地kafka(药链云-发货量)
            FlinkKafkaProducer myYaoDeliverProducer = KafkaSink.flinkKafkaProducer("csbr-YaoDeliverPGoodsRegionSum-flink");
            //转换对象为String
            DataStream<String> kafkaYaoDeliverResult = writeYaoDeliverPGoodsRegionSum.map(new MapFunction<PGoodsRegionSumEntity, String>() {
                @Override
                public String map(PGoodsRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoDeliverResult.addSink(myYaoDeliverProducer);

            //过滤回写收货量(药链云)
            DataStream<PGoodsRegionSumEntity> writeYaoReceivingPGoodsRegionSum = filterYaoPGoodsRegionSum.map(new MapFunction<BinLogMsgEntity, PGoodsRegionSumEntity>() {
                @Override
                public PGoodsRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PGoodsRegionSumEntity pGoodsRegionSumEntity = new PGoodsRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("TPLPoType")!=null && jsonObject.get("state")!=null){
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("12")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.meCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("meTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("meTrtplbb").getTimestamp("billDate"));
                                                //年
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //调用api 获取 trtplbb IsMedOrder='Y'
                                        //收货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.meCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            pGoodsRegionSumEntity.setMedicineReceivingGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("ArrivalProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("ArrivalCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("ArrivalDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleReceivingGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                        //订单删除时
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("D")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
                                jsonObject.put("isMedOrder","N");
                                jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                JSONObject result = FlinkApiServer.meCheckTrtplbb(jsonObject);
                                if(result != null){
                                    if(result.getBoolean("checkTrtplbb")){
                                        //true 存在
                                        if(result.getJSONObject("meTrtplbb") != null){
                                            try{
                                                SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                String billDate = dateFormat.format(result.getJSONObject("meTrtplbb").getTimestamp("billDate"));
                                                //年
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //收货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.meCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            pGoodsRegionSumEntity.setMedicineReceivingGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("ArrivalProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("ArrivalCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("ArrivalDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleReceivingGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return pGoodsRegionSumEntity;
                }
            });

            //落地数据库(收货量--药链云)
            writeYaoReceivingPGoodsRegionSum.addSink(new PGoodsRegionSumMySQLSink());
            //落地kafka(药链云-收货量)
            FlinkKafkaProducer myYaoReceivingProducer = KafkaSink.flinkKafkaProducer("csbr-YaoReceivingPGoodsRegionSum-flink");
            //转换对象为String
            DataStream<String> kafkaYaoReceivingResult = writeYaoReceivingPGoodsRegionSum.map(new MapFunction<PGoodsRegionSumEntity, String>() {
                @Override
                public String map(PGoodsRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaYaoReceivingResult.addSink(myYaoReceivingProducer);

            //过滤回写发货量(四方云)
            DataStream<PGoodsRegionSumEntity> writeSFDeliverPGoodsRegionSum = filterSFPGoodsRegionSum.map(new MapFunction<BinLogMsgEntity, PGoodsRegionSumEntity>() {
                @Override
                public PGoodsRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PGoodsRegionSumEntity pGoodsRegionSumEntity = new PGoodsRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("TPLPoType")!=null && jsonObject.get("state")!=null){
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("12")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
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
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //收货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.trCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            pGoodsRegionSumEntity.setMedicineDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("DepartureCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleDeliverGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                        //订单删除时
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("D")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
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
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
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
                                            pGoodsRegionSumEntity.setMedicineDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("DepartureProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("DepartureCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("DepartureDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总发货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleDeliverGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return pGoodsRegionSumEntity;
                }
            });

            //落地数据库(发货量--四方云)
            writeSFDeliverPGoodsRegionSum.addSink(new PGoodsRegionSumMySQLSink());
            //落地kafka(四方云-发货量)
            FlinkKafkaProducer mySFDeliverProducer = KafkaSink.flinkKafkaProducer("csbr-SFDeliverPGoodsRegionSum-flink");
            //转换对象为String
            DataStream<String> kafkaSFDeliverResult = writeSFDeliverPGoodsRegionSum.map(new MapFunction<PGoodsRegionSumEntity, String>() {
                @Override
                public String map(PGoodsRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFDeliverResult.addSink(mySFDeliverProducer);

            //过滤回写收货量(四方云)
            DataStream<PGoodsRegionSumEntity> writeSFReceivingPGoodsRegionSum = filterSFPGoodsRegionSum.map(new MapFunction<BinLogMsgEntity, PGoodsRegionSumEntity>() {
                @Override
                public PGoodsRegionSumEntity map(BinLogMsgEntity binLogMsgEntity) throws Exception {
                    PGoodsRegionSumEntity pGoodsRegionSumEntity = new PGoodsRegionSumEntity();
                    List<JSONObject> data = binLogMsgEntity.getData();
                    JSONObject jsonObject = data.get(0);
                    if(jsonObject.get("TPLPoType")!=null && jsonObject.get("state")!=null){
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("12")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
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
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //收货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.trCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            pGoodsRegionSumEntity.setMedicineReceivingGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("ArrivalProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("ArrivalCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("ArrivalDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总收货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleReceivingGoods(Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                        //订单删除时
                        if(jsonObject.get("TPLPoType").equals("1") && jsonObject.get("state").equals("D")){
                            if(jsonObject.get("GUID")!=null){
                                //调用api 比对 trtplbb的MainPoGuid是否一样 并且获取trtplbb的BillDate
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
                                                pGoodsRegionSumEntity.setYear(billDate.substring(0,4));
                                                //月
                                                pGoodsRegionSumEntity.setMonth(billDate.substring(4,6));
                                            }
                                            catch(Exception e ){
                                                e.printStackTrace();
                                                log.error("error:" + e.getMessage());
                                            }
                                        }
                                        //收货量(医药)
                                        jsonObject.put("isMedOrder","Y");
                                        jsonObject.put("mainPoGuid",jsonObject.get("GUID")+"");
                                        Boolean aBoolean = FlinkApiServer.trCheckTrtplbbIsmedorder(jsonObject);
                                        if(aBoolean != null && aBoolean){
                                            pGoodsRegionSumEntity.setMedicineReceivingGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                        if(jsonObject.get("ArrivalProvince")!=null){
                                            //调用api 获取对应的省

                                        }
                                        if(jsonObject.get("ArrivalCity")!=null){
                                            //调用api 获取对应的市

                                        }
                                        if(jsonObject.get("ArrivalDistrict")!=null){
                                            //调用api 获取对应的区

                                        }
                                        //总收货量
                                        if(jsonObject.get("OrdersPKGS")!=null){
                                            pGoodsRegionSumEntity.setTotleReceivingGoods(-Double.parseDouble(jsonObject.get("OrdersPKGS")+""));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    return pGoodsRegionSumEntity;
                }
            });

            //落地数据库(收货量--四方云)
            writeSFReceivingPGoodsRegionSum.addSink(new PGoodsRegionSumMySQLSink());
            //落地kafka(四方云-收货量)
            FlinkKafkaProducer mySFReceivingProducer = KafkaSink.flinkKafkaProducer("csbr-SFReceivingPGoodsRegionSum-flink");
            //转换对象为String
            DataStream<String> kafkaSFReceivingResult = writeSFReceivingPGoodsRegionSum.map(new MapFunction<PGoodsRegionSumEntity, String>() {
                @Override
                public String map(PGoodsRegionSumEntity value) throws Exception {
                    String s = JSON.toJSONString(value);
                    return s;
                }
            });
            kafkaSFReceivingResult.addSink(mySFReceivingProducer);


            //打印数据
            writeYaoDeliverPGoodsRegionSum.print().setParallelism(1).name("药链云-发货量-DeliverPGoodsRegionSum");
            writeYaoReceivingPGoodsRegionSum.print().setParallelism(1).name("药链云-收货量-ReceivingPGoodsRegionSum");
            writeSFDeliverPGoodsRegionSum.print().setParallelism(1).name("四方云-发货量-DeliverPGoodsRegionSum");
            writeSFReceivingPGoodsRegionSum.print().setParallelism(1).name("四方云-收货量-ReceivingPGoodsRegionSum");

            //执行数据
            env.execute("csbr_PGoodsRegionSum_job");

        }catch (Exception e){
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
