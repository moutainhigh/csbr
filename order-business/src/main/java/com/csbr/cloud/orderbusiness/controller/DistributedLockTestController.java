package com.csbr.cloud.orderbusiness.controller;

import com.csbr.cloud.orderbusiness.service.DistributionService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangheng
 * @date 2019/11/6 12:02
 */
@RestController
@RequestMapping("/distributedLockTest")
public class DistributedLockTestController {
    private int count = 10;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private DistributionService service;

    @RequestMapping(method = RequestMethod.GET)
    public String distributedLockTest() throws Exception {

        RMap<String, Integer> map = redissonClient.getMap("distributionTest");
        map.put("count", 8);

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(count);

        for (int i = 0; i < count; ++i) { // create and start threads
            new Thread(new Worker(startSignal, doneSignal, service)).start();
        }

        startSignal.countDown(); // let all threads proceed
        doneSignal.await();
        System.out.println("All processors done. Shutdown connection");

        return "finish";
    }
}
