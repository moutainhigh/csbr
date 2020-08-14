package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.domain.dto.OutBoundResultInsertDto;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultDetailEntity;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultEntity;
import com.csbr.cloud.hy.server.mapper.TrOutBoundResultDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrOutBoundResultMapper;
import com.csbr.cloud.hy.server.service.TrOutBoundResultService;
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
public class TrOutBoundResultServiceImpl extends ServiceImpl<TrOutBoundResultMapper, TrOutBoundResultEntity> implements TrOutBoundResultService {

    @Autowired
    private TrOutBoundResultMapper trOutBoundResultMapper;

    //明细表
    @Autowired
    private TrOutBoundResultDetailMapper trOutBoundResultDetailMapper;

    /**
     * 出库历史新增
     *
     * @param outBoundResultInsertDto
     */
    @Override
    @Transactional
    public void postOutBoundResultInsert(OutBoundResultInsertDto outBoundResultInsertDto) {
        TrOutBoundResultEntity trOutBoundResultEntity = new TrOutBoundResultEntity();
        BeanUtils.copyProperties(outBoundResultInsertDto,trOutBoundResultEntity);
        trOutBoundResultMapper.insert(trOutBoundResultEntity);

        for (TrOutBoundResultDetailEntity trOutBoundResultDetailEntity:outBoundResultInsertDto.getTrOutBoundResultDetail()) {
            trOutBoundResultDetailMapper.insert(trOutBoundResultDetailEntity);
        }
    }
}
