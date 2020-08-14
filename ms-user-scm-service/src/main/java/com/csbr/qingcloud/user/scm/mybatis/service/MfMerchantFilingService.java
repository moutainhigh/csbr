package com.csbr.qingcloud.user.scm.mybatis.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfMerchantFilingSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfMerchantFilingMapper;

/**
 * 商户备案业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-31
 */

public interface MfMerchantFilingService extends CsbrService<MfMerchantFiling> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 商户备案查询
     */
    PageListVO getFilingPageList(JSONObject jsonObject);
}