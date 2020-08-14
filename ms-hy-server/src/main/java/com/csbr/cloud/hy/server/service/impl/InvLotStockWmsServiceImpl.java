package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.vo.StockDifferenceVo;
import com.csbr.cloud.hy.server.entity.InvLotStockSapEntity;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.csbr.cloud.hy.server.mapper.InvLotStockSapMapper;
import com.csbr.cloud.hy.server.mapper.InvLotStockWmsMapper;
import com.csbr.cloud.hy.server.service.InvLotStockWmsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Service
@Transactional
public class InvLotStockWmsServiceImpl extends ServiceImpl<InvLotStockWmsMapper, InvLotStockWmsEntity> implements InvLotStockWmsService {

    @Autowired
    InvLotStockWmsMapper invLotStockWmsMapper;


    /**
     * 库存差异查询
     *
     * @param param
     */
    @Override
    public PageInfo<Map<String, Object>> getStockDifference(JSONObject param) {
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> stockDifference = invLotStockWmsMapper.getStockDifference(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(stockDifference);
        return pageInfo;
    }

    /**
     * 滞销库存查询
     *
     * @param param
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getUnsalableStock(JSONObject param) {
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> unsalableStock = invLotStockWmsMapper.getUnsalableStock(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(unsalableStock);
        return pageInfo;
    }
}
