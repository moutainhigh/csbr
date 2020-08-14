package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrReplenishmentBillEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface TrReplenishmentBillService extends IService<TrReplenishmentBillEntity> {

    /**
     * 补货单表查询
     */
    CommonRes getReplenishmentBillSelect(JSONObject param);


    /**
     * 补货单
     */
    void trReplenishmentBillJob();

}
