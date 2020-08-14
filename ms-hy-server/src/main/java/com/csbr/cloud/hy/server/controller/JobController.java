package com.csbr.cloud.hy.server.controller;

import com.csbr.cloud.hy.server.service.InvLotStockSapHistoryService;
import com.csbr.cloud.hy.server.service.InvLotStockWmsHistoryService;
import com.csbr.cloud.hy.server.service.TrDailyOrderTimeLimitService;
import com.csbr.cloud.hy.server.service.TrReplenishmentBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangheng
 * @date 2020/6/1 11:18
 * 调度任务
 */
@RestController
@RequestMapping(value = "job")
@EnableScheduling
public class JobController {


    @Autowired
    private TrDailyOrderTimeLimitService trDailyOrderTimeLimitService;

    @Autowired
    private InvLotStockWmsHistoryService invLotStockWmsHistoryService;

    @Autowired
    private InvLotStockSapHistoryService invLotStockSapHistoryService;

    @Autowired
    private TrReplenishmentBillService trReplenishmentBillService;


    /**
     * 每日订单时效统计
     * 每天凌晨执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("tr-daily-order-time-limit-job")
    public void trDailyOrderTimeLimitJob(){
        trDailyOrderTimeLimitService.trDailyOrderTimeLimitJob();
    }

    /**
     * 每日WMS商品历史库存
     * 每天凌晨执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("inv-lot-stock-wms-history-job")
    public void invLotStockWmsHistoryJob(){
        invLotStockWmsHistoryService.invLotStockWmsHistoryJob();
    }

    /**
     * 每日SAP商品历史库存
     * 每天凌晨执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("inv-lot-stock-sap-history-job")
    public void invLotStockSapHistoryJob(){
        invLotStockSapHistoryService.invLotStockSapHistoryJob();
    }

    /**
     * 每日补货单
     * 每天凌晨执行一次
     */
    @Scheduled(cron = "0 0 0 * * ?")
    @PostMapping("tr-replenishment-bill-job")
    public void trReplenishmentBillJob(){
        trReplenishmentBillService.trReplenishmentBillJob();
    }

}
