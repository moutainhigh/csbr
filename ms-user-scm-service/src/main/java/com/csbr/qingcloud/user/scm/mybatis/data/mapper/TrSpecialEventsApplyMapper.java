package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSpecialEventsApply;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSpecialEventsApplySO;

/**
 * 特殊事件申请实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Component
@Mapper
@Repository
public interface TrSpecialEventsApplyMapper extends BaseMapper<TrSpecialEventsApply> {
    /**
     * 分页查询特殊事件申请信息.
     *
     * @param so 查询条件
     *
     * @return 特殊事件申请查询结果
     */
    <D extends BasePageDTO> IPage<TrSpecialEventsApply> mapperPageTrSpecialEventsApplies(Page<?> page, D so);
}
