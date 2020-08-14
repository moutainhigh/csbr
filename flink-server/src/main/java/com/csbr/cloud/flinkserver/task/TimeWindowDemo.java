package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import scala.Tuple2;

/**
 * @author zhangheng
 * @date 2019/12/12 16:46
 */
public class TimeWindowDemo {

    public static void main(String[] args)throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkKafkaSourceV2(env);

        kafkaData.keyBy(line -> line.getTable()).timeWindow(Time.seconds(20)).sum("table")
                .map(new MapFunction<BinLogMsgEntity, Tuple2<String, Long>>() {
                    @Override
                    public Tuple2<String, Long> map(BinLogMsgEntity value) throws Exception {
                        String table = value.getTable();
                        Long id = value.getId();
                        return new Tuple2<>(table, id);
                    }
                }).print().setParallelism(1);
        env.execute("csbr_etl_job");
    }
}
