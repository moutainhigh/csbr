package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.*;
import com.csbr.cloud.hy.server.mapper.*;
import com.csbr.cloud.hy.server.service.TrReplenishmentBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
public class TrReplenishmentBillServiceImpl extends ServiceImpl<TrReplenishmentBillMapper, TrReplenishmentBillEntity> implements TrReplenishmentBillService {

    //补货单表
    @Autowired
    private TrReplenishmentBillMapper trReplenishmentBillMapper;

    //仓库资料表
    @Autowired
    private MfWarehouseMapper mfWarehouseMapper;

    //商品库管参数配置表
    @Autowired
    private MfGDStockManagementMapper mfGDStockManagementMapper;

    @Autowired
    private TrOutBoundResultMapper trOutBoundResultMapper;

    //WMS商品库存表
    @Autowired
    private InvLotStockWmsMapper invLotStockWmsMapper;

    @Autowired
    private TrTplpoMainMapper trTplpoMainMapper;

    @Autowired
    private TrTplbbDetailMapper trTplbbDetailMapper;

    @Autowired
    private TrPlanSalesMapper trPlanSalesMapper;

    //商品资料表
    @Autowired
    private MfGoodsMapper mfGoodsMapper;

    //补货单明细表
    @Autowired
    private TrReplenishmentBillDetailMapper trReplenishmentBillDetailMapper;


