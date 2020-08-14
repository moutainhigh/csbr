package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.hy.server.domain.dto.InBoundResultInsertDto;
import com.csbr.cloud.hy.server.entity.TrInBoundResultEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface TrInBoundResultService extends IService<TrInBoundResultEntity> {

    /**
     *  入库历史新增
     */
    void postInBoundResultInsert(InBoundResultInsertDto inBoundResultInsertDto);
}
