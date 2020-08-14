package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.hy.server.domain.vo.OutBoundResultSelectVo;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultDetailEntity;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultEntity;
import com.csbr.cloud.hy.server.mapper.TrOutBoundResultDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrOutBoundResultMapper;
import com.csbr.cloud.hy.server.service.TrOutBoundResultDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-05-18
 */
@Service
@Transactional
public class TrOutBoundResultDetailServiceImpl extends ServiceImpl<TrOutBoundResultDetailMapper, TrOutBoundResultDetailEntity> implements TrOutBoundResultDetailService {

    @Autowired
    private TrOutBoundResultMapper trOutBoundResultMapper;

    @Autowired
    private TrOutBoundResultDetailMapper trOutBoundResultDetailMapper;

    /**
     * 出库历史汇总查询
     *
     * @param param
     */
    @Override
    public Map<String, Object> getOutBoundResultSelect(JSONObject param) {
        QueryWrapper<TrOutBoundResultEntity> trOutBoundResultQueryWrapper = new QueryWrapper<>();
        trOutBoundResultQueryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("warehouseGuid"))){
            trOutBoundResultQueryWrapper.eq("warehouse_guid",param.getString("warehouseGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("warehouseName"))){
            trOutBoundResultQueryWrapper.eq("warehouse_name",param.getString("warehouseName"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerGuid"))){
            trOutBoundResultQueryWrapper.eq("cargo_owner_guid",param.getString("cargoOwnerGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerName"))){
            trOutBoundResultQueryWrapper.eq("cargo_owner_name",param.getString("cargoOwnerName"));
        }
        if(StringUtils.isNotEmpty(param.getString("bBillNo"))){
            trOutBoundResultQueryWrapper.eq("b_bill_no",param.getString("bBillNo"));
        }
        if(StringUtils.isNotEmpty(param.getString("businessType"))){
            trOutBoundResultQueryWrapper.eq("business_type",param.getString("businessType"));
        }
        List<OutBoundResultSelectVo> outBoundResultSelectVos = new ArrayList<>();
        //设置分页
        Page<TrOutBoundResultEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrOutBoundResultEntity> trOutBoundResultEntityIPage = trOutBoundResultMapper.selectPage(page, trOutBoundResultQueryWrapper);
        for (TrOutBoundResultEntity trOutBoundResultEntity:trOutBoundResultEntityIPage.getRecords()) {
            OutBoundResultSelectVo outBoundResultSelectVo = new OutBoundResultSelectVo();
            BeanUtils.copyProperties(trOutBoundResultEntity,outBoundResultSelectVo);
            QueryWrapper<TrOutBoundResultDetailEntity> trOutBoundResultDetailQueryWrapper = new QueryWrapper<>();
            trOutBoundResultDetailQueryWrapper.eq("guid",trOutBoundResultEntity.getGuid());
            List<TrOutBoundResultDetailEntity> trOutBoundResultDetails = trOutBoundResultDetailMapper.selectList(trOutBoundResultDetailQueryWrapper);
            outBoundResultSelectVo.setTrOutBoundResultDetails(trOutBoundResultDetails);
            outBoundResultSelectVos.add(outBoundResultSelectVo);
        }
        //封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", trOutBoundResultEntityIPage.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", trOutBoundResultEntityIPage.getPages());//分页总页数
        resultMap.put("pageSize", trOutBoundResultEntityIPage.getSize());//当前分页总页数
        resultMap.put("pageNum", trOutBoundResultEntityIPage.getCurrent());//当前页
        resultMap.put("data", outBoundResultSelectVos);
        return resultMap;
    }

    /**
     * 出库历史明细查询
     *
     * @param param
     */
    @Override
    public Map<String, Object> getOutBoundResultDetailSelect(JSONObject param) {
        QueryWrapper<TrOutBoundResultEntity> trOutBoundResultQueryWrapper = new QueryWrapper<>();
        trOutBoundResultQueryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("guid"))){
            trOutBoundResultQueryWrapper.eq("guid",param.getString("guid"));
        }
        List<OutBoundResultSelectVo> outBoundResultSelectVos = new ArrayList<>();
        //设置分页
        Page<TrOutBoundResultEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrOutBoundResultEntity> trOutBoundResultEntityIPage = trOutBoundResultMapper.selectPage(page, trOutBoundResultQueryWrapper);
        for (TrOutBoundResultEntity trOutBoundResultEntity:trOutBoundResultEntityIPage.getRecords()) {
            OutBoundResultSelectVo outBoundResultSelectVo = new OutBoundResultSelectVo();
            BeanUtils.copyProperties(trOutBoundResultEntity,outBoundResultSelectVo);
            QueryWrapper<TrOutBoundResultDetailEntity> trOutBoundResultDetailQueryWrapper = new QueryWrapper<>();
            trOutBoundResultDetailQueryWrapper.eq("guid",trOutBoundResultEntity.getGuid());
            if(StringUtils.isNotEmpty(param.getString("rowNo"))){
                trOutBoundResultDetailQueryWrapper.eq("row_no",param.getString("rowNo"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsGuid"))){
                trOutBoundResultDetailQueryWrapper.eq("goods_guid",param.getString("goodsGuid"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
                trOutBoundResultDetailQueryWrapper.eq("goods_code",param.getString("goodsCode"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsName"))){
                trOutBoundResultDetailQueryWrapper.eq("goods_name",param.getString("goodsName"));
            }
            if(StringUtils.isNotEmpty(param.getString("lot"))){
                trOutBoundResultDetailQueryWrapper.eq("lot",param.getString("lot"));
            }
            List<TrOutBoundResultDetailEntity> trOutBoundResultDetails = trOutBoundResultDetailMapper.selectList(trOutBoundResultDetailQueryWrapper);
            outBoundResultSelectVo.setTrOutBoundResultDetails(trOutBoundResultDetails);
            outBoundResultSelectVos.add(outBoundResultSelectVo);
        }
        //封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", trOutBoundResultEntityIPage.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", trOutBoundResultEntityIPage.getPages());//分页总页数
        resultMap.put("pageSize", trOutBoundResultEntityIPage.getSize());//当前分页总页数
        resultMap.put("pageNum", trOutBoundResultEntityIPage.getCurrent());//当前页
        resultMap.put("data", outBoundResultSelectVos);
        return resultMap;
    }
}
