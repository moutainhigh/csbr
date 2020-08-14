package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffSO;

/**
 * 人员实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfStaffMapper extends BaseMapper<MfStaff> {
    /**
     * 分页查询人员信息.
     *
     * @param so 查询条件
     *
     * @return 人员查询结果
     */
    <D extends BasePageDTO> IPage<MfStaff> mapperPageMfStafves(Page<?> page, D so);
}
