package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.CusRelationEntity;
import com.csbr.cloud.flinkserver.model.PCustomerTypeSumEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author zhangheng
 * @date 2020/1/8 16:57
 */
@Slf4j
public class CusRelationMySQLSink extends RichSinkFunction<CusRelationEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void invoke(CusRelationEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            String sqlInsert = "insert into cus_relation (guid,source_guid,source_data) values (?,?,?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            if(value.getGuid()!=null){
                preparedStatement.setString(1, value.getGuid());
            }else{
                preparedStatement.setString(1, null);
            }
            if(value.getSourceGuid()!=null){
                preparedStatement.setString(2, value.getSourceGuid());
            }else{
                preparedStatement.setString(2, null);
            }
            if(value.getSourceData()!=null){
                preparedStatement.setString(3, value.getSourceData());
            }else{
                preparedStatement.setString(3, null);
            }

            log.info("Start insert cus_relation");
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
