package com.csbr.cloud.flinkserver.task;

import com.csbr.cloud.flinkserver.config.KafkaOutputFormat;
import com.csbr.cloud.flinkserver.model.BusinessOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCOutputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.types.Row;

/**
 * @author zhangheng
 * @date 2019/12/13 14:26
 */
@Slf4j
public class ReadAllByMysqlDemo {

    private static String driverClass = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://49.4.23.228:3306/ms_order_center?useUnicode=true&characterEncoding=UTF-8&useSSL=false&autoReconnect=true&serverTimezone=Asia/Shanghai";
    private static String userNmae = "root";
    private static String passWord = "root";

    public static void main(String[] arg) throws Exception {

        //BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.LONG_TYPE_INFO
        TypeInformation[] fieldTypes = new TypeInformation[] {
                BasicTypeInfo.STRING_TYPE_INFO,
                BasicTypeInfo.STRING_TYPE_INFO
//                BasicTypeInfo.STRING_TYPE_INFO,
//                BasicTypeInfo.STRING_TYPE_INFO,
//                BasicTypeInfo.STRING_TYPE_INFO,
//                BasicTypeInfo.DATE_TYPE_INFO,
//                BasicTypeInfo.DATE_TYPE_INFO,
//                BasicTypeInfo.STRING_TYPE_INFO,
//                BasicTypeInfo.BIG_DEC_TYPE_INFO,
//                BasicTypeInfo.BIG_DEC_TYPE_INFO,
//                BasicTypeInfo.DATE_TYPE_INFO,
//                BasicTypeInfo.INT_TYPE_INFO
        };
        String[] fieldNames = new String[] { "cargo_owner_name", "b_bill_no" };

        RowTypeInfo rowTypeInfo = new RowTypeInfo(fieldTypes, fieldNames);
//        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat().setDrivername(driverClass)
//                .setDBUrl(dbUrl)
//                .setUsername(userNmae).setPassword(passWord)
//                .setQuery("select LOGIC_CODE, SHARE_LOG_CODE from table").setRowTypeInfo(rowTypeInfo).finish();

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        //读mysql
        DataSource<Row> dataSource = env.createInput(JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername(driverClass)
                .setDBUrl(dbUrl)
                .setUsername(userNmae)
                .setPassword(passWord)
                .setQuery("select cargo_owner_name,b_bill_no from business_order")
                .setRowTypeInfo(rowTypeInfo)
                .finish());
//        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);
//        tableEnv.registerDataSet("business_order", dataSource);
//
//        Table tapiResult = tableEnv.scan("business_order");
//        System.out.println("数据表是:");
//        tapiResult.printSchema();
//        Table query = tableEnv.sqlQuery("select cargo_owner_name,b_bill_no from business_order");
//        DataSet<BusinessOrder> ds=  tableEnv.toDataSet(query, BusinessOrder.class);
        //获取数据并打印
        dataSource.map(new MapFunction<Row, String>() {
            @Override
            public String map(Row value) throws Exception {
                System.out.println(value);
                return value.toString();
            }
        }).print();

//        DataSet<String> temp=ds.map(new MapFunction<BusinessOrder, String>() {
//            @Override
//            public String map(BusinessOrder result) throws Exception {
//                String cargoOwnerName = result.getCargo_owner_name();
//                String bBillNo = result.getB_bill_no();
//                return cargoOwnerName+":->:"+bBillNo;
//            }
//        });

        log.info("read db end");

//        KafkaOutputFormat kafkaOutput = KafkaOutputFormat.buildKafkaOutputFormat()
//                .setBootstrapServers("ip:9092").setTopic("search_test_whk").setAcks("all").setBatchSize("1000")
//                .setBufferMemory("100000").setLingerMs("1").setRetries("2").finish();
//
//        temp.output(kafkaOutput);

        //写入数据到kafka
//        temp.output(
//                KafkaOutputFormat.buildKafkaOutputFormat()
//                        .setBootstrapServers("kafka1.com:9092,kafka1.com:9093,kafka1.com:9094")
//                        .setTopic("write_mysql_kafka")
//                        .setAcks("all")
//                        .setBatchSize("1000")
//                        .setBufferMemory("100000")
//                        .setLingerMs("1")
//                        .setRetries("2")
//                        .finish()
//        );

//        //写入数据到mysql
//        String sql = "insert into test.temp (name,time,type) values (?,?,?)";
//        //将Row对象写到mysql
//        dataSource.output(JDBCOutputFormat.buildJDBCOutputFormat()
//                .setDrivername(driverClass)
//                .setDBUrl(dbUrl)
//                .setUsername(userNmae)
//                .setPassword(passWord)
//                .setQuery(sql)
//                .finish());
//
//        log.info("write kafka end");

//        env.execute();

    }
}
