package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.hy.server.entity.TrInBoundResultDetailEntity;
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
public interface TrInBoundResultDetailService extends IService<TrInBoundResultDetailEntity> {

    /**
     * 入库历史汇总查询
     */
    Map<String, Object> getInBoundResultSelect(JSONObject param);

    /**
     * 入库历史明细查询
     */
    Map<String, Object> getInBoundResultDetailSelect(JSONObject param);

}
