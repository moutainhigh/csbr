package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGoodsEntity;
import com.csbr.cloud.hy.server.mapper.MfGoodsMapper;
import com.csbr.cloud.hy.server.service.MfGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csbr.cloud.hy.server.util.BusinessData;
import com.csbr.cloud.hy.server.util.PageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
public class MfGoodsServiceImpl extends ServiceImpl<MfGoodsMapper, MfGoodsEntity> implements MfGoodsService {

    @Autowired
    private MfGoodsMapper mfGoodsMapper;

    /**
     * 对商品的新增修改
     *
     * @param mfGoodsEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes postGoodsOperation(MfGoodsEntity mfGoodsEntity) {
        //对商品的增删和修改
        if(StringUtils.isNotEmpty(mfGoodsEntity.getGuid())){
            QueryWrapper<MfGoodsEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("guid", mfGoodsEntity.getGuid());
            MfGoodsEntity selectOne = mfGoodsMapper.selectOne(queryWrapper);
            if(selectOne != null){
                //修改
                UpdateWrapper<MfGoodsEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("guid",mfGoodsEntity.getGuid());
                int update = mfGoodsMapper.update(mfGoodsEntity, updateWrapper);
                if(update > 0){
                    return CommonRes.ok("修改sucess",null);
                }
            }else{
                //新增
                int insert = mfGoodsMapper.insert(mfGoodsEntity);
                if(insert > 0){
                    return CommonRes.ok("新增sucess",null);
                }
            }
        }
        return CommonRes.ok("新增或修改fail",null);
    }

    /**
     * 查询商品信息
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public CommonRes getGoodsSelect(JSONObject param) {
        QueryWrapper<MfGoodsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("guid"))){
            wrapper.eq("guid",param.getString("guid"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
            wrapper.eq("goods_code",param.getString("goodsCode"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsName"))){
            wrapper.eq("goods_name",param.getString("goodsName"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsSpec"))){
            wrapper.eq("goods_spec",param.getString("goodsSpec"));
        }
        if(StringUtils.isNotEmpty(param.getString("helpCode"))){
            wrapper.eq("help_code",param.getString("helpCode"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerGuid"))){
            wrapper.eq("cargo_owner_guid",param.getString("cargoOwnerGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("cargoOwnerName"))){
            wrapper.eq("cargo_owner_name",param.getString("cargoOwnerName"));
        }
        //设置分页
        Page<MfGoodsEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<MfGoodsEntity> mapIPage = mfGoodsMapper.selectPage(page, wrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }
}
