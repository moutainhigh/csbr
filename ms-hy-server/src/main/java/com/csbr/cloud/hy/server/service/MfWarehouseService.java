package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfWarehouseEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  仓库资料服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
public interface MfWarehouseService extends IService<MfWarehouseEntity> {


    /**
     * 仓库资料新增修改
     */
    CommonRes postWareHouseOperation(MfWarehouseEntity mfWarehouseEntity);

    /**
     * 查询仓库资料信息
     */
    CommonRes getWareHouseSelect(JSONObject param);

}
