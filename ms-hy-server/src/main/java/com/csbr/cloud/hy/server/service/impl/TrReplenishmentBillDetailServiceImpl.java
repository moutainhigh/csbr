package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrReplenishmentBillDetailEntity;
import com.csbr.cloud.hy.server.mapper.TrReplenishmentBillDetailMapper;
import com.csbr.cloud.hy.server.service.TrReplenishmentBillDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class TrReplenishmentBillDetailServiceImpl extends ServiceImpl<TrReplenishmentBillDetailMapper, TrReplenishmentBillDetailEntity> implements TrReplenishmentBillDetailService {

    @Autowired
    private TrReplenishmentBillDetailMapper trReplenishmentBillDetailMapper;

    /**
     * 补货单明细查询
     *
     * @param param
     */
    @Override
    public CommonRes getReplenishmentBillDetailSelect(JSONObject param) {
        QueryWrapper<TrReplenishmentBillDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("guid",param.getString("guid"));
        if(StringUtils.isNotEmpty(param.getString("rowNo"))){
            queryWrapper.eq("row_no",param.getString("rowNo"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsGuid"))){
            queryWrapper.eq("goods_guid",param.getString("goodsGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
            queryWrapper.eq("goods_code",param.getString("goodsCode"));
        }
        Page<TrReplenishmentBillDetailEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<TrReplenishmentBillDetailEntity> mapIPage = trReplenishmentBillDetailMapper.selectPage(page, queryWrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }
}
