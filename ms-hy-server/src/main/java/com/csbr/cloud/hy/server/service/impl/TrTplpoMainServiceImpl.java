package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrTplpoMainDto;
import com.csbr.cloud.hy.server.domain.vo.TrTplpoMainVo;
import com.csbr.cloud.hy.server.entity.TrBbTracingEntity;
import com.csbr.cloud.hy.server.entity.TrTplbbDetailEntity;
import com.csbr.cloud.hy.server.entity.TrTplbbEntity;
import com.csbr.cloud.hy.server.entity.TrTplpoMainEntity;
import com.csbr.cloud.hy.server.mapper.TrBbTracingMapper;
import com.csbr.cloud.hy.server.mapper.TrTplbbDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrTplbbMapper;
import com.csbr.cloud.hy.server.mapper.TrTplpoMainMapper;
import com.csbr.cloud.hy.server.service.TrTplpoMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

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
@Transactional(propagation= Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor= RestClientException.class)
public class TrTplpoMainServiceImpl extends ServiceImpl<TrTplpoMainMapper, TrTplpoMainEntity> implements TrTplpoMainService {

    //物流订单汇总
    @Autowired
    private TrTplpoMainMapper trTplpoMainMapper;

    //物流业务单据汇总
    @Autowired
    private TrTplbbMapper trTplbbMapper;

    //物流业务单据明细
    @Autowired
    private TrTplbbDetailMapper trTplbbDetailMapper;

    //物流追踪
    @Autowired
    private TrBbTracingMapper trBbTracingMapper;

    /**
     * 新增物流订单(同时增加三张表数据)
     *
     * @param trTplpoMainDto
     */
    @Override
    @Transactional
    public void postPoMainInsert(TrTplpoMainDto trTplpoMainDto) {
        //生成 uuid
//        trTplpoMainDto.setGuid(String.valueOf(UUID.randomUUID()).replace("-", ""));
        TrTplpoMainEntity trTplpoMainEntity = new TrTplpoMainEntity();
        BeanUtils.copyProperties(trTplpoMainDto,trTplpoMainEntity);
        //添加物流订单汇总
        trTplpoMainMapper.insert(trTplpoMainEntity);
        List<TrTplbbEntity> trTplbbs = trTplpoMainDto.getTrTplbb();
        //如果大于1000条数据转用批量添加 1000条内批量插入没意义
        for (TrTplbbEntity trTplbbEntity: trTplbbs) {
            trTplbbMapper.insert(trTplbbEntity);
        }
        List<TrTplbbDetailEntity> trTplbbDetails = trTplpoMainDto.getTrTplbbDetail();
        for (TrTplbbDetailEntity trTplbbDetailEntity:trTplbbDetails) {
            trTplbbDetailMapper.insert(trTplbbDetailEntity);
        }

    }

    /**
     * 查询物流订单主表
     *
     * @param obj
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getPoMainSelect(JSONObject obj) {

        PageHelper.startPage(obj.getInteger("pageIndex"), obj.getInteger("pageSize"));
        List<Map<String, Object>> poMainSelect = trTplpoMainMapper.getPoMainSelect(obj);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(poMainSelect);
        return pageInfo;
    }

    /**
     * 订单节点监控
     *
     * @param param
     */
    @Override
    public Map<String, Object> getOrderMonitoring(JSONObject param) {
        //主表查询
//        QueryWrapper mainQueryWrapper = new QueryWrapper<>();
//        mainQueryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
//        if(StringUtils.isNotEmpty(param.getString("tplpoNo"))){
//            mainQueryWrapper.eq("tplpo_no",param.getString("tplpoNo"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("mainPoGuid"))){
//            mainQueryWrapper.eq("guid",param.getString("mainPoGuid"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("departureAddress"))){
//            mainQueryWrapper.eq("departure_address",param.getString("departureAddress"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("deliverGuid"))){
//            mainQueryWrapper.eq("deliver_guid",param.getString("deliverGuid"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("deliverName"))){
//            mainQueryWrapper.eq("deliver_name",param.getString("deliverName"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("warehouseGuid"))){
//            mainQueryWrapper.eq("warehouse_guid",param.getString("warehouseGuid"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("warehouseName"))){
//            mainQueryWrapper.eq("warehouse_name",param.getString("warehouseName"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("routeGuid"))){
//            mainQueryWrapper.eq("route_guid",param.getString("routeGuid"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("routeName"))){
//            mainQueryWrapper.eq("route_name",param.getString("routeName"));
//        }
//        if(StringUtils.isNotEmpty(param.getString("mainState"))){
//            mainQueryWrapper.eq("state",param.getString("mainState"));
//        }

//        IPage<TrTplpoMainEntity> iPage = trTplpoMainMapper.selectPage(new Page<TrTplpoMainEntity>(param.getInteger("pageIndex"), param.getInteger("pageSize")), mainQueryWrapper);
//        List<TrTplpoMainEntity> records = iPage.getRecords();
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> poMainSelect = trTplpoMainMapper.getPoMainSelect(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(poMainSelect);
        List<TrTplpoMainVo> trTplpoMainVos = new ArrayList<>();
        for (Map map: pageInfo.getList()) {
//            TrTplpoMainVo trTplpoMainVo = new TrTplpoMainVo();
//            BeanUtils.copyProperties(trTplpoMainEntity,trTplpoMainVo);
            String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);//map转String
            JSONObject jsonObject = JSON.parseObject(json);//String转json
            TrTplpoMainVo trTplpoMainVo = jsonObject.toJavaObject(jsonObject, TrTplpoMainVo.class);
            //物流追踪表查询
            QueryWrapper tracingQueryWrapper = new QueryWrapper<>();
            if(StringUtils.isNotEmpty(trTplpoMainVo.getMainPoGuid())){
                tracingQueryWrapper.eq("b_bill_guid",trTplpoMainVo.getMainPoGuid());
            }
//            if(StringUtils.isNotEmpty(param.getString("bBillNo"))){
//                tracingQueryWrapper.eq("b_bill_no",param.getString("bBillNo"));
//            }
//            if(StringUtils.isNotEmpty(param.getString("cargoOwnerGuid"))){
//                tracingQueryWrapper.eq("cargo_owner_guid",param.getString("cargoOwnerGuid"));
//            }
//            if(StringUtils.isNotEmpty(param.getString("cargoOwnerName"))){
//                tracingQueryWrapper.eq("cargo_owner_name",param.getString("cargoOwnerName"));
//            }
            List<TrBbTracingEntity> list = trBbTracingMapper.selectList(tracingQueryWrapper);
            trTplpoMainVo.setTrBbTracing(list);
            trTplpoMainVos.add(trTplpoMainVo);
        }
        //封装
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("totalRows", pageInfo.getTotal());//当前满足条件总行数
        resultMap.put("totalPages", pageInfo.getPages());//分页总页数
        resultMap.put("pageSize", pageInfo.getSize());//当前分页总页数
        resultMap.put("pageNum", pageInfo.getPageNum());//当前页
        resultMap.put("data", trTplpoMainVos);
        return resultMap;
    }

    /**
     * 提货超时、到站/签收超时订单
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public PageInfo<Map<String, Object>> getTimeoutOrderSelect(JSONObject param) {
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> timeoutOrderSelect = trTplpoMainMapper.getTimeoutOrderSelect(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(timeoutOrderSelect);
        return pageInfo;
    }
}
