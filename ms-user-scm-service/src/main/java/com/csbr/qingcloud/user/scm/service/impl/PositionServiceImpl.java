package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;
import com.csbr.qingcloud.user.scm.domain.vo.PositionVO;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfPosition;
import com.csbr.qingcloud.user.scm.mybatis.service.MfPositionService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.PositionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 岗位服务
 * @author: yio
 * @create: 2020-07-31 12:01
 **/
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private MfPositionService mfPositionService;

    @Autowired
    private MfTenantService mfTenantService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    private void checkDuplicate(String tenantGuid,String positionName,String guid){
        //查看名称是否重复
        LambdaQueryWrapper<MfPosition> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfPosition::getTenantGuid,tenantGuid)
                .eq(MfPosition::getPositionName,positionName)
                .select(MfPosition::getGuid);
        MfPosition ent=mfPositionService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("岗位(%s)已存在。", positionName));
        }

    }

    public void checkExists(PositionAddDTO dto){

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());


    }
    /**
     * 岗位新增服务
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addPosition(@Valid PositionAddDTO dto) {
        //查看岗位名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getPositionName(),null);
        checkExists(dto);
        MfPosition position = csbrBeanUtil.convert(dto, MfPosition.class);
        mfPositionService.csbrAddEntity(position);
        boolean flag = mfPositionService.save(position);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("岗位(%s)新增失败。", dto.getPositionName()));
        }
        return CommonRes.success(flag);
    }

    @Override
    public CommonRes<Boolean> updatePosition(PositionUpdateDTO dto) {
        //查找更新的数据是否存在
        if(!mfPositionService.isExistsData(Arrays.asList(dto.getGuid()),MfPosition.class)){
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("岗位(%s)不存在，无法更新。", dto.getPositionName()));
        }
        //查看岗位名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getPositionName(),dto.getGuid());
        checkExists(dto);
         //更新数据
        LambdaUpdateWrapper<MfPosition> updateWrapper = mfPositionService.csbrUpdateWrapper(MfPosition.class);
        updateWrapper.eq(MfPosition::getGuid, dto.getGuid());

        MfPosition position = csbrBeanUtil.convert(dto, MfPosition.class);
        boolean flag = mfPositionService.update(position, updateWrapper);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("岗位(%s)更新失败。", dto.getPositionName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除岗位
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removePosition(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfPositionService.isExistsData(guids, MfPosition.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfPosition> updateWrapper = mfPositionService.csbrUpdateWrapper(MfPosition.class);
        updateWrapper.in(MfPosition::getGuid, guids);
        boolean flag =  mfPositionService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 查询岗位信息
     * @param dto
     * @return
     */
    @Override
    public PageListVO<PositionVO> getPosition(PositionQueryDTO dto) {
        PageListVO<MfPosition> lst = mfPositionService.csbrPageList(dto
                , mfPositionService.csbrQueryWrapper(dto, MfPosition.class));
        PageListVO<PositionVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),PositionVO.class));
        return pageListVO;

    }

}
