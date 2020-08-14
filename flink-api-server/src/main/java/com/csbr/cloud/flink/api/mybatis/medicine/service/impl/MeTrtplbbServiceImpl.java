package com.csbr.cloud.flink.api.mybatis.medicine.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.csbr.cloud.flink.api.model.response.visualdata.FlinkSupportMeCheckTrtplbbRes;
import com.csbr.cloud.flink.api.mybatis.medicine.entity.MeTrtplbb;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.MeTrtplbbMapper;
import com.csbr.cloud.flink.api.mybatis.medicine.mapper.so.MeTrtplbbSO;
import com.csbr.cloud.flink.api.mybatis.medicine.service.MeTrtplbbService;
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
@DS("medicine")
public class MeTrtplbbServiceImpl implements MeTrtplbbService {

    /**
     * 物流业务单据汇总数据持久化对象
     */
    @Autowired
    private MeTrtplbbMapper meTrtplbbMapper;


    @Override
    public List<MeTrtplbb> getList(MeTrtplbbSO so) {
        return this.meTrtplbbMapper.searchMeTrtplbbs(so);
    }

    @Override
    public FlinkSupportMeCheckTrtplbbRes checkTrtplbb(MeTrtplbbSO so) {
        List<MeTrtplbb> trtplbbList = meTrtplbbMapper.searchMeTrtplbbs(so);

        FlinkSupportMeCheckTrtplbbRes res = new FlinkSupportMeCheckTrtplbbRes();

        if (trtplbbList.size() > 0) {
            res.setCheckTrtplbb(true);
            res.setMeTrtplbb(trtplbbList.get(0));
        }

        return res;
    }

    @Override
    public Boolean checkTrtplbbMedOrder(MeTrtplbbSO so) {
        so.setIsMedOrder("Y");
        List<MeTrtplbb> trtplbbList = meTrtplbbMapper.searchMeTrtplbbs(so);

        return trtplbbList.size() > 0 ? true : false;
    }


}