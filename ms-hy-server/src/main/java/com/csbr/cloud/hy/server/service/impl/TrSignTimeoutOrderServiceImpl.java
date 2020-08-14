package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.entity.TrSignTimeoutOrderEntity;
import com.csbr.cloud.hy.server.mapper.TrSignTimeoutOrderMapper;
import com.csbr.cloud.hy.server.service.TrSignTimeoutOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Service
@Transactional
public class TrSignTimeoutOrderServiceImpl extends ServiceImpl<TrSignTimeoutOrderMapper, TrSignTimeoutOrderEntity> implements TrSignTimeoutOrderService {

}
