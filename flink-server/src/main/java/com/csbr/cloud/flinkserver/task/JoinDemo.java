package com.csbr.cloud.flinkserver.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author zhangheng
 * @date 2019/12/11 10:50
 */
@Slf4j
public class JoinDemo {

//    @Autowired
//    private Properties properties;

    private static final OutputTag<BinLogMsgEntity> outOrder = new OutputTag<BinLogMsgEntity>("out_order") {};
    private static final OutputTag<BinLogMsgEntity> outOrderDetail =
            new OutputTag<BinLogMsgEntity>("out_order_detail") {};

    public static void main(String[] args){
        try {
            // 1.加载数据源参数
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            Properties consumerProps = new Properties();
            consumerProps.setProperty("bootstrap.servers", "kafka1.com:9092,kafka1.com:9093,kafka1.com:9094");
//          consumerProps.setProperty("group.id", flinkProperties().getGroupId());
            consumerProps.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            consumerProps.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            FlinkKafkaConsumer myConsumer = new FlinkKafkaConsumer("canal", new SimpleStringSchema(),
                    consumerProps);
            //设置只读取最新数据
            myConsumer.setStartFromLatest();
            // 2.初始化数据源,对数据源进行映射，过滤，根据表名分成多个侧数据流
            SingleOutputStreamOperator keyedStream = env.addSource(myConsumer)
                    .map(new MapFunction<String, BinLogMsgEntity>() {
                        @Override
                        public BinLogMsgEntity map(String value) throws Exception {
                            BinLogMsgEntity msgEntity = JSON.parseObject(value,
                                    new TypeReference<BinLogMsgEntity>() {});
                            return msgEntity;
                        }
                    }).process(new ProcessFunction<BinLogMsgEntity, BinLogMsgEntity>() {

                @Override
                public void processElement(BinLogMsgEntity value,
                                           Context ctx, Collector<BinLogMsgEntity> out)
                        throws Exception {
                    // 数据发送到常规输出中
                    out.collect(value);

                    // 根据表名，发送到侧输出中
                    if (value.getTable().equals("out_order")
                            && value.getType().equals("INSERT")) {
                        ctx.output(outOrder, value);
                    } else if (value.getTable().equals("out_order_details")
                            && value.getType().equals("INSERT")) {
                        ctx.output(outOrderDetail, value);
                    }
                }
            });

            DataStream<BinLogMsgEntity> outOrderStream = keyedStream.getSideOutput(outOrder);
            DataStream<BinLogMsgEntity> outOrderDetailStream = keyedStream.getSideOutput(outOrderDetail);
            // 4.对侧数据流进行打印
            // keyedStream.getSideOutput(outOrder).join(keyedStream.getSideOutput(outOrderDetail))

            // 4.对侧数据流进行打印
            // keyedStream.getSideOutput(outOrderDetail).print();

            outOrderStream.join(outOrderDetailStream).where(new KeySelector<BinLogMsgEntity, String>() {

                @Override
                public String getKey(BinLogMsgEntity value) throws Exception {
                    // 获取out_order_code
                    String outOrderCode = value.getData().get(0).getString("out_order_code");
                    System.out.println("order:"+outOrderCode );
                    return outOrderCode;
                }
            }).equalTo(new KeySelector<BinLogMsgEntity, String>() {

                @Override
                public String getKey(BinLogMsgEntity value) throws Exception {
                    // 获取out_order_code
                    String outOrderCode = value.getData().get(0).getString("out_order_code");
                    System.out.println("detail:"+outOrderCode );
                    return outOrderCode;
                }
            }).window(ProcessingTimeSessionWindows.withGap(Time.seconds(60)))
                    .apply(new JoinFunction<BinLogMsgEntity, BinLogMsgEntity, List<String>>() {

                        @Override
                        public List<String> join(BinLogMsgEntity first, BinLogMsgEntity second) throws Exception {
                            List<String> result = new ArrayList<>();
                            for(JSONObject orderJO : first.getData()){
                                String outOrderCode = orderJO.getString("out_order_code");
                                Integer orderType = orderJO.getInteger("order_type");
                                Long outOrderId = orderJO.getLong("id");
                                long inputTS = orderJO.getLong("input_date");
                                int hour = (int) (inputTS/1000/ (60 * 60));

                                List<JSONObject> detailList = second.getData().stream().filter(d ->d.getString("out_order_code").equals(outOrderCode)).collect(Collectors.toList());
                                int detailNum = detailList.size();
                                int skuPieceNum = detailList.stream().mapToInt(oj -> oj.getInteger("amount")).sum();
                                String sql = new StringBuffer("insert into dws_order_input_h (out_order_id,hour, order_type, detail_num,sku_piece_num) values (")
                                        .append(outOrderId.toString()).append(",")
                                        .append(hour).append(",")
                                        .append(orderType).append(",")
                                        .append(detailNum).append(",")
                                        .append(skuPieceNum).append(")").toString();

                                result.add(sql);
                            }
                            return result;
                        }})
                    .process(new ProcessFunction<List<String>, String>() {

                        @Override
                        public void processElement(List<String> in, Context arg1,
                                                   Collector<String> out) throws Exception {
                            in.forEach(s ->{
                                out.collect(s);
                            });
                        }
                    })
                    .print();
            // 5.对主数据流进行打印
            // keyedStream.print();

            env.execute("csbr_etl_job");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
