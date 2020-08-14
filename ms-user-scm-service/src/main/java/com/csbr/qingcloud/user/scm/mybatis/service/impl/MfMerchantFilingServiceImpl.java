package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantFilingMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantFilingSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantFilingService;

import java.util.List;
import java.util.Map;

/**
 * 商户备案业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-31
 */

@Service
@Transactional
public class MfMerchantFilingServiceImpl extends CsbrServiceImpl<MfMerchantFilingMapper, MfMerchantFiling> implements MfMerchantFilingService {

    /** 商户备案数据持久化对象 */
    @Autowired
    private MfMerchantFilingMapper mfMerchantFilingMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfMerchantFiling> iPage = mfMerchantFilingMapper.mapperPageMfMerchantFilings(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 商户备案查询
     *
     * @param jsonObject
     */
    @Override
    public PageListVO getFilingPageList(JSONObject jsonObject) {
        Page<?> page = new Page<>(jsonObject.getInteger("pageIndex"), jsonObject.getInteger("pageSize"));
        IPage<List<Map<String, Object>>> filingPageList = mfMerchantFilingMapper.getFilingPageList(page,jsonObject);
        return new PageListVO().build(filingPageList);
    }
}