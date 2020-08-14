package com.csbr.qingcloud.user.scm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.util.CommonUtil;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.entity.UserInfo;
import com.csbr.cloud.mybatis.interceptor.UserContextHolder;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.*;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationHierarchicalVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffVO;
import com.csbr.qingcloud.user.scm.feign.AdminPlatformAuthFeign;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaffPosition;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.mapper.pojo.RedisStaffPOJO;
import com.csbr.qingcloud.user.scm.mybatis.mapper.so.MfStaffSO;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffPositionService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.OrganisationService;
import com.csbr.qingcloud.user.scm.service.StaffService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 人员服务
 * @author: yio
 * @create: 2020-07-31 14:40
 **/
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private MfStaffService mfStaffService;

    @Autowired
    private AdminPlatformAuthFeign adminPlatfromAuthFeign;

    @Autowired
    private MfStaffPositionService mfStaffPositionService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private MfTenantService mfTenantService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;


    private void checkMobileDuplicate(String tenantGuid, String mobileNo, String guid) {
        //查看手机号是否重复
        LambdaQueryWrapper<MfStaff> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfStaff::getTenantGuid, tenantGuid)
                .eq(MfStaff::getMobileNo, mobileNo)
                .select(MfStaff::getGuid);
        MfStaff ent = mfStaffService.getOne(queryWrapper);
        if (ent != null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("人员手机号(%s)已存在。", mobileNo));
        }

    }
    private void checkExists(StaffAddDTO dto){

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());


    }
    /**
     * 添加人员服务
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addStaff(@Valid StaffAddDTO dto) {
        checkExists(dto);
        //查看账号是否重复
        LambdaQueryWrapper<MfStaff> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfStaff::getTenantGuid, dto.getTenantGuid())
                .eq(MfStaff::getLogonUser, dto.getLogonUser());
        int cnt = mfStaffService.count(queryWrapper);

        if (cnt > 0) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("人员账号(%s)已存在。", dto.getLogonUser()));
        }
        //查看手机号是否重复
        queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfStaff::getTenantGuid, dto.getTenantGuid())
                .eq(MfStaff::getMobileNo, dto.getMobileNo());
        cnt = mfStaffService.count(queryWrapper);
        if (cnt > 0) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("人员手机号(%s)已存在。", dto.getMobileNo()));
        }

        UserInfo userInfo = (UserInfo) UserContextHolder.get();

        //添加用户或更新注册信息
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setLogonUser(dto.getLogonUser());
        userAddDTO.setRegistrationCode(dto.getPwd());
        userAddDTO.setName(dto.getStaffName());
        userAddDTO.setMobileNo(dto.getMobileNo());
        userAddDTO.setPlatformGuid(userInfo.getPlatformGuid());
        userAddDTO.setIdCode(dto.getIdCode());
        CommonRes<String> res = adminPlatfromAuthFeign.updateRegCodeOrAddUser(userAddDTO);
        if (StringUtils.isNotEmpty(res.getMsg())) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, res.getMsg());

        }
        MfStaff staff = csbrBeanUtil.convert(dto, MfStaff.class);
        staff.setGuid(CommonUtil.newGuid());
        //添加人员岗位关系数据
        if (CollectionUtils.isNotEmpty(dto.getPositionGuids())) {
            List<MfStaffPosition> staffPositionList = new ArrayList<MfStaffPosition>();
            MfStaffPosition staffPosition = null;

            for (String posGuid : dto.getPositionGuids()) {
                staffPosition = new MfStaffPosition();
                staffPosition.setPositionGuid(posGuid);
                staffPosition.setTenantGuid(dto.getTenantGuid());
                staffPosition.setStaffGuid(staff.getGuid());
                mfStaffPositionService.csbrAddEntity(staffPosition);
                staffPositionList.add(staffPosition);
            }
            mfStaffPositionService.saveBatch(staffPositionList);
        }
        staff.setUserGuid(res.getData());
        mfStaffService.csbrAddEntity(staff);
        boolean flag = mfStaffService.save(staff);

        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("人员(%s)新增失败。", dto.getStaffName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改用户账号
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateStaff(StaffUpdateDTO dto) {
        checkExists(dto);
        //查找更新的数据是否存在
        LambdaQueryWrapper<MfStaff> queryWrapper = Wrappers.lambdaQuery();
        MfStaff staff = mfStaffService.getById(dto.getGuid());
        if (staff == null) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("人员(%s)不存在，无法更新。", dto.getStaffName()));
        }
        //查看账号是否重复
        if (StringUtils.isNotEmpty(dto.getLogonUser()) && !dto.getLogonUser().equals(staff.getLogonUser())) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, "人员账号不允许变更。");

        }
        //查看手机号是否重复
        checkMobileDuplicate(dto.getTenantGuid(), dto.getMobileNo(), dto.getGuid());
        //删除用户岗位关系
        if (StringUtils.isNotEmpty(dto.getTenantGuid())) {
            LambdaQueryWrapper<MfStaff> delQueryWrapper = Wrappers.lambdaQuery();
            delQueryWrapper.eq(MfStaff::getTenantGuid, dto.getTenantGuid());
            delQueryWrapper.eq(MfStaff::getGuid, dto.getGuid());
            mfStaffService.remove(delQueryWrapper);
        }
        UserInfo userInfo = (UserInfo) UserContextHolder.get();

        //添加用户或更新注册信息
        UserAddDTO userAddDTO = new UserAddDTO();
        userAddDTO.setLogonUser(dto.getLogonUser());
        userAddDTO.setRegistrationCode(dto.getPwd());
        userAddDTO.setName(dto.getStaffName());
        userAddDTO.setMobileNo(dto.getMobileNo());
        userAddDTO.setPlatformGuid(userInfo.getPlatformGuid());
        userAddDTO.setIdCode(dto.getIdCode());
        CommonRes<String> res = adminPlatfromAuthFeign.updateRegCodeOrAddUser(userAddDTO);
        if (StringUtils.isNotEmpty(res.getMsg())) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, res.getMsg());

        }
        //添加人员岗位关系数据
        if (CollectionUtils.isNotEmpty(dto.getPositionGuids())) {
            List<MfStaffPosition> staffPositionList = new ArrayList<MfStaffPosition>();
            MfStaffPosition staffPosition = null;
            for (String posGuid : dto.getPositionGuids()) {
                staffPosition = new MfStaffPosition();
                staffPosition.setPositionGuid(posGuid);
                staffPosition.setTenantGuid(dto.getTenantGuid());
                staffPosition.setStaffGuid(staff.getGuid());
                mfStaffPositionService.csbrAddEntity(staffPosition);
                staffPositionList.add(staffPosition);
            }
            mfStaffPositionService.saveBatch(staffPositionList);
        }
        //更新数据
        LambdaUpdateWrapper<MfStaff> updateWrapper = mfStaffService.csbrUpdateWrapper(MfStaff.class);
        updateWrapper.eq(MfStaff::getGuid, dto.getGuid());

        staff = csbrBeanUtil.convert(dto, MfStaff.class);

        boolean flag = mfStaffService.update(staff, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("人员(%s)更新失败。", dto.getStaffName()));
        }
        return CommonRes.success(flag);

    }

    /**
     * 移除人员
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeStaff(List<String> guids) {
        //查找更新的数据是否存在
        List<MfStaff> staffs = (List<MfStaff>) mfStaffService.listByIds(guids);

        if (CollectionUtils.isEmpty(staffs)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //对集合按tenantGuid与用户GUID分组
        Map<String, List<String>> staffMap = staffs.stream().collect(Collectors.groupingBy(MfStaff::getTenantGuid,
                Collectors.mapping(MfStaff::getUserGuid, Collectors.toList())));
        //如果用户信息不存在，那么删除用户账号信息
        for (Map.Entry<String, List<String>> entry : staffMap.entrySet()) {
            UserRemoveDTO rDTO = new UserRemoveDTO();
            rDTO.setTenantGuid(entry.getKey());
            rDTO.setGuids(entry.getValue());
            CommonRes<String> res = adminPlatfromAuthFeign.removeUserByStaff(rDTO);
            if (StringUtils.isNotEmpty(res.getMsg())) {
                throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, res.getMsg());

            }
        }

        //删除数据
        LambdaUpdateWrapper<MfStaff> updateWrapper = mfStaffService.csbrUpdateWrapper(MfStaff.class);
        updateWrapper.in(MfStaff::getGuid, guids);
        boolean flag = mfStaffService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }


    /**
     * 查询人员数据
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<StaffVO> getStaff(StaffQueryDTO dto) {
        LambdaQueryWrapper<MfStaff> queryWrapper = mfStaffService.csbrQueryWrapper(dto, MfStaff.class);
        PageListVO<MfStaff> lst = mfStaffService.csbrPageList(dto, queryWrapper);
        PageListVO<StaffVO> staffVos = csbrBeanUtil.convert(lst, PageListVO.class);
        staffVos.setRecords(csbrBeanUtil.convert(staffVos.getRecords(),StaffVO.class));
        if (lst != null && CollectionUtils.isNotEmpty(lst.getRecords())) {


            Set<String> staffSet = new HashSet<>();
            Set<String> userSet = new HashSet<>();
            for (MfStaff ent : lst.getRecords()) {
                staffSet.add(ent.getUserGuid());
                userSet.add(ent.getUserGuid());
            }
            //查询岗位
            Map<String, List<String>> map = mfStaffPositionService.getStaffPositionNameMap(new ArrayList<>(staffSet));

            //查询角色
            UserRoleNameDTO userRoleNameDTO = new UserRoleNameDTO();
            userRoleNameDTO.setTenantGuid(dto.getTenantGuid());
            userRoleNameDTO.setUserGuids(new ArrayList<>(userSet));
            Map<String, List<String>> mapUserRole = adminPlatfromAuthFeign.getUserRoleNames(userRoleNameDTO);
            for (StaffVO vo : staffVos.getRecords()) {
                vo.setPositionNames(map.get(vo.getGuid()));
                vo.setRoleNames(map.get(vo.getUserGuid()));
            }

        }


        return staffVos;
    }

    /**
     * 查询人员详情
     *
     * @param guid
     * @return
     */
    @Override
    public StaffVO getStaffDetail(String guid) {
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "人员关键字不能为空");

        }
        MfStaff staff = mfStaffService.getById(guid);
        if (staff == null) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "用户数据不存在。");
        } else {
            //查询岗位
            Map<String, List<String>> map = mfStaffPositionService.getStaffPositionNameMap(Arrays.asList(guid));

            //查询角色
            UserRoleNameDTO userRoleNameDTO = new UserRoleNameDTO();
            userRoleNameDTO.setTenantGuid(staff.getTenantGuid());
            userRoleNameDTO.setUserGuids(Arrays.asList(staff.getUserGuid()));
            Map<String, List<String>> mapUserRole = adminPlatfromAuthFeign.getUserRoleNames(userRoleNameDTO);

            StaffVO vo = csbrBeanUtil.convert(staff, StaffVO.class);
            vo.setPositionNames(map.get(vo.getGuid()));
            vo.setRoleNames(map.get(vo.getUserGuid()));
            return vo;

        }

    }

    /**
     * 添加当前人员到redis
     * @param tenantGuid
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void addCurrentStaffInfoToRedis(String tenantGuid) throws InvocationTargetException, IllegalAccessException {
        //根据用户信息查询人员
        UserInfo userInfo = (UserInfo) UserContextHolder.get();
        if (userInfo != null) {
            LambdaQueryWrapper<MfStaff> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(MfStaff::getTenantGuid, tenantGuid)
                    .eq(MfStaff::getUserGuid, userInfo.getUserId())
                    .select(MfStaff::getGuid, MfStaff::getOrganisationGuid);
            MfStaff staff = mfStaffService.getOne(queryWrapper);
            RedisStaffPOJO pojo = new RedisStaffPOJO();
            pojo.setStaffGuid(staff.getGuid());
            pojo.setOrganisationGuid(staff.getOrganisationGuid());
            //查询本企业的所有组织
            List<OrganisationHierarchicalVO> vos = organisationService.getOrganisationHierarchical(tenantGuid);
            OrganisationHierarchicalVO organisationHierarchicalVO = organisationService.getAssignOrganisationHierarchical(vos, staff.getOrganisationGuid());

            if (organisationHierarchicalVO != null) {
                pojo.setOrganisationCode(organisationHierarchicalVO.getOrganisationCode());
                pojo.setOrganisationName(organisationHierarchicalVO.getOrganisationName());
                //获取当前组织子GUID
                pojo.setChildOranisationGuids(organisationService.getChildOrganisationsByHierarchical(organisationHierarchicalVO));
            }
            redisTemplate.opsForValue().set(String.format("CurrentStaff:%s-%s-%s", userInfo.getPlatformGuid(), tenantGuid, userInfo.getUserId())
                    , JSON.toJSONString(pojo));
        }
    }

    /**
     * 获取当前登录用户的人员信息
     * @param tenantGuid
     * @return
     */
    @Override
    public RedisStaffPOJO getCurrentStaffInfoByRedis(String tenantGuid){
        UserInfo userInfo = (UserInfo) UserContextHolder.get();
        RedisStaffPOJO pojo=null;
        if (userInfo != null) {
            pojo= JSON.parseObject((String) redisTemplate.opsForValue().get(String.format("CurrentStaff:%s-%s-%s", userInfo.getPlatformGuid(), tenantGuid, userInfo.getUserId()))
                    ,new TypeReference<RedisStaffPOJO>(){});
        }
        if(pojo==null){
            throw new CsbrSystemException(SystemError.CACHET_DATA_NOT_EXISTS, "缓存中的人员信息不存在。");
        }
        return pojo;
    }


}
