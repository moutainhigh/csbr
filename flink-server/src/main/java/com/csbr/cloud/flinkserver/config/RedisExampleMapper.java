package com.csbr.cloud.flinkserver.config;

import com.csbr.cloud.flinkserver.model.BinLogMsgEntity;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * @author zhangheng
 * @date 2019/12/17 13:50
 * redis模板
 */
public class RedisExampleMapper implements RedisMapper<BinLogMsgEntity> {
    //设置数据使用的数据结构 HashSet 并设置key的名称
    public RedisCommandDescription getCommandDescription() {
//        return new RedisCommandDescription(RedisCommand.HSET, "flink");
        return new RedisCommandDescription(RedisCommand.SET, null);
    }
    /**
     * 获取 value值 value的数据是键值对
     * @param
     * @return
     */
    //指定key
    public String getKeyFromData(BinLogMsgEntity binLogMsgEntity) {
        return binLogMsgEntity.toString();
    }
    //指定value
    public String getValueFromData(BinLogMsgEntity binLogMsgEntity) {
        return binLogMsgEntity.toString();
    }

}
