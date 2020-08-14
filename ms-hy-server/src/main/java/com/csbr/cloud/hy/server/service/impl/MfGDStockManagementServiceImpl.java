package com.csbr.cloud.hy.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGDStockManagementEntity;
import com.csbr.cloud.hy.server.mapper.MfGDStockManagementMapper;
import com.csbr.cloud.hy.server.service.MfGDStockManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class MfGDStockManagementServiceImpl extends ServiceImpl<MfGDStockManagementMapper, MfGDStockManagementEntity> implements MfGDStockManagementService {

    @Autowired
    private MfGDStockManagementMapper mfGDStockManagementMapper;


    /**
     * 商品库管参数配置新增修改
     *
     * @param mfGDStockManagementEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonRes postStockManagementOperation(MfGDStockManagementEntity mfGDStockManagementEntity) {

        if(StringUtils.isNotEmpty(mfGDStockManagementEntity.getGuid())){
            QueryWrapper<MfGDStockManagementEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("guid",mfGDStockManagementEntity.getGuid());
            MfGDStockManagementEntity selectOne = mfGDStockManagementMapper.selectOne(queryWrapper);
            if(selectOne != null){
                //修改
                UpdateWrapper<MfGDStockManagementEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("guid",mfGDStockManagementEntity.getGuid());
                mfGDStockManagementMapper.update(mfGDStockManagementEntity,updateWrapper);
            }else{
                mfGDStockManagementMapper.insert(mfGDStockManagementEntity);
            }
        }else{
            return CommonRes.ok("guid为空!",null);
        }

        return CommonRes.ok();
    }
}
