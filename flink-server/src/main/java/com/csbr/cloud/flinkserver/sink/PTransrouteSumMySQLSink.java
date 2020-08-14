package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
import com.csbr.cloud.flinkserver.model.PTransrouteSumEntity;
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
 * @date 2020/1/10 10:15
 * 平台配送路线
 */
@Slf4j
public class PTransrouteSumMySQLSink extends RichSinkFunction<PTransrouteSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PTransrouteSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery = "SELECT * FROM p_transroute_sum WHERE province = ? AND city = ?";
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
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有值 更新
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_transroute_sum set province = ?,city = ? WHERE province = ? AND city = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
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
                if(value.getProvince()!=null){
                    preparedStatement.setString(3, value.getProvince());
                }else{
                    preparedStatement.setString(3, null);
                }
                if(value.getCity()!=null){
                    preparedStatement.setString(4, value.getCity());
                }else{
                    preparedStatement.setString(4, null);
                }

                log.info("Start update p_transroute_sum");
            }else{
                //无值 插入
                preparedStatement = null;
                String sqlInsert = "insert into p_transroute_sum (province,city) values (?,?)";
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

                log.info("Start insert p_transroute_sum");
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
