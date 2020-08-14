package com.csbr.qingcloud.user.scm.mybatis.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantMapper;

/**
 * 商户资料业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfMerchantService extends CsbrService<MfMerchant> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 商户注册审批查询
     */
    PageListVO getRegisterPageList(JSONObject jsonObject);
}