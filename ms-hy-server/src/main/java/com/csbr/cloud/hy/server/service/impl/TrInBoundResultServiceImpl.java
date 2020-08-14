package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.domain.dto.InBoundResultInsertDto;
import com.csbr.cloud.hy.server.entity.TrInBoundResultDetailEntity;
import com.csbr.cloud.hy.server.entity.TrInBoundResultEntity;
import com.csbr.cloud.hy.server.mapper.TrInBoundResultDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrInBoundResultMapper;
import com.csbr.cloud.hy.server.service.TrInBoundResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

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
public class TrInBoundResultServiceImpl extends ServiceImpl<TrInBoundResultMapper, TrInBoundResultEntity> implements TrInBoundResultService {

    @Autowired
    private TrInBoundResultMapper trInBoundResultMapper;
    
    //明细表
    @Autowired
    private TrInBoundResultDetailMapper trInBoundResultDetailMapper;
    
    
    /**
     * 入库历史新增
     *
     * @param inBoundResultInsertDto
     */
    @Override
    @Transactional
    public void postInBoundResultInsert(InBoundResultInsertDto inBoundResultInsertDto) {
        
        TrInBoundResultEntity trInBoundResultEntity = new TrInBoundResultEntity();
        BeanUtils.copyProperties(inBoundResultInsertDto,trInBoundResultEntity);
        trInBoundResultMapper.insert(trInBoundResultEntity);

        for (TrInBoundResultDetailEntity trInBoundResultDetailEntity : inBoundResultInsertDto.getTrInBoundResultDetail()) {
            trInBoundResultDetailMapper.insert(trInBoundResultDetailEntity);
        }
    }
}
