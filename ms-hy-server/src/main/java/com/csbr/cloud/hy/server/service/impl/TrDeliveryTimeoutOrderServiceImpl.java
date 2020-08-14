package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.entity.TrDeliveryTimeoutOrderEntity;
import com.csbr.cloud.hy.server.mapper.TrDeliveryTimeoutOrderMapper;
import com.csbr.cloud.hy.server.service.TrDeliveryTimeoutOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author L-ZP
 * @since 2020-04-27
 */
@Service
@Transactional
public class TrDeliveryTimeoutOrderServiceImpl extends ServiceImpl<TrDeliveryTimeoutOrderMapper, TrDeliveryTimeoutOrderEntity> implements TrDeliveryTimeoutOrderService {

}
