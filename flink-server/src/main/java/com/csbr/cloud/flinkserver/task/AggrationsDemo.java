package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author zhangheng
 * @date 2019/12/12 16:54
 */
@Slf4j
public class AggrationsDemo {

    public static void main(String[] args)throws Exception{
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
        KeyedStream<BinLogMsgEntity, Long> keyedStream = kafkaData.keyBy(line -> line.getId());
        SingleOutputStreamOperator<BinLogMsgEntity> sum = keyedStream.sum("id");
        sum.print().setParallelism(1);

        SingleOutputStreamOperator<BinLogMsgEntity> max = keyedStream.max("id");
        max.print().setParallelism(1);
        SingleOutputStreamOperator<BinLogMsgEntity> maxBy = keyedStream.maxBy("id");
        maxBy.print().setParallelism(1);
        //max 和 maxBy 之间的区别在于 max 返回流中的最大值，但 maxBy 返回具有最大值的键， min 和 minBy 同理。

        env.execute("csbr_etl_job");
    }
}
