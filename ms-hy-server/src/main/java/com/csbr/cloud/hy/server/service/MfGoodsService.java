package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.entity.MfGoodsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.csbr.cloud.hy.server.mapper.MfGoodsMapper;
import com.csbr.cloud.hy.server.util.BusinessData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  商品资料服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface MfGoodsService extends IService<MfGoodsEntity> {

    /**
     * 对商品的新增修改
     */
    CommonRes postGoodsOperation(MfGoodsEntity mfGoodsEntity);

    /**
     * 查询商品信息
     */
    CommonRes getGoodsSelect(JSONObject param);
}
