package com.csbr.qingcloud.user.scm.mybatis.service;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.CsbrService;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffSO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfStaffMapper;

/**
 * 人员业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

public interface MfStaffService extends CsbrService<MfStaff> {
    /**
     * 获取mapper分页查询链表
     *
     * @return 结果链表
     */
    <D extends BasePageDTO> PageListVO getMapperPageList(D so);

    /**
     * 检查人员是否存在
     * @param staffGuid
     */
    void checkStaffExistsByGuid(String staffGuid);

}