package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
import com.csbr.cloud.flinkserver.model.PDeliverySumEntity;
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
 * @date 2020/1/7 15:14
 */
@Slf4j
public class PDeliverySumMySQLSink extends RichSinkFunction<PDeliverySumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PDeliverySumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery  = "SELECT * FROM p_delivery_sum WHERE id = 1";
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据 更新
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_delivery_sum set totle_delivery += ?,totle_car_sum += ?,chill_car_sum += ?,fenbo_number += ?,dot_totle += ?,totle_car += ?,chill_car += ?,dot_number += ? WHERE id = 1";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(1, value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleCarSum()!=null){
                    preparedStatement.setLong(2, value.getTotleCarSum());
                }else{
                    preparedStatement.setLong(2, 0);
                }
                if(value.getChillCarSum()!=null){
                    preparedStatement.setLong(3, value.getChillCarSum());
                }else{
                    preparedStatement.setLong(3, 0);
                }
                if(value.getFenboNumber()!=null){
                    preparedStatement.setLong(4, value.getFenboNumber());
                }else{
                    preparedStatement.setLong(4, 0);
                }
                if(value.getDotTotle()!=null){
                    preparedStatement.setLong(5,value.getDotTotle());
                }else{
                    preparedStatement.setLong(5,0);
                }
                if(value.getTotleCar()!=null){
                    preparedStatement.setLong(6,value.getTotleCar());
                }else{
                    preparedStatement.setLong(6,0);
                }
                if(value.getChillCar()!=null){
                    preparedStatement.setLong(7,value.getChillCar());
                }else{
                    preparedStatement.setLong(7,0);
                }
                if(value.getDotNumber()!=null){
                    preparedStatement.setLong(8,value.getDotNumber());
                }else{
                    preparedStatement.setLong(8,0);
                }

                log.info("Start update p_delivery_sum");
            }else{
                //无数据 插入
                preparedStatement = null;
                String sqlInsert = "insert into p_delivery_sum (totle_delivery,totle_car_sum,chill_car_sum,fenbo_number,dot_totle,totle_car,chill_car,dot_number) values (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(1, value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleCarSum()!=null){
                    preparedStatement.setLong(2, value.getTotleCarSum());
                }else{
                    preparedStatement.setLong(2, 0);
                }
                if(value.getChillCarSum()!=null){
                    preparedStatement.setLong(3, value.getChillCarSum());
                }else{
                    preparedStatement.setLong(3, 0);
                }
                if(value.getFenboNumber()!=null){
                    preparedStatement.setLong(4, value.getFenboNumber());
                }else{
                    preparedStatement.setLong(4, 0);
                }
                if(value.getDotTotle()!=null){
                    preparedStatement.setLong(5,value.getDotTotle());
                }else{
                    preparedStatement.setLong(5,0);
                }
                if(value.getTotleCar()!=null){
                    preparedStatement.setLong(6,value.getTotleCar());
                }else{
                    preparedStatement.setLong(6,0);
                }
                if(value.getChillCar()!=null){
                    preparedStatement.setLong(7,value.getChillCar());
                }else{
                    preparedStatement.setLong(7,0);
                }
                if(value.getDotNumber()!=null){
                    preparedStatement.setLong(8,value.getDotNumber());
                }else{
                    preparedStatement.setLong(8,0);
                }

                log.info("Start insert p_delivery_sum");
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
