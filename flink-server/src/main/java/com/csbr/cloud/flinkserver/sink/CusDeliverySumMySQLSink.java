package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.model.CusDeliverySumEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author zhangheng
 * @date 2020/1/9 16:09
 */
@Slf4j
public class CusDeliverySumMySQLSink extends RichSinkFunction<CusDeliverySumEntity> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(CusDeliverySumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询 多数据源
            String sqlQuery = "SELECT * FROM cus_delivery_sum WHERE guid = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getGuid()!=null){
                preparedStatement.setString(1,value.getGuid());
            }else{
                preparedStatement.setString(1,null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据更新
                preparedStatement = null;
                String sqlUpdate = "UPDATE cus_delivery_sum set totle_car += ? WHERE guid =?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleCar()!=null){
                    preparedStatement.setLong(1, value.getTotleCar());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getGuid()!=null){
                    preparedStatement.setString(2, value.getGuid());
                }else{
                    preparedStatement.setString(2, null);
                }
                log.info("Start update cus_delivery_sum");
            }else{
                //无数据插入
                preparedStatement = null;
                String sqlInsert = "insert into cus_delivery_sum (guid,totle_car) values (?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getGuid()!=null){
                    preparedStatement.setString(1, value.getGuid());
                }else{
                    preparedStatement.setString(1, null);
                }
                if(value.getTotleCar()!=null){
                    preparedStatement.setLong(2, value.getTotleCar());
                }else{
                    preparedStatement.setLong(2, 0);
                }
                log.info("Start insert cus_delivery_sum");
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open(Configuration parms) throws Exception {
        Class.forName(MysqlConfig.driverClass);
        connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
    }

    public void close() throws Exception {

        if(rs != null){
            rs.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }

    }
}
