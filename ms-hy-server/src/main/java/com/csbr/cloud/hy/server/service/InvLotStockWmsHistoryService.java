package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsHistoryEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface InvLotStockWmsHistoryService extends IService<InvLotStockWmsHistoryEntity> {

    /**
     * 根据仓库汇总查询库存
     */
    PageInfo<Map<String, Object>> getLotStockWms(JSONObject param);

    /**
     * 查询仓库库存明细
     */
    CommonRes getLotStockWmsHistory(JSONObject param);

    /**
     * 根据商品汇总查询库存
     */
    CommonRes getWmsStockByGoods(JSONObject param);

    /**
     * WMS商品历史库存
     */
    void invLotStockWmsHistoryJob();
}
