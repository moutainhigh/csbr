package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PStorageDeliveryRegionSumEntity;
import com.csbr.cloud.flinkserver.model.PStorageSumEntity;
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
 * @date 2020/1/9 13:37
 */
@Slf4j
public class PStorageSumMySQLSink extends RichSinkFunction<PStorageSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PStorageSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询 始终保持数据库一条数据多方源累加
            String sqlQuery = "SELECT * FROM p_storage_sum WHERE id = 1";
            preparedStatement = connection.prepareStatement(sqlQuery);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据执行修改
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_storage_sum set totle_gsp += ?,totle_area += ?,shade_area += ?,three_gsp_count += ?,three_area += ?,chill_area += ?,pro_count += ?,city_count += ? WHERE id = 1";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(1, value.getTotleGSP());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(2, value.getTotleArea());
                }else{
                    preparedStatement.setDouble(2, 0.0);
                }
                if(value.getShadeArea()!=null){
                    preparedStatement.setDouble(3, value.getShadeArea());
                }else{
                    preparedStatement.setDouble(3, 0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(4, value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(4, 0);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(5,value.getThreeArea());
                }else{
                    preparedStatement.setDouble(5,0.0);
                }
                if(value.getChillArea()!=null){
                    preparedStatement.setDouble(6,value.getChillArea());
                }else{
                    preparedStatement.setDouble(6,0.0);
                }
                if(value.getProCount()!=null){
                    preparedStatement.setLong(7,value.getProCount());
                }else{
                    preparedStatement.setLong(7,0);
                }
                if(value.getCityCount()!=null){
                    preparedStatement.setLong(8,value.getCityCount());
                }else{
                    preparedStatement.setLong(8,0);
                }
                log.info("Start update p_storage_sum");
            }else{
                //没有数据执行增加
                preparedStatement = null;
                String sqlInsert = "insert into p_storage_sum (totle_gsp,totle_area,shade_area,three_gsp_count,three_area,chill_area,pro_count,city_count) values (?,?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(1, value.getTotleGSP());
                }else{
                    preparedStatement.setLong(1, 0);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(2, value.getTotleArea());
                }else{
                    preparedStatement.setDouble(2, 0.0);
                }
                if(value.getShadeArea()!=null){
                    preparedStatement.setDouble(3, value.getShadeArea());
                }else{
                    preparedStatement.setDouble(3, 0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(4, value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(4, 0);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(5,value.getThreeArea());
                }else{
                    preparedStatement.setDouble(5,0.0);
                }
                if(value.getChillArea()!=null){
                    preparedStatement.setDouble(6,value.getChillArea());
                }else{
                    preparedStatement.setDouble(6,0.0);
                }
                if(value.getProCount()!=null){
                    preparedStatement.setLong(7,value.getProCount());
                }else{
                    preparedStatement.setLong(7,0);
                }
                if(value.getCityCount()!=null){
                    preparedStatement.setLong(8,value.getCityCount());
                }else{
                    preparedStatement.setLong(8,0);
                }
                log.info("Start insert p_storage_sum");
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
