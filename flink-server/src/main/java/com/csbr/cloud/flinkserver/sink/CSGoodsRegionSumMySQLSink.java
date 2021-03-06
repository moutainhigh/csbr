package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.CSGoodsRegionSumEntity;
import com.csbr.cloud.flinkserver.model.CusDeliverySumEntity;
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
 * @date 2020/1/10 14:26
 * 传世货量区域分布
 */
@Slf4j
public class CSGoodsRegionSumMySQLSink extends RichSinkFunction<CSGoodsRegionSumEntity> {

    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    public void invoke(CSGoodsRegionSumEntity value, SinkFunction.Context context) {

        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            //先查询 多数据源
            String sqlQuery = "SELECT * FROM cs_goods_region_sum WHERE province = ? AND city = ? AND district = ? AND goods_year = ? AND goods_month = ?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            if(value.getProvince()!=null){
                preparedStatement.setString(1,value.getProvince());
            }else{
                preparedStatement.setString(1,null);
            }
            if(value.getCity()!=null){
                preparedStatement.setString(2,value.getCity());
            }else{
                preparedStatement.setString(2,null);
            }
            if(value.getDistrict()!=null){
                preparedStatement.setString(3,value.getDistrict());
            }else{
                preparedStatement.setString(3,null);
            }
            if(value.getYear()!=null){
                preparedStatement.setString(4,value.getYear());
            }else{
                preparedStatement.setString(4,null);
            }
            if(value.getMonth()!=null){
                preparedStatement.setString(5,value.getMonth());
            }else{
                preparedStatement.setString(5,null);
            }
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                //有数据更新
                preparedStatement = null;
                String sqlUpdate = "UPDATE cs_goods_region_sum set totle_deliver_goods += ?,medicine_deliver_goods += ? WHERE province = ? AND city = ? AND district = ? AND goods_year = ? AND goods_month = ?";
                preparedStatement = connection.prepareStatement(sqlUpdate);
                if(value.getTotleDeliverGoods()!=null){
                    preparedStatement.setDouble(1, value.getTotleDeliverGoods());
                }else{
                    preparedStatement.setDouble(1, 0.0);
                }
                if(value.getMedicineDeliverGoods()!=null){
                    preparedStatement.setDouble(2, value.getMedicineDeliverGoods());
                }else{
                    preparedStatement.setDouble(2, 0.0);
                }
                if(value.getProvince()!=null){
                    preparedStatement.setString(3, value.getProvince());
                }else{
                    preparedStatement.setString(3, null);
                }
                if(value.getCity()!=null){
                    preparedStatement.setString(4,value.getCity());
                }else{
                    preparedStatement.setString(4,null);
                }
                if(value.getDistrict()!=null){
                    preparedStatement.setString(5,value.getDistrict());
                }else{
                    preparedStatement.setString(5,null);
                }
                if(value.getYear()!=null){
                    preparedStatement.setString(6,value.getYear());
                }else{
                    preparedStatement.setString(6,null);
                }
                if(value.getMonth()!=null){
                    preparedStatement.setString(7,value.getMonth());
                }else{
                    preparedStatement.setString(7,null);
                }
                log.info("Start update cs_goods_region_sum");
            }else{
                //无数据插入
                preparedStatement = null;
                String sqlInsert = "insert into cs_goods_region_sum (province,city,district,totle_deliver_goods,medicine_deliver_goods,goods_year,goods_month) values (?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                if(value.getProvince()!=null){
                    preparedStatement.setString(1,value.getProvince());
                }else{
                    preparedStatement.setString(1,null);
                }
                if(value.getCity()!=null){
                    preparedStatement.setString(2,value.getCity());
                }else{
                    preparedStatement.setString(2,null);
                }
                if(value.getDistrict()!=null){
                    preparedStatement.setString(3,value.getDistrict());
                }else{
                    preparedStatement.setString(3,null);
                }
                if(value.getTotleDeliverGoods()!=null){
                    preparedStatement.setDouble(4,value.getTotleDeliverGoods());
                }else{
                    preparedStatement.setDouble(4,0.0);
                }
                if(value.getMedicineDeliverGoods()!=null){
                    preparedStatement.setDouble(5,value.getMedicineDeliverGoods());
                }else{
                    preparedStatement.setDouble(5,0.0);
                }
                if(value.getYear()!=null){
                    preparedStatement.setString(6,value.getYear());
                }else{
                    preparedStatement.setString(6,null);
                }
                if(value.getMonth()!=null){
                    preparedStatement.setString(7,value.getMonth());
                }else{
                    preparedStatement.setString(7,null);
                }
                log.info("Start insert cs_goods_region_sum");
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
