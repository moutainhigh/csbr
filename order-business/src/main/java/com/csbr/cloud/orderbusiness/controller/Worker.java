package com.csbr.cloud.orderbusiness.controller;

import com.csbr.cloud.orderbusiness.service.DistributionService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhangheng
 * @date 2019/11/6 13:33
 */
public class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final DistributionService service;
    private RedissonClient redissonClient;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributionService service) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.service = service;
        this.redissonClient = redissonClient;
    }

    @Override
    public void run() {
        try {
            startSignal.await();

            System.out.println(Thread.currentThread().getName() + " start");

//            Integer count = service.aspect(new Person(1, "张三"));
//            Integer count = service.aspect("1");

//            Integer count = service.aspect(() -> {
//                RMap<String, Integer> map = redissonClient.getMap("distributionTest");
//
//                Integer count1 = map.get("count");
//                if (count1 > 0) {
//                    count1 = count1 - 1;
//                    map.put("count", count1);
//                }
//                return count1;
//            });

            System.out.println(Thread.currentThread().getName() + ": count = " + 0);

            doneSignal.countDown();

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
