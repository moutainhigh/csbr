package com.csbr.cloud.hy.server.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGDStockManagementEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface MfGDStockManagementService extends IService<MfGDStockManagementEntity> {

    /**
     * 商品库管参数配置新增修改
     */
    CommonRes postStockManagementOperation(MfGDStockManagementEntity mfGDStockManagementEntity);

}
