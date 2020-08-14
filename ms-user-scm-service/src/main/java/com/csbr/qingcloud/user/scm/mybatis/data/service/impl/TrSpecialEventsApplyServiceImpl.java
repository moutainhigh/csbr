package com.csbr.qingcloud.user.scm.mybatis.data.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSpecialEventsApply;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.TrSpecialEventsApplyMapper;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSpecialEventsApplySO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSpecialEventsApplyService;

/**
 * 特殊事件申请业务逻辑.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Service
@Transactional
public class TrSpecialEventsApplyServiceImpl extends CsbrServiceImpl<TrSpecialEventsApplyMapper, TrSpecialEventsApply> implements TrSpecialEventsApplyService {

    /** 特殊事件申请数据持久化对象 */
    @Autowired
    private TrSpecialEventsApplyMapper trSpecialEventsApplyMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<TrSpecialEventsApply> iPage = trSpecialEventsApplyMapper.mapperPageTrSpecialEventsApplies(page, so);
        return new PageListVO().build(iPage);
    }


}