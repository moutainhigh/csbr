package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author zhangheng
 * @date 2019/12/12 16:14
 */
@Slf4j
public class FliterDemo {

    public static void main(String[] args)throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
        SingleOutputStreamOperator<BinLogMsgEntity> filterDS2 = getFilterDS2(kafkaData);
        filterDS2.print().setParallelism(1);
        // 执行数据流
        env.execute("csbr_etl_job");
    }


    /**
     * 过滤数据表为ms_base_data的数据
     * @param kafkaData
     * @return
     */
    private static SingleOutputStreamOperator<BinLogMsgEntity> getFilterDS2(SingleOutputStreamOperator<BinLogMsgEntity> kafkaData) {
        return kafkaData.filter(new FilterFunction<BinLogMsgEntity>() {
            @Override
            public boolean filter(BinLogMsgEntity value) throws Exception {
                if (value.getTable().equals("ms_base_data")) {
                    return true;
                }
                return false;
            }
        });
    }
}
