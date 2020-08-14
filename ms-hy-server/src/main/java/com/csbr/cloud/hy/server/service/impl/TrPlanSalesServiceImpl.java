package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrPlanSalesDto;
import com.csbr.cloud.hy.server.entity.TrPlanSalesDetailEntity;
import com.csbr.cloud.hy.server.entity.TrPlanSalesEntity;
import com.csbr.cloud.hy.server.mapper.TrPlanSalesDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrPlanSalesMapper;
import com.csbr.cloud.hy.server.service.TrPlanSalesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@Service
@Transactional
public class TrPlanSalesServiceImpl extends ServiceImpl<TrPlanSalesMapper, TrPlanSalesEntity> implements TrPlanSalesService {

    @Autowired
    private TrPlanSalesMapper trPlanSalesMapper;

    @Autowired
    private TrPlanSalesDetailMapper trPlanSalesDetailMapper;


    /**
     * 预期销量设置新增修改
     *
     * @param trPlanSalesDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes postPlanSalesOperation(TrPlanSalesDto trPlanSalesDto) {
        TrPlanSalesEntity trPlanSalesEntity = new TrPlanSalesEntity();
        BeanUtils.copyProperties(trPlanSalesDto,trPlanSalesEntity);
        if(StringUtils.isNotEmpty(trPlanSalesDto.getGuid())){
            //主表
            QueryWrapper<TrPlanSalesEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("guid",trPlanSalesDto.getGuid());
            TrPlanSalesEntity selectTrPlanSalesOne = trPlanSalesMapper.selectOne(queryWrapper);
            if(selectTrPlanSalesOne != null){
                //修改主表
                UpdateWrapper<TrPlanSalesEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("guid",trPlanSalesDto.getGuid());
                int update = trPlanSalesMapper.update(trPlanSalesEntity, updateWrapper);
            }else{
                //新增主表
                trPlanSalesMapper.insert(trPlanSalesEntity);
            }
            //明细表
            List<TrPlanSalesDetailEntity> trPlanSalesDetails = trPlanSalesDto.getTrPlanSalesDetails();
            if(trPlanSalesDetails != null && trPlanSalesDetails.size() > 0){
                for (TrPlanSalesDetailEntity trPlanSalesDetailEntity:trPlanSalesDetails) {
                    if(StringUtils.isNotEmpty(trPlanSalesDetailEntity.getPlanSalesGuid()) && StringUtils.isNotEmpty(trPlanSalesDetailEntity.getGoodsGuid())){
                        QueryWrapper<TrPlanSalesDetailEntity> trPlanSalesDetailQueryWrapper = new QueryWrapper<>();
                        trPlanSalesDetailQueryWrapper.eq("plan_sales_guid",trPlanSalesDetailEntity.getPlanSalesGuid());
                        trPlanSalesDetailQueryWrapper.eq("goods_guid",trPlanSalesDetailEntity.getGoodsGuid());
                        TrPlanSalesDetailEntity selectOne = trPlanSalesDetailMapper.selectOne(trPlanSalesDetailQueryWrapper);
                        if(selectOne != null){
                            //修改明细表
                            UpdateWrapper<TrPlanSalesDetailEntity> trPlanSalesDetailUpdateWrapper = new UpdateWrapper<>();
                            trPlanSalesDetailUpdateWrapper.eq("plan_sales_guid",trPlanSalesDetailEntity.getPlanSalesGuid());
                            trPlanSalesDetailUpdateWrapper.eq("goods_guid",trPlanSalesDetailEntity.getGoodsGuid());
                            trPlanSalesDetailMapper.update(trPlanSalesDetailEntity,trPlanSalesDetailUpdateWrapper);
                        }else{
                            //新增明细表
                            trPlanSalesDetailMapper.insert(trPlanSalesDetailEntity);
                        }
                    }
                }
            }
        }else{
            return CommonRes.ok("主表guid为空!",null);
        }

        return CommonRes.ok();
    }

    /**
     * 预期销量设置查询
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public CommonRes getPlanSalesSelect(JSONObject param) {

        QueryWrapper<TrPlanSalesEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("logAreaGuid"))){
            queryWrapper.eq("log_area_guid",param.getString("logAreaGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("wareHouseGuid"))){
            queryWrapper.eq("ware_house_guid",param.getString("wareHouseGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("deptGuid"))){
            queryWrapper.eq("dept_guid",param.getString("deptGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("deptName"))){
            queryWrapper.eq("dept_name",param.getString("deptName"));
        }
        if(StringUtils.isNotEmpty(param.getString("planYear"))){
            queryWrapper.eq("plan_year",param.getString("planYear"));
        }
        if(StringUtils.isNotEmpty(param.getString("status"))){
            queryWrapper.eq("status",param.getString("status"));
        }
        //设置分页
        Page<TrPlanSalesEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrPlanSalesEntity> mapIPage = trPlanSalesMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }

    /**
     * 预期销量明细查询
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public CommonRes getPlanSalesDetailSelect(JSONObject param) {
        QueryWrapper<TrPlanSalesDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plan_sales_guid",param.getString("guid"));
        if(StringUtils.isNotEmpty(param.getString("goodsGuid"))){
            queryWrapper.eq("goods_guid",param.getString("goodsGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
            queryWrapper.eq("goods_code",param.getString("goodsCode"));
        }
        //设置分页
        Page<TrPlanSalesDetailEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrPlanSalesDetailEntity> mapIPage = trPlanSalesDetailMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }

    /**
     * 查询计划销量
     *
     * @param param
     */
    @Override
    public PageInfo<Map<String, Object>> getSalesAccuracy(JSONObject param) {
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> salesAccuracy = trPlanSalesMapper.getSalesAccuracy(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(salesAccuracy);
        return pageInfo;
    }
}
