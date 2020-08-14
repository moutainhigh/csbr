package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.CsbrSumEntity;
import com.csbr.cloud.flinkserver.model.CusStorageSumEntity;
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
 * @date 2020/1/8 11:18
 * 用户在线仓库统计
 */
@Slf4j
public class CusStorageSumMySQLSink extends RichSinkFunction<CusStorageSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(CusStorageSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery = "SELECT * FROM cus_storage_sum WHERE guid = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getGuid()!=null){
                preparedStatement.setString(1,value.getGuid());
            }else{
                preparedStatement.setString(1,null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有值做修改
                preparedStatement = null;
                String sqlUpdate = "UPDATE cus_storage_sum set totle_gsp += ?,totle_area += ?,three_gsp_count += ?,three_area += ? WHERE guid = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(1,value.getTotleGSP());
                }else{
                    preparedStatement.setLong(1,0L);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(2, value.getTotleArea());
                }else{
                    preparedStatement.setDouble(2, 0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(3,value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(3,0L);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(4, value.getThreeArea());
                }else{
                    preparedStatement.setDouble(4, 0.0);
                }
                if(value.getGuid()!=null){
                    preparedStatement.setString(5, value.getGuid());
                }else{
                    preparedStatement.setString(5, null);
                }
                log.info("Start update cus_storage_sum");
            }else{
                //无值做增加
                preparedStatement = null;
                String sqlInsert = "INSERT INTO cus_storage_sum (guid,totle_gsp,totle_area,three_gsp_count,three_area) values (?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getGuid()!=null){
                    preparedStatement.setString(1, value.getGuid());
                }else{
                    preparedStatement.setString(1, null);
                }
                if(value.getTotleGSP()!=null){
                    preparedStatement.setLong(2,value.getTotleGSP());
                }else{
                    preparedStatement.setLong(2,0L);
                }
                if(value.getTotleArea()!=null){
                    preparedStatement.setDouble(3, value.getTotleArea());
                }else{
                    preparedStatement.setDouble(3, 0.0);
                }
                if(value.getThreeGSPCount()!=null){
                    preparedStatement.setLong(4,value.getThreeGSPCount());
                }else{
                    preparedStatement.setLong(4,0L);
                }
                if(value.getThreeArea()!=null){
                    preparedStatement.setDouble(5, value.getThreeArea());
                }else{
                    preparedStatement.setDouble(5, 0.0);
                }
                log.info("Start insert cus_storage_sum");
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
