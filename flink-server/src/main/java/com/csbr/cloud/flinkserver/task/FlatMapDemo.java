package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author zhangheng
 * @date 2019/12/12 16:03
 */
@Slf4j
public class FlatMapDemo {

    public static void main(String[] args){
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
        kafkaData.flatMap(new FlatMapFunction<BinLogMsgEntity, BinLogMsgEntity>() {
            @Override
            public void flatMap(BinLogMsgEntity value, Collector<BinLogMsgEntity> out) throws Exception {
                if (StringUtils.isNotEmpty(value.getDatabase())) {
                    out.collect(value);
                }
            }
        }).print().setParallelism(1);

    }
}
