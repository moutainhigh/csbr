package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.hy.server.domain.vo.InBoundResultSelectVo;
import com.csbr.cloud.hy.server.entity.TrInBoundResultDetailEntity;
import com.csbr.cloud.hy.server.entity.TrInBoundResultEntity;
import com.csbr.cloud.hy.server.mapper.TrInBoundResultDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrInBoundResultMapper;
import com.csbr.cloud.hy.server.service.TrInBoundResultDetailService;
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
public class TrInBoundResultDetailServiceImpl extends ServiceImpl<TrInBoundResultDetailMapper, TrInBoundResultDetailEntity> implements TrInBoundResultDetailService {

    @Autowired
    private TrInBoundResultMapper trInBoundResultMapper;

    @Autowired
    private TrInBoundResultDetailMapper trInBoundResultDetailMapper;

    /**
     * 入库历史汇总查询
     *
     * @param param
     */
    @Override
    public Map<String, Object> getInBoundResultSelect(JSONObject param) {

        QueryWrapper<TrInBoundResultEntity> trInBoundResultQueryWrapper = new QueryWrapper<>();
        trInBoundResultQueryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("warehouseGuid"))){
            trInBoundResultQueryWrapper.eq("warehouse_guid",param.getString("warehouseGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("warehouseName"))){
            trInBoundResultQueryWrapper.eq("warehouse_name",param.getString("warehouseName"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerGuid"))){
            trInBoundResultQueryWrapper.eq("cargo_owner_guid",param.getString("cargoOwnerGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerName"))){
            trInBoundResultQueryWrapper.eq("cargo_owner_name",param.getString("cargoOwnerName"));
        }
        if(StringUtils.isNotEmpty(param.getString("bBillNo"))){
            trInBoundResultQueryWrapper.eq("b_bill_no",param.getString("bBillNo"));
        }
        if(StringUtils.isNotEmpty(param.getString("businessType"))){
            trInBoundResultQueryWrapper.eq("business_type",param.getString("businessType"));
        }
        List<InBoundResultSelectVo> inBoundResultSelectVos = new ArrayList<>();
        //设置分页
        Page<TrInBoundResultEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrInBoundResultEntity> trInBoundResultEntityIPage = trInBoundResultMapper.selectPage(page, trInBoundResultQueryWrapper);
        for (TrInBoundResultEntity trInBoundResultEntity:trInBoundResultEntityIPage.getRecords()) {
            InBoundResultSelectVo inBoundResultSelectVo = new InBoundResultSelectVo();
            BeanUtils.copyProperties(trInBoundResultEntity,inBoundResultSelectVo);
            QueryWrapper<TrInBoundResultDetailEntity> inBoundResultDetailEntityQueryWrapper = new QueryWrapper<>();
            inBoundResultDetailEntityQueryWrapper.eq("guid",trInBoundResultEntity.getGuid());
            List<TrInBoundResultDetailEntity> trInBoundResultDetails = trInBoundResultDetailMapper.selectList(inBoundResultDetailEntityQueryWrapper);
            inBoundResultSelectVo.setTrInBoundResultDetail(trInBoundResultDetails);
            inBoundResultSelectVos.add(inBoundResultSelectVo);
        }
        //封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", trInBoundResultEntityIPage.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", trInBoundResultEntityIPage.getPages());//分页总页数
        resultMap.put("pageSize", trInBoundResultEntityIPage.getSize());//当前分页总页数
        resultMap.put("pageNum", trInBoundResultEntityIPage.getCurrent());//当前页
        resultMap.put("data", inBoundResultSelectVos);
        return resultMap;
    }

    /**
     * 入库历史明细查询
     *
     * @param param
     */
    @Override
    public Map<String, Object> getInBoundResultDetailSelect(JSONObject param) {
        QueryWrapper<TrInBoundResultEntity> trInBoundResultQueryWrapper = new QueryWrapper<>();
        trInBoundResultQueryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("guid"))){
            trInBoundResultQueryWrapper.eq("guid",param.getString("guid"));
        }
        List<InBoundResultSelectVo> inBoundResultSelectVos = new ArrayList<>();
        //设置分页
        Page<TrInBoundResultEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrInBoundResultEntity> trInBoundResultEntityIPage = trInBoundResultMapper.selectPage(page, trInBoundResultQueryWrapper);
        for (TrInBoundResultEntity trInBoundResultEntity:trInBoundResultEntityIPage.getRecords()) {
            InBoundResultSelectVo inBoundResultSelectVo = new InBoundResultSelectVo();
            BeanUtils.copyProperties(trInBoundResultEntity,inBoundResultSelectVo);
            QueryWrapper<TrInBoundResultDetailEntity> inBoundResultDetailEntityQueryWrapper = new QueryWrapper<>();
            inBoundResultDetailEntityQueryWrapper.eq("guid",trInBoundResultEntity.getGuid());
            if(StringUtils.isNotEmpty(param.getString("rowNo"))){
                inBoundResultDetailEntityQueryWrapper.eq("row_no",param.getString("rowNo"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsGuid"))){
                inBoundResultDetailEntityQueryWrapper.eq("goods_guid",param.getString("goodsGuid"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
                inBoundResultDetailEntityQueryWrapper.eq("goods_code",param.getString("goodsCode"));
            }
            if(StringUtils.isNotEmpty(param.getString("goodsName"))){
                inBoundResultDetailEntityQueryWrapper.eq("goods_name",param.getString("goodsName"));
            }
            if(StringUtils.isNotEmpty(param.getString("lot"))){
                inBoundResultDetailEntityQueryWrapper.eq("lot",param.getString("lot"));
            }
            List<TrInBoundResultDetailEntity> trInBoundResultDetails = trInBoundResultDetailMapper.selectList(inBoundResultDetailEntityQueryWrapper);
            inBoundResultSelectVo.setTrInBoundResultDetail(trInBoundResultDetails);
            inBoundResultSelectVos.add(inBoundResultSelectVo);
        }
        //封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", trInBoundResultEntityIPage.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", trInBoundResultEntityIPage.getPages());//分页总页数
        resultMap.put("pageSize", trInBoundResultEntityIPage.getSize());//当前分页总页数
        resultMap.put("pageNum", trInBoundResultEntityIPage.getCurrent());//当前页
        resultMap.put("data", inBoundResultSelectVos);
        return resultMap;
    }
}
