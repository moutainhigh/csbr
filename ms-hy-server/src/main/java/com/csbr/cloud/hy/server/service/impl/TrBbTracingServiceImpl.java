package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.*;
import com.csbr.cloud.hy.server.mapper.*;
import com.csbr.cloud.hy.server.service.TrBbTracingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
public class TrBbTracingServiceImpl extends ServiceImpl<TrBbTracingMapper, TrBbTracingEntity> implements TrBbTracingService {

    @Autowired
    private TrBbTracingMapper trBbTracingMapper;

    //物流主表
    @Autowired
    private TrTplpoMainMapper trTplpoMainMapper;

    //业务汇总表
    @Autowired
    private TrTplbbMapper trTplbbMapper;

    @Autowired
    private TrDeliveryTimeoutOrderMapper trDeliveryTimeoutOrderMapper;

    @Autowired
    private TrSignTimeoutOrderMapper trSignTimeoutOrderMapper;

    /**
     * 新增物流执行跟踪
     *
     * @param trBbTracingEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes postBBTracingInsert(TrBbTracingEntity trBbTracingEntity) {

        //新增物流执行跟踪
        trBbTracingMapper.insert(trBbTracingEntity);
        Map<String,Object> map = new HashMap<>();
        map.put("bBillGuid",trBbTracingEntity.getBBillGuid());
        Map<String, Object> trTplpoMainInfo = trTplpoMainMapper.getTrTplpoMainInfo(map);
        String json = JSON.toJSONString(trTplpoMainInfo);//map转String
        JSONObject jsonObject = JSON.parseObject(json);//String转json
        //新增提货超时订单
        int collectiontime = trBbTracingEntity.getExcuteDate().compareTo(jsonObject.getDate("collectiontime"));
        if(collectiontime > 0 ){
            TrDeliveryTimeoutOrderEntity trDeliveryTimeoutOrderEntity = new TrDeliveryTimeoutOrderEntity();
            trDeliveryTimeoutOrderEntity.setGuid(String.valueOf(UUID.randomUUID()).replace("-", ""));
            trDeliveryTimeoutOrderEntity.setTenantGuid(trBbTracingEntity.getTenantGuid());
            trDeliveryTimeoutOrderEntity.setCorpGuid(trBbTracingEntity.getCorpGuid());
            trDeliveryTimeoutOrderEntity.setCargoOwnerGuid(trBbTracingEntity.getCargoOwnerGuid());
            trDeliveryTimeoutOrderEntity.setCargoOwnerName(trBbTracingEntity.getCargoOwnerName());
            trDeliveryTimeoutOrderEntity.setDeliverGuid(jsonObject.getString("deliverGuid"));
            trDeliveryTimeoutOrderEntity.setDeliverName(jsonObject.getString("deliverName"));
            trDeliveryTimeoutOrderEntity.setBBillGuid(trBbTracingEntity.getBBillGuid());
            trDeliveryTimeoutOrderEntity.setBBillNo(trBbTracingEntity.getBBillNo());
            trDeliveryTimeoutOrderEntity.setCollectiontime(jsonObject.getDate("collectiontime"));
            trDeliveryTimeoutOrderEntity.setTimeLimit(jsonObject.getInteger("timeLimit"));
            trDeliveryTimeoutOrderEntity.setCreateTime(new Date());
            trDeliveryTimeoutOrderMapper.insert(trDeliveryTimeoutOrderEntity);
        }
        int arrivalArrivedDate = trBbTracingEntity.getExcuteDate().compareTo(jsonObject.getDate("arrivalArrivedDate"));
        //新增到货签收超时订单
        if(arrivalArrivedDate > 0){
            TrSignTimeoutOrderEntity trSignTimeoutOrderEntity = new TrSignTimeoutOrderEntity();
            trSignTimeoutOrderEntity.setGuid(String.valueOf(UUID.randomUUID()).replace("-", ""));
            trSignTimeoutOrderEntity.setTenantGuid(trBbTracingEntity.getTenantGuid());
            trSignTimeoutOrderEntity.setCorpGuid(trBbTracingEntity.getCorpGuid());
            trSignTimeoutOrderEntity.setCargoOwnerGuid(trBbTracingEntity.getCargoOwnerGuid());
            trSignTimeoutOrderEntity.setCargoOwnerName(trBbTracingEntity.getCargoOwnerName());
            trSignTimeoutOrderEntity.setDeliverGuid(jsonObject.getString("deliverGuid"));
            trSignTimeoutOrderEntity.setDeliverName(jsonObject.getString("deliverName"));
            trSignTimeoutOrderEntity.setBBillGuid(trBbTracingEntity.getBBillGuid());
            trSignTimeoutOrderEntity.setBBillNo(trBbTracingEntity.getBBillNo());
            trSignTimeoutOrderEntity.setArrivalArrivedDate(jsonObject.getDate("arrivalArrivedDate"));
            trSignTimeoutOrderEntity.setLastArrivalArrivedDate(jsonObject.getDate("lastArrivalArrivedDate"));
            trSignTimeoutOrderEntity.setCreateTime(new Date());
            trSignTimeoutOrderMapper.insert(trSignTimeoutOrderEntity);
        }

        //更新物流主表和业务汇总表状态 业务单据GUID
        //根据物流跟踪表的业务单据GUID修改bb表的状态
        if(StringUtils.isNotEmpty(trBbTracingEntity.getBBillGuid())){
                QueryWrapper<TrTplbbEntity> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("guid",trBbTracingEntity.getBBillGuid());
                TrTplbbEntity selectOne = trTplbbMapper.selectOne(queryWrapper);
                if(selectOne != null){
                    //修改业务汇总表状态
                    UpdateWrapper<TrTplbbEntity> updateWrapper = new UpdateWrapper<>();
                    updateWrapper.eq("guid",selectOne.getGuid());
                    selectOne.setState(trBbTracingEntity.getState());
                    int update = trTplbbMapper.update(selectOne, updateWrapper);
//                    if(update > 0){
//                        return CommonRes.ok("修改sucess",null);
//                    }
                    //修改物流主表状态

                    UpdateWrapper<TrTplpoMainEntity> updateTrTplpoMainWrapper = new UpdateWrapper<>();
                    updateTrTplpoMainWrapper.eq("guid",selectOne.getMainPoGuid());
                    TrTplpoMainEntity trTplpoMainEntity = new TrTplpoMainEntity().setState(trBbTracingEntity.getState());
                    trTplpoMainMapper.update(trTplpoMainEntity,updateTrTplpoMainWrapper);
                    Date date = new Date();
                    //提货时更新实际提货时间 提货交接
                    if(trBbTracingEntity.getState().equals("52")){
                        trTplpoMainEntity.setRealityCollectiontime(date);
                        trTplpoMainMapper.update(trTplpoMainEntity,updateTrTplpoMainWrapper);
                    }
                    //送货到达时更新送达实际 到货签收
                    if(trBbTracingEntity.getState().equals("81")){
                        trTplpoMainEntity.setRealityArrivalArrivedDate(date);
                        trTplpoMainMapper.update(trTplpoMainEntity,updateTrTplpoMainWrapper);

                    }
                    //回单时修改主表回单状态 回单确认
                    if(trBbTracingEntity.getState().equals("82")){
                        trTplpoMainEntity.setBackBillFlag("Y");
                        trTplpoMainMapper.update(trTplpoMainEntity,updateTrTplpoMainWrapper);
                    }
                }
        }

        return CommonRes.ok("sucess",null);
    }
}
