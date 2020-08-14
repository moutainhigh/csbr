package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.*;

/**
 * @author zhangheng
 * @date 2019/12/19 14:18
 */
@Slf4j
public class CsbrSumMySQLSink extends RichSinkFunction<CsbrSumEntity> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void invoke(CsbrSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
                String sqlInsert = "insert into csbr_sum_info (totleGsp,totleArea,threeArea,shadeArea,chillArea) values (?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getTotleGsp()!=null){
                    preparedStatement.setLong(1, value.getTotleGsp());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(2, value.getTotleArea());
                }else{
                    preparedStatement.setDouble(2, 0.0);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(3, value.getThreeArea());
                }else{
                    preparedStatement.setDouble(3, 0.0);
                }
                if(value.getShadeArea()!=null){
                    preparedStatement.setDouble(4, value.getShadeArea());
                }else{
                    preparedStatement.setDouble(4, 0.0);
                }
                if(value.getChillArea()!=null){
                    preparedStatement.setDouble(5, value.getChillArea());
                }else{
                    preparedStatement.setDouble(5, 0.0);
                }
                log.info("Start insert csbr_sum_info");
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

        if (preparedStatement != null) {
            preparedStatement.close();
        }

        if (connection != null) {
            connection.close();
        }

    }
}
