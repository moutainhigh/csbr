package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface InvLotStockWmsService extends IService<InvLotStockWmsEntity> {

    /**
     * 库存差异查询
     */
    PageInfo<Map<String, Object>> getStockDifference(JSONObject param);


    /**
     * 滞销库存查询
     * @param param
     * @return
     */
    PageInfo<Map<String, Object>> getUnsalableStock(JSONObject param);
}
