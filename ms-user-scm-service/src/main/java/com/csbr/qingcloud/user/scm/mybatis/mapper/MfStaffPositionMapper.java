package com.csbr.qingcloud.user.scm.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.MfStaffPositionPOJO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaffPosition;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffPositionSO;

import java.util.List;

/**
 * 人员岗位实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfStaffPositionMapper extends BaseMapper<MfStaffPosition> {
    /**
     * 分页查询人员岗位信息.
     *
     * @param so 查询条件
     *
     * @return 人员岗位查询结果
     */
    <D extends BasePageDTO> IPage<MfStaffPosition> mapperPageMfStaffPositions(Page<?> page, D so);

    List<MfStaffPositionPOJO> getStaffPositionNames(List<String> staffGuid);
}
