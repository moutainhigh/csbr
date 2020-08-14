package com.csbr.cloud.flink.api.mybatis.basedata.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.csbr.cloud.flink.api.mybatis.basedata.entity.MfCustomerInfo;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.MfCustomerInfoMapper;
import com.csbr.cloud.flink.api.mybatis.basedata.mapper.so.MfCustomerInfoSO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.MfCustomerInfoService;

/**
 * 业务逻辑.
 *
 * @author Huanglh
 * @since 2020-01-10
 */
@Service
@DS("base")
public class MfCustomerInfoServiceImpl implements MfCustomerInfoService {

    /**
     * 数据持久化对象
     */
    @Autowired
    private MfCustomerInfoMapper mfCustomerInfoMapper;


    @Override
    public List<MfCustomerInfo> getList(MfCustomerInfoSO so) {
        return this.mfCustomerInfoMapper.searchMfCustomerInfos(so);
    }

    @Override
    public Boolean checkExist(String chineseName) {
        QueryWrapper query = new QueryWrapper();
        query.eq("chinese_name", chineseName);
        Integer count = mfCustomerInfoMapper.selectCount(query);
        return count > 0 ? true : false;
    }

    @Override
    public String getGUIDByChineseName(String chineseName) {
        String res = "";
        MfCustomerInfoSO so = new MfCustomerInfoSO();
        so.setChineseName(chineseName);

        List<MfCustomerInfo> infoList = mfCustomerInfoMapper.searchMfCustomerInfos(so);
        if (infoList.size() > 0) {
            res = infoList.get(0).getGuid();
        }
        return res;
    }


}