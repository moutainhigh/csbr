package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PDeliverySumEntity;
import com.csbr.cloud.flinkserver.model.PStorageDeliveryRegionSumEntity;
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
 * @date 2020/1/9 10:19
 */
@Slf4j
public class PStorageDeliveryRegionSumMySQLSink extends RichSinkFunction<PStorageDeliveryRegionSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PStorageDeliveryRegionSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询 始终保持数据库一条数据多方源累加
            String sqlQuery = "SELECT * FROM p_storage_delivery_region_sum WHERE province = ? AND city = ? AND district = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getProvince()!=null){
                preparedStatement.setString(1, value.getProvince());
            }else{
                preparedStatement.setString(1, null);
            }
            if(value.getCity()!=null){
                preparedStatement.setString(2, value.getCity());
            }else{
                preparedStatement.setString(2, null);
            }
            if(value.getDistrict()!=null){
                preparedStatement.setString(3, value.getDistrict());
            }else{
                preparedStatement.setString(3, null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据执行修改
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_storage_delivery_region_sum set totle_gsp += ?,totle_area += ?,three_gsp_count += ?,three_area += ?,totle_delivery += ? WHERE province = ? AND city = ? AND district = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(1, value.getTotleGSP());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(2,value.getTotleArea());
                }else{
                    preparedStatement.setDouble(2,0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(3,value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(3,0);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(4,value.getThreeArea());
                }else{
                    preparedStatement.setDouble(4,0.0);
                }
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(5,value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(5,0);
                }
                if(value.getProvince()!=null){
                    preparedStatement.setString(6, value.getProvince());
                }else{
                    preparedStatement.setString(6, null);
                }
                if(value.getCity()!=null){
                    preparedStatement.setString(7, value.getCity());
                }else{
                    preparedStatement.setString(7, null);
                }
                if(value.getDistrict()!=null){
                    preparedStatement.setString(8, value.getDistrict());
                }else{
                    preparedStatement.setString(8, null);
                }
                log.info("Start update p_storage_delivery_region_sum");
            }else{
                //没有数据执行增加
                preparedStatement = null;
                String sqlInsert = "insert into p_storage_delivery_region_sum (province,city,district,totle_gsp,totle_area,three_gsp_count,three_area,totle_delivery) values (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getProvince()!=null){
                    preparedStatement.setString(1, value.getProvince());
                }else{
                    preparedStatement.setString(1, null);
                }
                if(value.getCity()!=null){
                    preparedStatement.setString(2, value.getCity());
                }else{
                    preparedStatement.setString(2, null);
                }
                if(value.getDistrict()!=null){
                    preparedStatement.setString(3, value.getDistrict());
                }else{
                    preparedStatement.setString(3, null);
                }
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(4, value.getTotleGSP());
                }else{
                    preparedStatement.setLong(4, 0);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(5,value.getTotleArea());
                }else{
                    preparedStatement.setDouble(5,0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(6,value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(6,0);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(7,value.getThreeArea());
                }else{
                    preparedStatement.setDouble(7,0.0);
                }
                if(value.getTotleDelivery()!=null){
                    preparedStatement.setLong(8,value.getTotleDelivery());
                }else{
                    preparedStatement.setLong(8,0);
                }
                log.info("Start insert p_storage_delivery_region_sum");
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
