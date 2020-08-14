package com.csbr.cloud.hy.server.service.impl;

import com.csbr.cloud.hy.server.entity.TlTransExcepRecordEntity;
import com.csbr.cloud.hy.server.mapper.TlTransExcepRecordMapper;
import com.csbr.cloud.hy.server.service.TlTransExcepRecordService;
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
public class TlTransExcepRecordServiceImpl extends ServiceImpl<TlTransExcepRecordMapper, TlTransExcepRecordEntity> implements TlTransExcepRecordService {

}
