package com.csbr.cloud.hy.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csbr.cloud.hy.server.entity.InvLotStockSapEntity;
import com.csbr.cloud.hy.server.entity.InvLotStockSapHistoryEntity;
import com.csbr.cloud.hy.server.mapper.InvLotStockSapHistoryMapper;
import com.csbr.cloud.hy.server.mapper.InvLotStockSapMapper;
import com.csbr.cloud.hy.server.service.InvLotStockSapHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class InvLotStockSapHistoryServiceImpl extends ServiceImpl<InvLotStockSapHistoryMapper, InvLotStockSapHistoryEntity> implements InvLotStockSapHistoryService {


    @Autowired
    private InvLotStockSapMapper invLotStockSapMapper;

    @Autowired
    private InvLotStockSapHistoryMapper invLotStockSapHistoryMapper;

    /**
     * SAP商品历史库存
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invLotStockSapHistoryJob() {
        QueryWrapper<InvLotStockSapEntity> queryWrapper = new QueryWrapper();
        queryWrapper.apply("create_time = date_sub(curdate(),interval 1 day)");
        List<InvLotStockSapEntity> invLotStockSapEntityList = invLotStockSapMapper.selectList(queryWrapper);
        for (InvLotStockSapEntity invLotStockSapEntity:invLotStockSapEntityList) {
            QueryWrapper<InvLotStockSapHistoryEntity> invLotStockSapHistoryEntityQueryWrapper = new QueryWrapper<>();
            invLotStockSapHistoryEntityQueryWrapper.eq("guid",invLotStockSapEntity.getGuid());
            InvLotStockSapHistoryEntity invLotStockSapHistoryEntity = invLotStockSapHistoryMapper.selectOne(invLotStockSapHistoryEntityQueryWrapper);
            if(invLotStockSapHistoryEntity == null){
                BeanUtils.copyProperties(invLotStockSapEntity,invLotStockSapHistoryEntity);
                invLotStockSapHistoryMapper.insert(invLotStockSapHistoryEntity);
            }
        }
    }
}
