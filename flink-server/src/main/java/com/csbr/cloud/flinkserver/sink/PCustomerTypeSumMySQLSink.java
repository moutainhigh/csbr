package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
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
 * @date 2020/1/7 15:08
 */
@Slf4j
public class PCustomerTypeSumMySQLSink extends RichSinkFunction<PCustomerTypeSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PCustomerTypeSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery = "SELECT * FROM p_customer_type_sum WHERE id = 1";
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有值 修改
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_customer_type_sum set totle_pro += ?,totle_delivery += ?,totle_medIns += ? WHERE id =1";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotlePro()!=null){
                    preparedStatement.setLong(1, value.getTotlePro());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(2, value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(2, 0);
                }
                if(value.getTotleMedIns()!=null){
                    preparedStatement.setLong(3, value.getTotleMedIns());
                }else{
                    preparedStatement.setLong(3, 0);
                }

                log.info("Start update p_customer_type_sum");
            }else{
                //无值 更新
                preparedStatement = null;
                String sqlInsert = "insert into p_customer_type_sum (totle_pro,totle_delivery,totle_medIns) values (?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getTotlePro()!=null){
                    preparedStatement.setLong(1, value.getTotlePro());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(2, value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(2, 0);
                }
                if(value.getTotleMedIns()!=null){
                    preparedStatement.setLong(3, value.getTotleMedIns());
                }else{
                    preparedStatement.setLong(3, 0);
                }

                log.info("Start insert p_customer_type_sum");
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

        if (rs != null){
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
