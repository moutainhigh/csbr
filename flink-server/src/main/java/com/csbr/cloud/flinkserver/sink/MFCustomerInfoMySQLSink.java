package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.MFCustomerInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

import java.sql.*;

/**
 * @author zhangheng
 * @date 2020/1/7 12:09
 */
@Slf4j
public class MFCustomerInfoMySQLSink extends RichSinkFunction<MFCustomerInfoEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public void invoke(MFCustomerInfoEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            String sqlInsert = "insert into MFCustomerInfo (guid,chinese_name,enterprise_type,enterprise_class,med_level,province,city,district,address,contacts,contact_tel,create_time) values (?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sqlInsert);
            if(value.getGuid()!=null){
                preparedStatement.setString(1, value.getGuid());
            }else{
                preparedStatement.setString(1, null);
            }
            if(value.getChineseName()!=null){
                preparedStatement.setString(2, value.getChineseName());
            }else{
                preparedStatement.setString(2, null);
            }
            if(value.getEnterpriseType()!=null){
                preparedStatement.setString(3, value.getEnterpriseType());
            }else{
                preparedStatement.setString(3, null);
            }
            if(value.getEnterpriseClass()!=null){
                preparedStatement.setString(4, value.getEnterpriseClass());
            }else{
                preparedStatement.setString(4, null);
            }
            if(value.getMedLevel()!=null){
                preparedStatement.setString(5, value.getMedLevel());
            }else{
                preparedStatement.setString(5, null);
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
            if(value.getAddress()!=null){
                preparedStatement.setString(9, value.getAddress());
            }else{
                preparedStatement.setString(9, null);
            }
            if(value.getContacts()!=null){
                preparedStatement.setString(10, value.getContacts());
            }else{
                preparedStatement.setString(10, null);
            }
            if(value.getContactTel()!=null){
                preparedStatement.setString(11, value.getContactTel());
            }else{
                preparedStatement.setString(11, null);
            }
            if(value.getCreateTime()!=null){
                Timestamp sqlDate = new Timestamp(value.getCreateTime().getTime());
                preparedStatement.setTimestamp(12, sqlDate);
            }else{
                preparedStatement.setTimestamp(12, null);
            }
            log.info("Start insert mf_customer_info");
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
