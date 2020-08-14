package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.PPurchaseGoodsSumEntity;
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
 * @date 2020/1/10 11:45
 * 平台医疗机构采购量汇总
 */
@Slf4j
public class PPurchaseGoodsSumMySQLSink extends RichSinkFunction<PPurchaseGoodsSumEntity> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(PPurchaseGoodsSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询
            String sqlQuery = "SELECT * FROM p_purchase_goods_sum WHERE goods_year = ? AND goods_month = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getYear()!=null){
                preparedStatement.setString(1, value.getYear());
            }else{
                preparedStatement.setString(1, null);
            }
            if(value.getMonth()!=null){
                preparedStatement.setString(2,value.getMonth());
            }else{
                preparedStatement.setString(2,null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据 修改
                preparedStatement = null;
                String sqlUpdate = "UPDATE p_purchase_goods_sum set totle_purchase_amount += ? WHERE goods_year = ? AND goods_month = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotlePurchaseAmount()!=null){
                    preparedStatement.setDouble(1, value.getTotlePurchaseAmount());
                }else{
                    preparedStatement.setDouble(1, 0.0);
                }
                if(value.getYear()!=null){
                    preparedStatement.setString(2, value.getYear());
                }else{
                    preparedStatement.setString(2, null);
                }
                if(value.getMonth()!=null){
                    preparedStatement.setString(3,value.getMonth());
                }else{
                    preparedStatement.setString(3,null);
                }
                log.info("Start update p_purchase_goods_sum");
            }else{
                //无数据 新增
                preparedStatement = null;
                String sqlInsert = "insert into p_purchase_goods_sum (totle_purchase_amount,goods_year,goods_month) values (?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getTotlePurchaseAmount()!=null){
                    preparedStatement.setDouble(1, value.getTotlePurchaseAmount());
                }else{
                    preparedStatement.setDouble(1, 0.0);
                }
                if(value.getYear()!=null){
                    preparedStatement.setString(2, value.getYear());
                }else{
                    preparedStatement.setString(2, null);
                }
                if(value.getMonth()!=null){
                    preparedStatement.setString(3,value.getMonth());
                }else{
                    preparedStatement.setString(3,null);
                }
                log.info("Start insert p_purchase_goods_sum");
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