    /**
     * 补货单表查询
     *
     * @param param
     */
    @Override
    public CommonRes getReplenishmentBillSelect(JSONObject param) {

        QueryWrapper<TrReplenishmentBillEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("transferWHName"))){
            queryWrapper.eq("transfer_w_h_name",param.getString("transferWHName"));
        }
        if(StringUtils.isNotEmpty(param.getString("receivingAddress"))){
            queryWrapper.eq("receiving_address",param.getString("receivingAddress"));
        }
        if(StringUtils.isNotEmpty(param.getString("state"))){
            queryWrapper.eq("state",param.getString("state"));
        }
        if(StringUtils.isNotEmpty(param.getString("startTime"))){
            queryWrapper.ge("bill_date",param.getString("startTime"));
        }
        if(StringUtils.isNotEmpty(param.getString("endTime"))){
            queryWrapper.le("bill_date",param.getString("endTime"));
        }
        Page<TrReplenishmentBillEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrReplenishmentBillEntity> mapIPage = trReplenishmentBillMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }

    /**
     * 补货单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void trReplenishmentBillJob() {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        Date now = new Date();
        String time = sdf.format(now);
        //查询仓库资料表
        List<MfWarehouseEntity> mfWarehouseEntities = mfWarehouseMapper.selectList(new QueryWrapper<>());
        //补货单(主表) list
        List<TrReplenishmentBillEntity> trReplenishmentBillEntities = new ArrayList<>();
        //补货单明细表
        List<TrReplenishmentBillDetailEntity> trReplenishmentBillDetailEntities = new ArrayList<>();
        //循环查询商品库管参数配置表
        for (MfWarehouseEntity mfWarehouseEntity:mfWarehouseEntities) {
            //补货单主表
            TrReplenishmentBillEntity trReplenishmentBillEntity = new TrReplenishmentBillEntity();
            trReplenishmentBillEntity.setGuid(String.valueOf(UUID.randomUUID()).replace("-", ""));
            trReplenishmentBillEntity.setTenantGuid(mfWarehouseEntity.getTenantGuid());
            trReplenishmentBillEntity.setCorpGuid(mfWarehouseEntity.getCorpGuid());
            DecimalFormat countFormat = new DecimalFormat("0000");
            trReplenishmentBillEntity.setBBillNo("BHD_"+time+countFormat.format(Integer.parseInt("0000")+1));
            trReplenishmentBillEntity.setBillDate(new Date());
            trReplenishmentBillEntity.setTransferWHGuid(mfWarehouseEntity.getGuid());
            trReplenishmentBillEntity.setTransferWHName(mfWarehouseEntity.getChineseName());
            trReplenishmentBillEntity.setReceivingAddress(mfWarehouseEntity.getAddress());
            //自动产生
            trReplenishmentBillEntity.setRequisitionMode("1");
            trReplenishmentBillEntity.setIsCcbb("N");
            trReplenishmentBillEntity.setIsNeedAudit("N");
            trReplenishmentBillEntity.setState("W");
            trReplenishmentBillEntity.setUrgency("1");
            trReplenishmentBillEntity.setReplenishmentType("1");
            //主表补货数量(按建议补货量(按日均)汇总)
            BigDecimal totalQty = new BigDecimal(0);
            QueryWrapper<MfGDStockManagementEntity> mfGDStockManagementEntityQueryWrapper = new QueryWrapper<>();
            //仓库guid
            mfGDStockManagementEntityQueryWrapper.eq("warehouse_guid",mfWarehouseEntity.getGuid());
            List<MfGDStockManagementEntity> mfGDStockManagementEntities = mfGDStockManagementMapper.selectList(mfGDStockManagementEntityQueryWrapper);
            for (MfGDStockManagementEntity mfGDStockManagementEntity:mfGDStockManagementEntities) {
                //补货单明细表
                TrReplenishmentBillDetailEntity trReplenishmentBillDetailEntity = new TrReplenishmentBillDetailEntity();
                //库存周转合理天数
                mfGDStockManagementEntity.getRinvtod();
                //到货准备天数
                BigDecimal arrivalReadyDays = new BigDecimal(mfGDStockManagementEntity.getArrivalReadyDays().toString());
                //计划销量备货系数
                mfGDStockManagementEntity.getPlanStockupRatio();
                //备货期天数=库存周转合理天数 + 到货准备天数
                BigDecimal leadTime = mfGDStockManagementEntity.getRinvtod().add(arrivalReadyDays);

                //查询近3天的日均销量
                Map<String,Object> param = new HashMap<>();
                param.put("queryDay",3);
                param.put("goodsGuid",mfGDStockManagementEntity.getGoodGuid());
                param.put("warehouseGuid",mfGDStockManagementEntity.getWarehouseGuid());
                Map<String, Object> wmsDms3 = trOutBoundResultMapper.getWmsDms(param);
                BigDecimal wmsDms3Qty = new BigDecimal(wmsDms3.get("qty").toString()).divide(new BigDecimal(3));
                //查询近7天
                param.put("queryDay",7);
                Map<String, Object> wmsDms7 = trOutBoundResultMapper.getWmsDms(param);
                BigDecimal wmsDms7Qty = new BigDecimal(wmsDms7.get("qty").toString()).divide(new BigDecimal(7));
                //查询近14天
                param.put("queryDay",14);
                Map<String, Object> wmsDms14 = trOutBoundResultMapper.getWmsDms(param);
                BigDecimal wmsDms14Qty = new BigDecimal(wmsDms14.get("qty").toString()).divide(new BigDecimal(14));
                //查询近30天
                param.put("queryDay",30);
                Map<String, Object> wmsDms30 = trOutBoundResultMapper.getWmsDms(param);
                BigDecimal wmsDms30Qty = new BigDecimal(wmsDms30.get("qty").toString()).divide(new BigDecimal(30));
                BigDecimal add = wmsDms3Qty.add(wmsDms7Qty).add(wmsDms14Qty).add(wmsDms30Qty);
                //查询本月已销售量
                param.put("queryDay",0);
                Map<String, Object> wmsDms0 = trOutBoundResultMapper.getWmsDms(param);
                BigDecimal wmsDms0Qty = new BigDecimal(wmsDms0.get("qty").toString());

                //日均销量
                BigDecimal wmsDmsQty = add.divide(new BigDecimal(4));

                //备货量(按日均) = 备货期天数 * 日均销量 * 备货系数
                BigDecimal stockUp = leadTime.multiply(wmsDmsQty).multiply(mfGDStockManagementEntity.getPlanStockupRatio());

                //库存量计算
                QueryWrapper<InvLotStockWmsEntity> invLotStockWmsEntityQueryWrapper = new QueryWrapper<>();
                invLotStockWmsEntityQueryWrapper.eq("warehouse_guid",mfGDStockManagementEntity.getWarehouseGuid());
                invLotStockWmsEntityQueryWrapper.eq("goods_guid",mfGDStockManagementEntity.getGoodGuid());
                List<InvLotStockWmsEntity> invLotStockWmsEntityList = invLotStockWmsMapper.selectList(invLotStockWmsEntityQueryWrapper);
                //当前库存
                BigDecimal stockCount = new BigDecimal(0);
                for (int i = 0; i < invLotStockWmsEntityList.size() ; i++) {
                    stockCount.add(invLotStockWmsEntityList.get(i).getStockQty());
                }
                //在途库存计算
                //在途库存
                BigDecimal inventoryStock = new BigDecimal(0);
                JSONObject params = new JSONObject();
                params.put("warehouseGuid",mfGDStockManagementEntity.getWarehouseGuid());
                List<Map<String, Object>> poMainAndBbList = trTplpoMainMapper.getPoMainAndBbList(params);
                //过滤状态为81的
                for (Map<String, Object> map:poMainAndBbList) {
                    QueryWrapper<TrTplbbDetailEntity> trTplbbDetailEntityQueryWrapper = new QueryWrapper<>();
                    trTplbbDetailEntityQueryWrapper.eq("guid",map.get("bbGuid").toString());
                    List<TrTplbbDetailEntity> trTplbbDetailEntities = trTplbbDetailMapper.selectList(trTplbbDetailEntityQueryWrapper);
                    for (int i = 0; i <trTplbbDetailEntities.size() ; i++) {
                        inventoryStock.add(trTplbbDetailEntities.get(i).getQty());
                    }
                }
                //库存量 = 当前库存 + 在途库存
                BigDecimal inventory = new BigDecimal(0);
                inventory = stockCount.add(inventoryStock);
                //建议补货量(按日均) = 备货量 - 库存量
                BigDecimal recommendedReplenishmentDay = stockUp.subtract(inventory);
                //主表的建议补货量
                totalQty.add(recommendedReplenishmentDay);
                //建议补货量(按计划)
                //计划销量
                //本月
                //获取当前年份月份
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("warehouseGuid",mfGDStockManagementEntity.getWarehouseGuid());
                jsonObject.put("goodsGuid",mfGDStockManagementEntity.getGoodGuid());
                jsonObject.put("planYear",year);
                List<Map<String, Object>> salesAccuracys = trPlanSalesMapper.getSalesAccuracy(jsonObject);
                //计划销售 = 本月计划销量 + 下月计划销量 - 本月已销售量
                BigDecimal plannedSales = new BigDecimal(0);
                    switch(month){
                        case 1 :
                            //本月计划销量 + 下月计划销量 - 本月已销售量
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("jan").toString()).add(new BigDecimal(salesAccuracys.get(0).get("feb").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jan").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("feb").toString()));
                            break;
                        case 2 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("feb").toString()).add(new BigDecimal(salesAccuracys.get(0).get("mar").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("feb").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("mar").toString()));
                            break;
                        case 3 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("mar").toString()).add(new BigDecimal(salesAccuracys.get(0).get("apr").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("mar").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("apr").toString()));
                            break;
                        case 4 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("apr").toString()).add(new BigDecimal(salesAccuracys.get(0).get("may").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("apr").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("may").toString()));
                            break;
                        case 5 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("may").toString()).add(new BigDecimal(salesAccuracys.get(0).get("jun").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("may").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jun").toString()));
                            break;
                        case 6 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("jun").toString()).add(new BigDecimal(salesAccuracys.get(0).get("jul").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jun").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jul").toString()));
                            break;
                        case 7 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("jul").toString()).add(new BigDecimal(salesAccuracys.get(0).get("aug").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jul").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("aug").toString()));
                            break;
                        case 8 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("aug").toString()).add(new BigDecimal(salesAccuracys.get(0).get("sept").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("aug").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("sept").toString()));
                            break;
                        case 9 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("sept").toString()).add(new BigDecimal(salesAccuracys.get(0).get("oct").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("sept").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("oct").toString()));
                            break;
                        case 10 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("oct").toString()).add(new BigDecimal(salesAccuracys.get(0).get("nov").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("oct").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("nov").toString()));
                            break;
                        case 11 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("nov").toString()).add(new BigDecimal(salesAccuracys.get(0).get("dec").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("nov").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("dec").toString()));
                            break;
                        case 12 :
                            plannedSales = new BigDecimal(salesAccuracys.get(0).get("dec").toString()).add(new BigDecimal(salesAccuracys.get(0).get("jan").toString())).subtract(wmsDms0Qty);
                            //本月计划销量
                            trReplenishmentBillDetailEntity.setThisMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("dec").toString()));
                            //下月计划销量
                            trReplenishmentBillDetailEntity.setNextMonthPlannedSales(new BigDecimal(salesAccuracys.get(0).get("jan").toString()));
                            break;
                        default :
                            //语句
                    }
                //备货量(按计划销量) = 计划销量 * 备货系数
                BigDecimal stockUpPlan = plannedSales.multiply(mfGDStockManagementEntity.getPlanStockupRatio());
                //建议补货量(按计划) = 备货量(按计划销量) - 库存量
                BigDecimal recommendedReplenishmentPlan = stockUpPlan.subtract(inventory);

                trReplenishmentBillDetailEntity.setGuid(trReplenishmentBillEntity.getGuid());
                trReplenishmentBillDetailEntity.setGoodsGuid(mfGDStockManagementEntity.getGoodGuid());
                QueryWrapper<MfGoodsEntity> mfGoodsEntityQueryWrapper = new QueryWrapper<>();
                mfGoodsEntityQueryWrapper.eq("guid",mfGDStockManagementEntity.getGoodGuid());
                MfGoodsEntity mfGoodsEntity = mfGoodsMapper.selectOne(mfGoodsEntityQueryWrapper);
                if(mfGoodsEntity != null){
                    trReplenishmentBillDetailEntity.setGoodsCode(mfGoodsEntity.getGoodsCode());
                    trReplenishmentBillDetailEntity.setGoodsName(mfGoodsEntity.getGoodsName());
                    trReplenishmentBillDetailEntity.setGoodsSpec(mfGoodsEntity.getGoodsSpec());
                    trReplenishmentBillDetailEntity.setGoodsType(mfGoodsEntity.getGoodsType());
                    trReplenishmentBillDetailEntity.setRegistKey(mfGoodsEntity.getRegistKey());
                    trReplenishmentBillDetailEntity.setProducer(mfGoodsEntity.getProducer());
                }
                trReplenishmentBillDetailEntity.setUnit(salesAccuracys.get(0).get("unit").toString());
                trReplenishmentBillDetailEntity.setStockQty(stockCount);
                trReplenishmentBillDetailEntity.setInventoryCap(mfGDStockManagementEntity.getInventoryCap());
                trReplenishmentBillDetailEntity.setInventoryLimit(mfGDStockManagementEntity.getInventoryLimit());
                //本月出库量
                trReplenishmentBillDetailEntity.setDeliveryGoods(wmsDms0Qty);
                //建议补货数量（按计划销量）
                trReplenishmentBillDetailEntity.setReplenishmentQtyPlan(recommendedReplenishmentPlan);
                //建议补货数量（按日均销量）
                trReplenishmentBillDetailEntity.setReplenishmentQtyDaySales(recommendedReplenishmentDay);
                //添加补货单明细表集合
                trReplenishmentBillDetailEntities.add(trReplenishmentBillDetailEntity);
            }
            //品规数
            trReplenishmentBillEntity.setGoodsSpecNum(mfGDStockManagementEntities.size());
            //主表建议补货数量
            trReplenishmentBillEntity.setTotalQty(totalQty);
            trReplenishmentBillEntities.add(trReplenishmentBillEntity);
        }

        //添加补货单主表
        for (TrReplenishmentBillEntity trReplenishmentBillEntitie: trReplenishmentBillEntities) {
            trReplenishmentBillMapper.insert(trReplenishmentBillEntitie);
        }

        //添加补货单明细表
        for (TrReplenishmentBillDetailEntity trReplenishmentBillDetailEntitie:trReplenishmentBillDetailEntities) {
            trReplenishmentBillDetailMapper.insert(trReplenishmentBillDetailEntitie);
        }
    }
}
