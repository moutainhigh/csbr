package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfPosition;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfPositionMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfPositionSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfPositionService;

/**
 * 岗位业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfPositionServiceImpl extends CsbrServiceImpl<MfPositionMapper, MfPosition> implements MfPositionService {

    /** 岗位数据持久化对象 */
    @Autowired
    private MfPositionMapper mfPositionMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfPosition> iPage = mfPositionMapper.mapperPageMfPositions(page, so);
        return new PageListVO().build(iPage);
    }


}