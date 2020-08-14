package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.TrBbTracingEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface TrBbTracingService extends IService<TrBbTracingEntity> {

    /**
     * 新增物流执行跟踪
     */
    CommonRes postBBTracingInsert(TrBbTracingEntity trBbTracingEntity);

}
