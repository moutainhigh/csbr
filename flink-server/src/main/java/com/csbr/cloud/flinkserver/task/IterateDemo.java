package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author zhangheng
 * @date 2019/12/12 16:20
 */
@Slf4j
public class IterateDemo {

    public static void main(String[] args) throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
        IterativeStream<BinLogMsgEntity> iterativeStream = kafkaData.iterate();
        SingleOutputStreamOperator<BinLogMsgEntity> mockMapDS = iterativeStream
                .map(new MapFunction<BinLogMsgEntity, BinLogMsgEntity>() {
                    @Override
                    public BinLogMsgEntity map(BinLogMsgEntity value) throws Exception {
                        BinLogMsgEntity binLogMsgEntity = new BinLogMsgEntity();
                        binLogMsgEntity.setTs(value.getTs());
                        binLogMsgEntity.setSql("测试sql");
                        return binLogMsgEntity;
                    }
                });
        SingleOutputStreamOperator<BinLogMsgEntity> feedback = mockMapDS.filter(new FilterFunction<BinLogMsgEntity>() {
            @Override
            public boolean filter(BinLogMsgEntity value) throws Exception {
                //过滤id大于50的数据
                return value.getId() > 50;
            }
        });
        iterativeStream.closeWith(feedback);
        SingleOutputStreamOperator<BinLogMsgEntity> resultDS = mockMapDS.filter(new FilterFunction<BinLogMsgEntity>() {
            @Override
            public boolean filter(BinLogMsgEntity value) throws Exception {
                //过滤id小于10的
                return value.getId() < 10;
            }
        });
        resultDS.print().setParallelism(1);

        env.execute("csbr_etl_job");
    }
}
