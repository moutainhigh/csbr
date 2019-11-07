package com.csbr.cloud.orderlogistics.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.orderlogistics.entity.DispatchOrderDetail;
import com.csbr.cloud.orderlogistics.mapper.DispatchOrderDetailMapper;
import com.csbr.cloud.orderlogistics.service.DispatchOrderDetailService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangheng
 * @date 2019/11/6 16:41
 */
@Slf4j
@Service
public class DispatchOrderDetailImpl implements DispatchOrderDetailService {

    @Autowired
    private DispatchOrderDetailMapper dispatchOrderDetailMapper;

    @GlobalTransactional
    @DS("master")
    @Override
    public List<DispatchOrderDetail> getDispatchOrderDetailList(){

       return dispatchOrderDetailMapper.getDispatchOrderDetailList();
    }

}
