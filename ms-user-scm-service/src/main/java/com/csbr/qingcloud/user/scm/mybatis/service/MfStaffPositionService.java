package com.csbr.qingcloud.user.scm.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaffPosition;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.MfStaffPositionPOJO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffPositionSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfStaffPositionMapper;

import java.util.List;
import java.util.Map;

/**
 * 人员岗位业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfStaffPositionService extends CsbrService<MfStaffPosition> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);


    /**
     * 获取岗位人员的map
     * @param staffGuid
     * @return key:人员GUID，value:岗位名称
     */
    Map<String,List<String>> getStaffPositionNameMap(List<String> staffGuid);
}