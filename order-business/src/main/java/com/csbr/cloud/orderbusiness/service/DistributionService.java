package com.csbr.cloud.orderbusiness.service;

import com.csbr.cloud.distributelock.annotation.DistributedLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rx.functions.Action;

/**
 * @author zhangheng
 * @date 2019/11/6 12:04
 */
@Service
public class DistributionService {

    @Autowired
    private RedissonClient redissonClient;

    @DistributedLock(param = "id", lockNamePost = ".lock")
    public Integer aspect() {
        RMap<String, Integer> map = redissonClient.getMap("distributionTest");

        Integer count = map.get("count");

        if (count > 0) {
            count = count - 1;
            map.put("count", count);
        }

        return count;
    }

    @DistributedLock(argNum = 1, lockNamePost = ".lock")
    public Integer aspect(String i) {
        RMap<String, Integer> map = redissonClient.getMap("distributionTest");

        Integer count = map.get("count");

        if (count > 0) {
            count = count - 1;
            map.put("count", count);
        }

        return count;
    }

}
