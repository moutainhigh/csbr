package com.csbr.cloud.flink.api.mybatis.transport.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportTrCheckTrtplbbRes;
import com.csbr.cloud.flink.api.mybatis.transport.entity.TrTrtplbb;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.TrTrtplbbMapper;
import com.csbr.cloud.flink.api.mybatis.transport.mapper.so.TrTrtplbbSO;
import com.csbr.cloud.flink.api.mybatis.transport.service.TrTrtplbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流业务单据汇总业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-14
 */
@Service
@DS("transport")
public class TrTrtplbbServiceImpl implements TrTrtplbbService {

    /**
     * 物流业务单据汇总数据持久化对象
     */
    @Autowired
    private TrTrtplbbMapper trTrtplbbMapper;

    @Override
    public List<TrTrtplbb> getList(TrTrtplbbSO so) {
        return this.trTrtplbbMapper.searchTrTrtplbbs(so);
    }

    @Override
    public FlinkSupportTrCheckTrtplbbRes checkTrtplbb(TrTrtplbbSO so) {
        List<TrTrtplbb> trtplbbList = trTrtplbbMapper.searchTrTrtplbbs(so);

        FlinkSupportTrCheckTrtplbbRes res = new FlinkSupportTrCheckTrtplbbRes();

        if (trtplbbList.size() > 0) {
            res.setCheckTrtplbb(true);
            res.setTrTrtplbb(trtplbbList.get(0));
        }

        return res;
    }

    @Override
    public Boolean checkTrtplbbMedOrder(TrTrtplbbSO so) {
        so.setIsMedOrder("Y");
        List<TrTrtplbb> trtplbbList = trTrtplbbMapper.searchTrTrtplbbs(so);

        return trtplbbList.size() > 0 ? true : false;
    }

}