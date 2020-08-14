package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantService;

import java.util.List;
import java.util.Map;

/**
 * 商户资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfMerchantServiceImpl extends CsbrServiceImpl<MfMerchantMapper, MfMerchant> implements MfMerchantService {

    /** 商户资料数据持久化对象 */
    @Autowired
    private MfMerchantMapper mfMerchantMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfMerchant> iPage = mfMerchantMapper.mapperPageMfMerchants(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 商户注册审批查询
     *
     * @param jsonObject
     */
    @Override
    public PageListVO getRegisterPageList(JSONObject jsonObject) {
        Page<?> page = new Page<>(jsonObject.getInteger("pageIndex"), jsonObject.getInteger("pageSize"));
        IPage<List<Map<String, Object>>> registerPageList = mfMerchantMapper.getRegisterPageList(page, jsonObject);
        return new PageListVO().build(registerPageList);
    }
}