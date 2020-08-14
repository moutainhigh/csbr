package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.tenant.TenantAddDTO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffVO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.RedisStaffPOJO;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 人员服务
 * @author: yio
 * @create: 2020-07-31 14:39
 **/
public interface StaffService {

    /**
     * 新增人员
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> addStaff(@Valid StaffAddDTO dto);

    /**
     * 修改人员
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateStaff(StaffUpdateDTO dto);

    /**
     * 移除人员
     *
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeStaff(List<String> guids);

    /**
     * 查询人员
     *
     * @param dto
     * @return
     */
    PageListVO<StaffVO> getStaff(StaffQueryDTO dto);

    /**
     * 查询人员详情
     * @param guid
     * @return
     */
    StaffVO getStaffDetail(String guid);

    /**
     * 添加当前人员到redis
     * @param tenantGuid
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    void addCurrentStaffInfoToRedis(String tenantGuid) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取当前登录用户的人员信息
     * @param tenantGuid
     * @return
     */
    RedisStaffPOJO getCurrentStaffInfoByRedis(String tenantGuid);
}