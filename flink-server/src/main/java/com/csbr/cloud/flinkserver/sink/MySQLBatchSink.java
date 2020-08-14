package com.csbr.cloud.flinkserver.sink;

import com.csbr.cloud.flinkserver.config.MysqlConfig;
import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @author zhangheng
 * @date 2019/12/17 9:38
 * 批量插入数据库
 */
@Slf4j
public class MySQLBatchSink extends RichSinkFunction<List<BinLogMsgEntity>> {
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private PreparedStatement preparedStatement;

    /**
     * open() 方法中建立连接，这样不用每次 invoke 的时候都要建立连接和释放连接
     *
     * @param parameters
     * @throws Exception
     */
    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        try {
            if (connection == null) {
                Class.forName(MysqlConfig.driverClass);
                connection = DriverManager.getConnection(MysqlConfig.dbUrl, MysqlConfig.userNmae, MysqlConfig.passWord);
            }
            String sql = "insert into Student(id, name, password, age) values(?, ?, ?, ?);";
            preparedStatement = this.connection.prepareStatement(sql);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        //关闭连接和释放资源
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
      /**
      * 每条数据的插入都要调用一次 invoke() 方法
      *
      * @param value
      * @param context
      * @throws Exception
      */
    @Override
    public void invoke(List<BinLogMsgEntity> value, Context context) throws Exception {
        //遍历数据集合
        for (BinLogMsgEntity student : value) {
//            preparedStatement.setInt(1, student.getId());
//            preparedStatement.setString(2, student.getName());
//            preparedStatement.setString(3, student.getPassword());
//            preparedStatement.setInt(4, student.getAge());
            preparedStatement.addBatch();
        }
        int[] count = preparedStatement.executeBatch();//批量后执行
        log.info("成功了插入了" + count.length + "行数据");
    }

}
