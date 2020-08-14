package com.csbr.qingcloud.user.scm.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.MfStaffPositionPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaffPosition;
import com.csbr.qingcloud.user.scm.mybatis.mapper.MfStaffPositionMapper;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffPositionSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffPositionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 人员岗位业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Service
@Transactional
public class MfStaffPositionServiceImpl extends CsbrServiceImpl<MfStaffPositionMapper, MfStaffPosition> implements MfStaffPositionService {

    /** 人员岗位数据持久化对象 */
    @Autowired
    private MfStaffPositionMapper mfStaffPositionMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfStaffPosition> iPage = mfStaffPositionMapper.mapperPageMfStaffPositions(page, so);
        return new PageListVO().build(iPage);
    }


    /**
     * 获取岗位人员的map
     * @param staffGuid
     * @return key:人员GUID，value:岗位名称
     */
    @Override
    public Map<String,List<String>> getStaffPositionNameMap(List<String> staffGuid) {
        List<MfStaffPositionPOJO> lst=mfStaffPositionMapper.getStaffPositionNames(staffGuid);
        return lst.stream().collect(Collectors.groupingBy(MfStaffPositionPOJO::getStaffGuid,
                Collectors.mapping(MfStaffPositionPOJO::getPositionName,Collectors.toList())));
    }


}