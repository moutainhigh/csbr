package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfWarehouseEntity;
import com.csbr.cloud.hy.server.mapper.MfWarehouseMapper;
import com.csbr.cloud.hy.server.service.MfWarehouseService;
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
public class MfWarehouseServiceImpl extends ServiceImpl<MfWarehouseMapper, MfWarehouseEntity> implements MfWarehouseService {

    @Autowired
    private MfWarehouseMapper mfWarehouseMapper;


    /**
     * 仓库资料新增修改
     *
     * @param mfWarehouseEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes postWareHouseOperation(MfWarehouseEntity mfWarehouseEntity) {
        //对仓库资料的新增和修改
        if(StringUtils.isNotEmpty(mfWarehouseEntity.getGuid())){
            QueryWrapper<MfWarehouseEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("guid", mfWarehouseEntity.getGuid());
            MfWarehouseEntity selectOne = mfWarehouseMapper.selectOne(queryWrapper);
            if(selectOne != null){
                //修改
                UpdateWrapper<MfWarehouseEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("guid",mfWarehouseEntity.getGuid());
                int update = mfWarehouseMapper.update(mfWarehouseEntity, updateWrapper);
                if(update > 0){
                    return CommonRes.ok("修改sucess",null);
                }
            }else{
                //新增
                //新增
                int insert = mfWarehouseMapper.insert(mfWarehouseEntity);
                if(insert > 0){
                    return CommonRes.ok("新增sucess",null);
                }
            }
        }

        return CommonRes.ok("guid为空!",null);
    }

    /**
     * 查询仓库资料信息
     *
     * @param param
     */
    @Override
    @Transactional(readOnly = true)//只读事务 防止查询加锁
    public CommonRes getWareHouseSelect(JSONObject param) {
        QueryWrapper<MfWarehouseEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("tenant_guid",param.getString("tenantGuid"));
        if(StringUtils.isNotEmpty(param.getString("guid"))){
            wrapper.eq("guid",param.getString("guid"));
        }
        if(StringUtils.isNotEmpty(param.getString("chineseName"))){
            wrapper.eq("chinese_name",param.getString("chineseName"));
        }
        //设置分页
        Page<MfWarehouseEntity> page = new Page<>(param.getInteger("pageIndex"), param.getInteger("pageSize"));
        IPage<MfWarehouseEntity> mapIPage = mfWarehouseMapper.selectPage(page, wrapper);
        PageUtils pageUtils = new PageUtils();
        return CommonRes.ok(pageUtils.build(mapIPage));
    }
}
