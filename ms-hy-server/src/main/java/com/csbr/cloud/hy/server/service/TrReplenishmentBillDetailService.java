package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrReplenishmentBillDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface TrReplenishmentBillDetailService extends IService<TrReplenishmentBillDetailEntity> {

    /**
     * 补货单明细查询
     */
    CommonRes getReplenishmentBillDetailSelect(JSONObject param);

}
