package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.hy.server.entity.InvLotStockSapHistoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface InvLotStockSapHistoryService extends IService<InvLotStockSapHistoryEntity> {

    /**
     * SAP商品历史库存
     */
    void invLotStockSapHistoryJob();

}
