package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.SourceKafka;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author zhangheng
 * @date 2019/12/12 16:28
 */
@Slf4j
public class MapDemo {

    public static void main(String[] args) throws Exception{
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        SingleOutputStreamOperator<BinLogMsgEntity> kafkaData = SourceKafka.getFlinkkafkaSource(env);
        SingleOutputStreamOperator<BinLogMsgEntity> filterDS2 = getAddAgePojo(kafkaData);
        filterDS2.print().setParallelism(1);
        // 执行数据流
        env.execute("csbr_etl_job");
    }

    /**
     * 获取的是整个pojo
     * @param kafkaData
     * @return
     */
    private static SingleOutputStreamOperator<BinLogMsgEntity> getAddAgePojo(SingleOutputStreamOperator<BinLogMsgEntity> kafkaData) {
        //		返回的是MockUpModel pojo类，其中age字段均+5
        return kafkaData.map(new MapFunction<BinLogMsgEntity, BinLogMsgEntity>() {
            @Override
            public BinLogMsgEntity map(BinLogMsgEntity value) throws Exception {
                BinLogMsgEntity binLogMsgEntity = new BinLogMsgEntity();
                binLogMsgEntity.setTs(value.getTs());
                binLogMsgEntity.setSql("测试sql");
                return binLogMsgEntity;

            }
        });
    }

    /**
     * 获取的只是每个元素加过 测试数据的 数据
     * @param kafkaData
     * @return
     */
    private static SingleOutputStreamOperator<String> getAddAgeDS(SingleOutputStreamOperator<BinLogMsgEntity> kafkaData) {
        //		获取的只是message中age字段+5后的数据
        return kafkaData.map(line -> line.getSql().concat("测试数据"));
    }
}
