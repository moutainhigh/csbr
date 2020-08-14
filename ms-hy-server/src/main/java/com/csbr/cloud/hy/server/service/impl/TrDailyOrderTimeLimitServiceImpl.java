package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrDailyOrderTimeLimitEntity;
import com.csbr.cloud.hy.server.mapper.TrDailyOrderTimeLimitMapper;
import com.csbr.cloud.hy.server.mapper.TrDeliveryTimeoutOrderMapper;
import com.csbr.cloud.hy.server.mapper.TrSignTimeoutOrderMapper;
import com.csbr.cloud.hy.server.mapper.TrTplpoMainMapper;
import com.csbr.cloud.hy.server.service.TrDailyOrderTimeLimitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TrDailyOrderTimeLimitServiceImpl extends ServiceImpl<TrDailyOrderTimeLimitMapper, TrDailyOrderTimeLimitEntity> implements TrDailyOrderTimeLimitService {


    @Autowired
    private TrDailyOrderTimeLimitMapper trDailyOrderTimeLimitMapper;

    @Autowired
    private TrDeliveryTimeoutOrderMapper trDeliveryTimeoutOrderMapper;

    @Autowired
    private TrSignTimeoutOrderMapper trSignTimeoutOrderMapper;

    //物流主表
    @Autowired
    private TrTplpoMainMapper trTplpoMainMapper;

    /**
     * 订单执行时效查询
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public CommonRes getDailyOrderTimeLimitSelect(JSONObject param) {
        QueryWrapper<TrDailyOrderTimeLimitEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerGuid"))){
            queryWrapper.eq("cargo_owner_guid",param.getString("cargoOwnerGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerName"))){
            queryWrapper.eq("cargo_owner_name",param.getString("cargoOwnerName"));
        }
        if(StringUtils.isNotEmpty(param.getString("deliverGuid"))){
            queryWrapper.eq("deliver_guid",param.getString("deliverGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("deliverName"))){
            queryWrapper.eq("deliver_name",param.getString("deliverName"));
        }
        if(StringUtils.isNotEmpty(param.getString("startTime"))){
            queryWrapper.ge("create_time",param.getString("startTime"));
        }
        if(StringUtils.isNotEmpty(param.getString("endTime"))){
            queryWrapper.le("create_time",param.getString("endTime"));
        }
        //设置分页
        Page<TrDailyOrderTimeLimitEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrDailyOrderTimeLimitEntity> trDailyOrderTimeLimitEntityIPage = trDailyOrderTimeLimitMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(trDailyOrderTimeLimitEntityIPage));
    }

    /**
     * 每日订单时效统计
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trDailyOrderTimeLimitJob() {
        //发货量
        List<Map<String, Object>> trDailyOrderTimeLimitJobs = trTplpoMainMapper.trDailyOrderTimeLimitJob(new HashMap<>());
        for (Map<String, Object> trDailyOrderTimeLimitJob:trDailyOrderTimeLimitJobs) {
            String json = JSON.toJSONString(trDailyOrderTimeLimitJob, SerializerFeature.WriteMapNullValue);//map转String
            JSONObject jsonObject = JSON.parseObject(json);//String转json
            TrDailyOrderTimeLimitEntity trDailyOrderTimeLimitEntity = null;
            trDailyOrderTimeLimitEntity = jsonObject.toJavaObject(jsonObject, TrDailyOrderTimeLimitEntity.class);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
            queryWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
            queryWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
            queryWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
            queryWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
            queryWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
            queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
            TrDailyOrderTimeLimitEntity dailyOrderTimeLimitEntity = trDailyOrderTimeLimitMapper.selectOne(queryWrapper);
            if(dailyOrderTimeLimitEntity != null){
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
                updateWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
                updateWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
                updateWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
                updateWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
                updateWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
                updateWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
                //发货量
                dailyOrderTimeLimitEntity.setTotleDeliverGoods(trDailyOrderTimeLimitEntity.getTotleDeliverGoods());
                trDailyOrderTimeLimitMapper.update(dailyOrderTimeLimitEntity,updateWrapper);

            }else{
                trDailyOrderTimeLimitMapper.insert(trDailyOrderTimeLimitEntity);
            }
        }

        //提货超时量
        List<Map<String, Object>> trDeliveryTimeoutOrderJobs = trDeliveryTimeoutOrderMapper.trDeliveryTimeoutOrderJob(new HashMap<>());
        for (Map<String, Object> trDeliveryTimeoutOrderJob:trDeliveryTimeoutOrderJobs) {
            String json = JSON.toJSONString(trDeliveryTimeoutOrderJob, SerializerFeature.WriteMapNullValue);//map转String
            JSONObject jsonObject = JSON.parseObject(json);//String转json
            TrDailyOrderTimeLimitEntity trDailyOrderTimeLimitEntity = null;
            trDailyOrderTimeLimitEntity = jsonObject.toJavaObject(jsonObject, TrDailyOrderTimeLimitEntity.class);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
            queryWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
            queryWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
            queryWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
            queryWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
            queryWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
            queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
            TrDailyOrderTimeLimitEntity dailyOrderTimeLimitEntity = trDailyOrderTimeLimitMapper.selectOne(queryWrapper);
            if(dailyOrderTimeLimitEntity != null){
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
                updateWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
                updateWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
                updateWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
                updateWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
                updateWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
                updateWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
                //提货超时
                dailyOrderTimeLimitEntity.setTotleDeliveryTimeout(trDailyOrderTimeLimitEntity.getTotleDeliverGoods());
                trDailyOrderTimeLimitMapper.update(dailyOrderTimeLimitEntity,updateWrapper);

            }else{
                trDailyOrderTimeLimitMapper.insert(trDailyOrderTimeLimitEntity);
            }
        }

        //到站签收超时量
        List<Map<String, Object>> trSignTimeoutOrderJobs = trSignTimeoutOrderMapper.trSignTimeoutOrderJob(new HashMap<>());
        for (Map<String, Object> trSignTimeoutOrderJob:trSignTimeoutOrderJobs) {
            String json = JSON.toJSONString(trSignTimeoutOrderJob, SerializerFeature.WriteMapNullValue);//map转String
            JSONObject jsonObject = JSON.parseObject(json);//String转json
            TrDailyOrderTimeLimitEntity trDailyOrderTimeLimitEntity = null;
            trDailyOrderTimeLimitEntity = jsonObject.toJavaObject(jsonObject, TrDailyOrderTimeLimitEntity.class);
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
            queryWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
            queryWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
            queryWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
            queryWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
            queryWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
            queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
            TrDailyOrderTimeLimitEntity dailyOrderTimeLimitEntity = trDailyOrderTimeLimitMapper.selectOne(queryWrapper);
            if(dailyOrderTimeLimitEntity != null){
                UpdateWrapper updateWrapper = new UpdateWrapper();
                updateWrapper.eq("tenant_guid",trDailyOrderTimeLimitEntity.getTenantGuid());
                updateWrapper.eq("corp_guid",trDailyOrderTimeLimitEntity.getCorpGuid());
                updateWrapper.eq("cargo_owner_guid",trDailyOrderTimeLimitEntity.getCargoOwnerGuid());
                updateWrapper.eq("cargo_owner_name",trDailyOrderTimeLimitEntity.getCargoOwnerName());
                updateWrapper.eq("deliver_guid",trDailyOrderTimeLimitEntity.getDeliverGuid());
                updateWrapper.eq("deliver_name",trDailyOrderTimeLimitEntity.getDeliverName());
                updateWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", trDailyOrderTimeLimitEntity.getCreateTime());
                //到站签收超时
                dailyOrderTimeLimitEntity.setTotleSignTimeout(trDailyOrderTimeLimitEntity.getTotleSignTimeout());
                trDailyOrderTimeLimitMapper.update(dailyOrderTimeLimitEntity,updateWrapper);

            }else{
                trDailyOrderTimeLimitMapper.insert(trDailyOrderTimeLimitEntity);
            }
        }

    }

}
