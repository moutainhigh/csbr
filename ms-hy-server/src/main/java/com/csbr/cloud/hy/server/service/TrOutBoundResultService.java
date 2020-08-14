package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.hy.server.domain.dto.OutBoundResultInsertDto;
import com.csbr.cloud.hy.server.entity.TrOutBoundResultEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface TrOutBoundResultService extends IService<TrOutBoundResultEntity> {


    /**
     * 出库历史新增
     */
    void postOutBoundResultInsert(OutBoundResultInsertDto outBoundResultInsertDto);
}
