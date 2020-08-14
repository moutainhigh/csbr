package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PDeliverySumEntity;
import com.csbr.cloud.flinkserver.model.PMasterRelationEntity;
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
 * @date 2020/1/10 15:38
 * 医疗机构供应商关系
 */
@Slf4j
public class PMasterRelationMySQLSink extends RichSinkFunction<PMasterRelationEntity> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PMasterRelationEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery  = "SELECT * FROM p_master_relation WHERE guid = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getGuid()!=null){
                preparedStatement.setString(1,value.getGuid());
            }else{
                preparedStatement.setString(1,null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据 更新
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_master_relation set enterprise_guid = ?,relation_ent_guid = ?,relation_type = ?,relation_create_time = ?,state = ? WHERE guid = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getEnterpriseGUID()!=null){
                    preparedStatement.setString(1, value.getEnterpriseGUID());
                }else{
                    preparedStatement.setString(1, null);
                }
                if(value.getRelationEntGUID()!=null){
                    preparedStatement.setString(2, value.getRelationEntGUID());
                }else{
                    preparedStatement.setString(2, null);
                }
                if(value.getRelationType()!=null){
                    preparedStatement.setString(3, value.getRelationType());
                }else{
                    preparedStatement.setString(3, null);
                }
                if(value.getRelationCreateTime()!=null){
                    preparedStatement.setString(4, value.getRelationCreateTime());
                }else{
                    preparedStatement.setString(4, null);
                }
                if(value.getState()!=null){
                    preparedStatement.setString(5,value.getState());
                }else{
                    preparedStatement.setString(5,null);
                }
                if(value.getGuid()!=null){
                    preparedStatement.setString(6,value.getGuid());
                }else{
                    preparedStatement.setString(6,null);
                }

                log.info("Start update p_master_relation");
            }else{
                //无数据 插入
                preparedStatement = null;
                String sqlInsert = "insert into p_master_relation (guid,enterprise_guid,relation_ent_guid,relation_type,relation_create_time,state) values (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getGuid()!=null){
                    preparedStatement.setString(1, value.getGuid());
                }else{
                    preparedStatement.setString(1, null);
                }
                if(value.getEnterpriseGUID()!=null){
                    preparedStatement.setString(2, value.getEnterpriseGUID());
                }else{
                    preparedStatement.setString(2, null);
                }
                if(value.getRelationEntGUID()!=null){
                    preparedStatement.setString(3, value.getRelationEntGUID());
                }else{
                    preparedStatement.setString(3, null);
                }
                if(value.getRelationType()!=null){
                    preparedStatement.setString(4, value.getRelationType());
                }else{
                    preparedStatement.setString(4, null);
                }
                if(value.getRelationCreateTime()!=null){
                    preparedStatement.setString(5,value.getRelationCreateTime());
                }else{
                    preparedStatement.setString(5,null);
                }
                if(value.getState()!=null){
                    preparedStatement.setString(6,value.getState());
                }else{
                    preparedStatement.setString(6,null);
                }

                log.info("Start insert p_master_relation");
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
