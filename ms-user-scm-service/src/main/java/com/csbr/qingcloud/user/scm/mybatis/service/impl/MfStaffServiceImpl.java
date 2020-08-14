package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfStaffMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffService;

import java.util.Arrays;

/**
 * 人员业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfStaffServiceImpl extends CsbrServiceImpl<MfStaffMapper, MfStaff> implements MfStaffService {

    /** 人员数据持久化对象 */
    @Autowired
    private MfStaffMapper mfStaffMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfStaff> iPage = mfStaffMapper.mapperPageMfStafves(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 检查人员GUID是否存在
     * @param staffGuid
     */
    @Override
    public void checkStaffExistsByGuid(String staffGuid) {

        //检查人员GUID是否存在
        if(StringUtils.isNotEmpty(staffGuid)){
            if(!this.isExistsData(Arrays.asList(staffGuid), MfStaff.class)){
                throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("人员关键字(%s)不存在。", staffGuid));

            }
        }

    }
}