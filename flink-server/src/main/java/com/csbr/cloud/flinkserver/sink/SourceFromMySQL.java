package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.BusinessOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author zhangheng
 * @date 2019/12/17 15:40
 * 从mysql中读取数据
 */
@Slf4j
public class SourceFromMySQL extends RichSourceFunction<BusinessOrder> {

    PreparedStatement ps;
    private Connection connection;

    /**
     * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接。
     *
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        connection = getConnection();
        String sql = "select cargo_owner_name,b_bill_no from business_order;";
        ps = this.connection.prepareStatement(sql);
    }

    /**
     * 程序执行完毕就可以进行，关闭连接和释放资源的动作了
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        super.close();
        if (connection != null) { //关闭连接和释放资源
            connection.close();
        }
        if (ps != null) {
            ps.close();
        }
    }

    /**
     * DataStream 调用一次 run() 方法用来获取数据
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void run(SourceContext<BusinessOrder> ctx) throws Exception {
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            BusinessOrder businessOrder = new BusinessOrder(
                    resultSet.getString("cargo_owner_name").trim(),
                    resultSet.getString("b_bill_no").trim()
            );
            ctx.collect(businessOrder);
        }
    }

    @Override
    public void cancel() {
    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(MysqlConfig.driverClass);
            connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
        } catch (Exception e) {
            log.info("-----------mysql get connection has exception , msg = "+ e.getMessage());
        }
        return connection;
    }

}
