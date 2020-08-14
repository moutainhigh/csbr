package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrPlanSalesDto;
import com.csbr.cloud.hy.server.entity.TrPlanSalesEntity;
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
public interface TrPlanSalesService extends IService<TrPlanSalesEntity> {

    /**
     * 预期销量设置新增修改
     */
    CommonRes postPlanSalesOperation(TrPlanSalesDto trPlanSalesDto);

    /**
     * 预期销量设置查询
     */
    CommonRes getPlanSalesSelect(JSONObject param);

    /**
     * 预期销量明细查询
     */
    CommonRes getPlanSalesDetailSelect(JSONObject param);

    /**
     * 查询计划销量
     */
    PageInfo<Map<String, Object>> getSalesAccuracy(JSONObject param);
}
