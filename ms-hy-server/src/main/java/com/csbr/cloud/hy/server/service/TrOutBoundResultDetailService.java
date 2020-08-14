package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-18
 */
public interface TrOutBoundResultDetailService extends IService<TrOutBoundResultDetailEntity> {

    /**
     * 出库历史汇总查询
     */
    Map<String, Object> getOutBoundResultSelect(JSONObject param);

    /**
     * 出库历史明细查询
     */
    Map<String, Object> getOutBoundResultDetailSelect(JSONObject param);

}
