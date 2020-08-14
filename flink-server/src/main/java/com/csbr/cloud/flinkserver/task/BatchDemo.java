package com.csbr.cloud.flinkserver.task;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;

/**
 * @author zhangheng
 * @date 2019/12/30 16:20
 * flink批处理
 */
public class BatchDemo {

    public static void main(String[] args) {

        //批处理环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//        env.readCsvFile();
        //读取数据
        DataSet<String> stringDataSource = env.readTextFile("");
        //处理数据
        stringDataSource.map(new MapFunction<String, Object>() {


            @Override
            public Object map(String s) throws Exception {
                return null;
            }
        });

        //保存数据


    }
}
