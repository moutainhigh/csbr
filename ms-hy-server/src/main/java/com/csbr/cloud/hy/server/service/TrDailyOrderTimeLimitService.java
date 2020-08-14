package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrDailyOrderTimeLimitEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface TrDailyOrderTimeLimitService extends IService<TrDailyOrderTimeLimitEntity> {


    /**
     * 订单执行时效查询
     */
    CommonRes getDailyOrderTimeLimitSelect(JSONObject param);

    /**
     * 每日订单时效统计
     */
    void trDailyOrderTimeLimitJob();
}
