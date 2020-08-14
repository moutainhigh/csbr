package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsEntity;
import com.csbr.cloud.hy.server.entity.InvLotStockWmsHistoryEntity;
import com.csbr.cloud.hy.server.mapper.InvLotStockWmsHistoryMapper;
import com.csbr.cloud.hy.server.mapper.InvLotStockWmsMapper;
import com.csbr.cloud.hy.server.service.InvLotStockWmsHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class InvLotStockWmsHistoryServiceImpl extends ServiceImpl<InvLotStockWmsHistoryMapper, InvLotStockWmsHistoryEntity> implements InvLotStockWmsHistoryService {

    @Autowired
    InvLotStockWmsHistoryMapper invLotStockWmsHistoryMapper;

    @Autowired
    InvLotStockWmsMapper invLotStockWmsMapper;

    /**
     * 根据仓库汇总查询库存
     *
     * @param param
     */
    @Override
    public PageInfo<Map<String, Object>> getLotStockWms(JSONObject param) {
        PageHelper.startPage(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        List<Map<String, Object>> lotStockWms = invLotStockWmsHistoryMapper.getLotStockWms(param);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(lotStockWms);
        return pageInfo;
    }

    /**
     * 查询仓库库存明细
     *
     * @param param
     */
    @Override
    public CommonRes getLotStockWmsHistory(JSONObject param) {
        QueryWrapper<InvLotStockWmsHistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        queryWrapper.eq("warehouse_guid",param.getString("warehouseGuid"));
        queryWrapper.eq("goods_code",param.getString("goodsCode"));
        queryWrapper.eq("goods_name",param.getString("goodsName"));
        queryWrapper.eq("manufacturer",param.getString("manufacturer"));
        queryWrapper.eq("lot",param.getString("lot"));
        queryWrapper.eq("storage_date",param.getString("storageDate"));
        //设置分页
        Page<InvLotStockWmsHistoryEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<InvLotStockWmsHistoryEntity> mapIPage = invLotStockWmsHistoryMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }

    /**
     * 根据商品汇总查询库存
     *
     * @param param
     */
    @Override
    public CommonRes getWmsStockByGoods(JSONObject param) {
        QueryWrapper<InvLotStockWmsHistoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        queryWrapper.eq("warehouse_guid",param.getString("warehouseGuid"));
        queryWrapper.eq("goods_code",param.getString("goodsCode"));
        queryWrapper.eq("goods_name",param.getString("goodsName"));
        queryWrapper.eq("manufacturer",param.getString("manufacturer"));
        queryWrapper.eq("create_time",param.getString("createTime"));
        //设置分页
        Page<InvLotStockWmsHistoryEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<InvLotStockWmsHistoryEntity> mapIPage = invLotStockWmsHistoryMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }

    /**
     * WMS商品历史库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invLotStockWmsHistoryJob() {
        QueryWrapper<InvLotStockWmsEntity> queryWrapper = new QueryWrapper();
        queryWrapper.apply("create_time = date_sub(curdate(),interval 1 day)");
        List<InvLotStockWmsEntity> invLotStockWmsEntityList = invLotStockWmsMapper.selectList(queryWrapper);
        for (InvLotStockWmsEntity invLotStockWmsEntity:invLotStockWmsEntityList) {
            QueryWrapper<InvLotStockWmsHistoryEntity> invLotStockWmsHistoryEntityQueryWrapper = new QueryWrapper<>();
            invLotStockWmsHistoryEntityQueryWrapper.eq("guid",invLotStockWmsEntity.getGuid());
            InvLotStockWmsHistoryEntity invLotStockWmsHistoryEntity = invLotStockWmsHistoryMapper.selectOne(invLotStockWmsHistoryEntityQueryWrapper);
            if(invLotStockWmsHistoryEntity == null){
                BeanUtils.copyProperties(invLotStockWmsEntity,invLotStockWmsHistoryEntity);
                invLotStockWmsHistoryMapper.insert(invLotStockWmsHistoryEntity);
            }
        }
    }
}
