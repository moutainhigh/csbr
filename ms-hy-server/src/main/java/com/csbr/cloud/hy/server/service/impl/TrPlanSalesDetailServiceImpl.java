package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.entity.TrPlanSalesDetailEntity;
import com.csbr.cloud.hy.server.mapper.TrPlanSalesDetailMapper;
import com.csbr.cloud.hy.server.service.TrPlanSalesDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangheng
 * @since 2020-05-08
 */
@Service
@Transactional
public class TrPlanSalesDetailServiceImpl extends ServiceImpl<TrPlanSalesDetailMapper, TrPlanSalesDetailEntity> implements TrPlanSalesDetailService {

}
