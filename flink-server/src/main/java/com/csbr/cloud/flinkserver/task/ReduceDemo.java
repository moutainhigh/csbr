package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author zhangheng
 * @date 2019/12/11 10:02
 * FLINK的reduce算子
 */
@Slf4j
public class ReduceDemo {


    public static void main(String[] args){
        try {
            // 1.初始化数据源
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
//        myConsumer.assignTimestampsAndWatermarks(new CustomWatermarkEmitter());

            kafkaData.filter(new FilterFunction<BinLogMsgEntity>() {

                        @Override
                        public boolean filter(BinLogMsgEntity value) throws Exception {
                            if(null == value.getData() ) {
                                return false;
                            }else {
                                return true;
                            }
                        }})
                    .keyBy("table").timeWindow(Time.seconds(5))
                    .reduce(new ReduceFunction<BinLogMsgEntity>() {

                        @Override
                        public BinLogMsgEntity reduce(BinLogMsgEntity value1, BinLogMsgEntity value2) throws Exception {
                            log.info("-------------------redeceFunction:tablie1:{"+value1.getTable()+"},tablie2:{"+value2.getTable()+"}。"
                                    + "size1:{"+value1.getData().size()+"},size2:{"+value2.getData().size()+"}。"
                                    + "type1:{"+value1.getType()+"},type2:{"+value2.getType()+"}----------------------");
                            return value1.getData().size()>value2.getData().size() ? value1 : value2 ;
                        }});

            kafkaData.print();

            // 执行数据流
            env.execute("csbr_etl_job");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error:" + e.getMessage());
        }
    }
}
