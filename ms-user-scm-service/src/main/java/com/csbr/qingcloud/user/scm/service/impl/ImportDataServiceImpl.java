package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.response.ResponseCode;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.UserInfo;
import com.csbr.cloud.mybatis.interceptor.UserContextHolder;
import com.csbr.qingcloud.user.scm.domain.dto.importdata.*;
import com.csbr.qingcloud.user.scm.domain.dto.staff.UserAddDTO;
import com.csbr.qingcloud.user.scm.feign.AdminPlatformAuthFeign;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfCustomerService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfGoodsService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrCustomerSalesIndicatorService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSalesFlowService;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfOrganisation;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfStaff;
import com.csbr.qingcloud.user.scm.mybatis.service.MfOrganisationService;
import com.csbr.qingcloud.user.scm.mybatis.service.MfStaffService;
import com.csbr.qingcloud.user.scm.service.ImportDataService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description:
 * @author: Huanglh
 * @create: 2020-08-03 15:17
 **/
@Service
@Transactional
public class ImportDataServiceImpl implements ImportDataService {

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private MfGoodsService mfGoodsService;
    @Autowired
    private MfCustomerService mfCustomerService;
    @Autowired
    private TrSalesFlowService trSalesFlowService;
    @Autowired
    private MfStaffService mfStaffService;
    @Autowired
    private MfOrganisationService mfOrganisationService;
    @Autowired
    private TrCustomerSalesIndicatorService customerSalesIndicatorService;
    @Autowired
    private AdminPlatformAuthFeign platformAuthFeign;


    // region user部分

    /**
     * 导入人员资料
     *
     * @param dtos
     * @return
     */
    @Override
    @GlobalTransactional
    public CommonRes importStaff(List<StaffInfoImportDTO> dtos) {
        try {
            // 检查用户信息
            List<String> mobileNos = new ArrayList<>();
            for (StaffInfoImportDTO dto : dtos) {
                mobileNos.add(dto.getMobileNo());
            }
            // 获取平台GUID
            UserInfo userInfo = UserContextHolder.get();
            if (ObjectUtils.isEmpty(userInfo)) {
                throw new CsbrUserException(UserError.LOCAL_USERINFO_ISNULL, "User info is null!");
            }
            String platGuid = userInfo.getPlatformGuid();

            // 从用户中心获取用户资料
            CommonRes<Map<String, String>> userRes = platformAuthFeign.getUsersByIdMobilePlat(mobileNos, platGuid);
            if (!userRes.getCode().equals(ResponseCode.Success.SUCCESS_CODE)) {
                return userRes;
            }
            Map<String, String> userMap = userRes.getData();
            // 构造新增用户的列表
            List<UserAddDTO> userAddDTOS = new ArrayList<>();
            for (StaffInfoImportDTO dto : dtos) {
                // 如果存在用户，则填入用户GUID(用电话作为key)
                if (userMap.containsKey(dto.getMobileNo())) {
                    dto.setUserGuid(userMap.get(dto.getMobileNo()));
                } else {
                    // 如果不存在用户，需要新增用户，构建新增用户DTO
                    UserAddDTO userAddDTO = new UserAddDTO();
                    userAddDTO.setIdCode(dto.getIdCode());
                    userAddDTO.setMobileNo(dto.getMobileNo());
                    userAddDTO.setName(dto.getStaffName());
                    userAddDTO.setTenantGuid(dto.getTenantGuid());
                    userAddDTO.setPlatformGuid(platGuid);
                    userAddDTOS.add(userAddDTO);
                }
            }

            // 创建用户
            if (userAddDTOS.size() > 0) {
                CommonRes<Map<String, String>> userAddRes = platformAuthFeign.batchAddUser(userAddDTOS);
                if (!userRes.getCode().equals(ResponseCode.Success.SUCCESS_CODE)) {
                    return userAddRes;
                }
                // 获取新增用户信息，key-手机号，value
                Map<String, String> newUsers = userAddRes.getData();
                // 填入新增用户的GUID，手机号为key
                for (StaffInfoImportDTO dto : dtos) {
                    if (newUsers.containsKey(dto.getMobileNo())) {
                        dto.setUserGuid(newUsers.get(dto.getMobileNo()));
                    }
                }
            }

            // dto 转换为 entity
            List<MfStaff> mfStaffs = csbrBeanUtil.convert(dtos, MfStaff.class);

            // 添加新增默认内容
            List<String> jobNumbers = new ArrayList<>();
            String tenantGuid = "";
            for (MfStaff mfStaff : mfStaffs) {
                mfStaffService.csbrAddEntity(mfStaff);
                jobNumbers.add(mfStaff.getJobNumber());
                tenantGuid = mfStaff.getTenantGuid();
            }

            // 以工号、手机号验证已存在的人员
            LambdaQueryWrapper<MfStaff> staffQuery = Wrappers.lambdaQuery();
            staffQuery.eq(MfStaff::getTenantGuid, tenantGuid).in(MfStaff::getMobileNo, mobileNos)
                    .in(MfStaff::getJobNumber, jobNumbers)
                    .select(MfStaff::getGuid, MfStaff::getMobileNo, MfStaff::getJobNumber);
            List<MfStaff> staffs = mfStaffService.list(staffQuery);
            // 构建Map，key为手机号+工号，value为已存在人员的GUID
            Map<String, String> staffMap = staffs.stream().collect(
                    Collectors.toMap((staff) -> staff.getMobileNo() + staff.getJobNumber(), MfStaff::getGuid));

            for (MfStaff mfStaff : mfStaffs) {
                if (staffMap.containsKey(mfStaff.getMobileNo() + mfStaff.getJobNumber())) {
                    mfStaff.setGuid(staffMap.get(mfStaff.getMobileNo() + mfStaff.getJobNumber()));
                    mfStaffService.csbrUpdateEntity(mfStaff);
                    // 如果是更新的数据，就去掉创建人信息
                    mfStaff.setCreateUserId(null);
                    mfStaff.setCreateUserName(null);
                }
            }

            mfStaffService.saveOrUpdateBatch(mfStaffs);

        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }

        return CommonRes.success();
    }

    /**
     * 导入人员从属关系资料
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public CommonRes importStaffRelation(List<StaffRelationImportDTO> dtos) {
        try {
            // 获取所有工号
            List<String> jobNumbers = new ArrayList<>();
            String tenantGuid = "";
            for (StaffRelationImportDTO dto : dtos) {
                jobNumbers.add(dto.getJobNumber());
                jobNumbers.add(dto.getLeaderJobNumber());
                tenantGuid = dto.getTenantGuid();
            }

            // 查找指定企业的人员
            LambdaQueryWrapper<MfStaff> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(MfStaff::getTenantGuid, tenantGuid).in(MfStaff::getJobNumber, jobNumbers)
                    .select(MfStaff::getGuid, MfStaff::getJobNumber);
            List<MfStaff> staffs = mfStaffService.list(queryWrapper);
            // 构建Map，key为工号，value为GUID
            Map<String, String> guids = staffs.stream().collect(Collectors.toMap(MfStaff::getJobNumber, MfStaff::getGuid));

            List<MfStaff> updateStaffs = new ArrayList<>();
            for (StaffRelationImportDTO dto : dtos) {
                MfStaff staff = new MfStaff();
                staff.setGuid(guids.get(dto.getJobNumber()));
                staff.setLeaderGuid(guids.get(dto.getLeaderJobNumber()));
            }

            mfStaffService.updateBatchById(updateStaffs);

        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }

        return CommonRes.success();
    }
    // endregion

    // region data部分(后期会拆分出去)

    /**
     * 导入产品资料
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public CommonRes importGoods(List<ProductInfoImportDTO> dtos) {
        try {
            // dto 转换为 entity
            List<MfGoods> mfGoods = csbrBeanUtil.convert(dtos, MfGoods.class);

            // 添加新增默认内容
            List<String> goodsCodes = new ArrayList<>();
            String tenantGuid = "";
            for (MfGoods mfGood : mfGoods) {
                mfGoodsService.csbrAddEntity(mfGood);
                goodsCodes.add(mfGood.getGoodsCode());
                tenantGuid = mfGood.getTenantGuid();
            }

            // 以 企业GUID，商品编号 查找符合条件的数据，为已存在的数据
            LambdaQueryWrapper<MfGoods> query = Wrappers.lambdaQuery();
            query.eq(MfGoods::getTenantGuid, tenantGuid);
            query.in(MfGoods::getGoodsCode, goodsCodes);
            query.select(MfGoods::getGuid, MfGoods::getTenantGuid, MfGoods::getGoodsCode);
            List<MfGoods> queryGoods = mfGoodsService.list(query);

            // 所有已存在的数据，填入guid，会自动做更新操作
            for (MfGoods mfGood : mfGoods) {
                for (MfGoods queryGood : queryGoods) {
                    if (mfGood.getTenantGuid().equals(queryGood.getTenantGuid())
                            && mfGood.getGoodsCode().equals(queryGood.getGoodsCode())) {
                        mfGood.setGuid(queryGood.getGuid());
                        mfGoodsService.csbrUpdateEntity(mfGood);
                        // 如果是更新的数据，就去掉创建人信息
                        mfGood.setCreateUserId(null);
                        mfGood.setCreateUserName(null);
                        break;
                    }
                }
            }

            mfGoodsService.saveOrUpdateBatch(mfGoods);
        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }

        return CommonRes.success();
    }

    /**
     * 导入客户资料
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public CommonRes importCustomer(List<CustomInfoImportDTO> dtos) {
        try {
            // dto 转换为 entity
            List<MfCustomer> mfCustomers = csbrBeanUtil.convert(dtos, MfCustomer.class);

            // 添加新增默认内容
            List<String> customerCodes = new ArrayList<>();
            String tenantGuid = "";
            for (MfCustomer mfCustomer : mfCustomers) {
                mfCustomerService.csbrAddEntity(mfCustomer);
                customerCodes.add(mfCustomer.getCustomerCode());
                tenantGuid = mfCustomer.getTenantGuid();
            }

            // 以 企业GUID，商品编号 查找符合条件的数据，为已存在的数据
            LambdaQueryWrapper<MfCustomer> query = Wrappers.lambdaQuery();
            query.eq(MfCustomer::getTenantGuid, tenantGuid);
            query.in(MfCustomer::getCustomerCode, customerCodes);
            query.select(MfCustomer::getGuid, MfCustomer::getTenantGuid, MfCustomer::getCustomerCode);
            List<MfCustomer> queryCustomers = mfCustomerService.list(query);

            // 所有已存在的数据，填入guid，会自动做更新操作
            for (MfCustomer mfCustomer : mfCustomers) {
                for (MfCustomer queryCustomer : queryCustomers) {
                    if (mfCustomer.getTenantGuid().equals(queryCustomer.getTenantGuid())
                            && mfCustomer.getCustomerCode().equals(queryCustomer.getCustomerCode())) {
                        mfCustomer.setGuid(queryCustomer.getGuid());
                        mfCustomerService.csbrUpdateEntity(mfCustomer);
                        // 如果是更新的数据，就去掉创建人信息
                        mfCustomer.setCreateUserId(null);
                        mfCustomer.setCreateUserName(null);
                        break;
                    }
                }
            }

            mfCustomerService.saveOrUpdateBatch(mfCustomers);
        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }
        return CommonRes.success();
    }

    /**
     * 导入销售流向
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public CommonRes importSaleFlow(List<SaleFlowImportDTO> dtos) {
        try {
            // dto 转换为 entity
            List<TrSalesFlow> trSalesFlows = csbrBeanUtil.convert(dtos, TrSalesFlow.class);

            // 添加新增默认内容
            // 客户名称，用来获取客户GUID
            List<String> customerNames = new ArrayList<>();
            // 人员和经理工号，用来获取人员GUID和组织信息
            List<String> jobNumbers = new ArrayList<>();
            for (TrSalesFlow flow : trSalesFlows) {
                trSalesFlowService.csbrAddEntity(flow);
                customerNames.add(flow.getCustomerName());
                jobNumbers.add(flow.getStaffJobNumber());
                jobNumbers.add(flow.getManagerJobNumber());
            }
            // 工号去重
            jobNumbers = jobNumbers.stream().distinct().collect(Collectors.toList());

            // 获取客户信息
            LambdaQueryWrapper<MfCustomer> customerQuery = Wrappers.lambdaQuery();
            customerQuery.in(MfCustomer::getCustomerName, customerNames);
            customerQuery.select(MfCustomer::getGuid, MfCustomer::getCustomerName);
            Map<Object, MfCustomer> customers = mfCustomerService.csbrMap(customerQuery, MfCustomer::getCustomerName);

            // 获取人员信息
            LambdaQueryWrapper<MfStaff> staffQuery = Wrappers.lambdaQuery();
            staffQuery.in(MfStaff::getJobNumber, jobNumbers);
            staffQuery.select(MfStaff::getGuid, MfStaff::getJobNumber, MfStaff::getOrganisationGuid);
            Map<Object, MfStaff> staffs = mfStaffService.csbrMap(staffQuery, MfStaff::getJobNumber);

            // 获取组织信息
            List<String> orgGuids = new ArrayList<>();
            for (MfStaff staff : staffs.values()) {
                if (ObjectUtils.isNotEmpty(staff.getOrganisationGuid())) {
                    orgGuids.add(staff.getOrganisationGuid());
                }
            }
            // 组织GUID去重
            orgGuids = orgGuids.stream().distinct().collect(Collectors.toList());
            LambdaQueryWrapper<MfOrganisation> orgQuery = Wrappers.lambdaQuery();
            orgQuery.in(MfOrganisation::getGuid, orgGuids);
            orgQuery.select(MfOrganisation::getGuid, MfOrganisation::getOrganisationCode, MfOrganisation::getOrganisationName);
            Map<Object, MfOrganisation> orgs = mfOrganisationService.csbrMap(orgQuery, MfOrganisation::getGuid);

            // 添加客户，人员，组织信息
            for (TrSalesFlow flow : trSalesFlows) {
                // 客户信息
                if (customers.containsKey(flow.getCustomerName())) {
                    flow.setCustomerGuid(customers.get(flow.getCustomerName()).getGuid());
                }

                // 人员信息
                if (staffs.containsKey(flow.getStaffJobNumber())) {
                    flow.setStaffGuid(staffs.get(flow.getStaffJobNumber()).getGuid());
                    // 组织信息，组织只看人员所在的组织
                    String staffOrgGuid = staffs.get(flow.getStaffJobNumber()).getOrganisationGuid();
                    if (orgs.containsKey(staffOrgGuid)) {
                        MfOrganisation organisation = orgs.get(staffOrgGuid);
                        flow.setOrganisationGuid(organisation.getGuid());
                        flow.setOrganisationCode(organisation.getOrganisationCode());
                        flow.setOrganisationName(organisation.getOrganisationName());
                    }
                }

                // 经理信息
                if (staffs.containsKey(flow.getManagerJobNumber())) {
                    flow.setManagerGuid(staffs.get(flow.getManagerJobNumber()).getGuid());
                }
            }
        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }
        return CommonRes.success();
    }

    /**
     * 导入客户销售指标
     *
     * @param dtos
     * @return
     */
    @Override
    @Transactional
    public CommonRes importCustomerSalesIndicator(List<SaleIndicatorImportDTO> dtos) {
        try {

            // dto 转换为 entity
            List<TrCustomerSalesIndicators> csIndicators = csbrBeanUtil.convert(dtos, TrCustomerSalesIndicators.class);

            // 添加新增默认内容
            // 客户编号，用来获取客户GUID
            List<String> customerCodes = new ArrayList<>();
            // 人员工号，用来获取人员GUID和组织信息
            List<String> jobNumbers = new ArrayList<>();
            // 商品编号，用来获取商品信息
            List<String> goodCodes = new ArrayList<>();
            String tenantGuid = "";
            // 开始、截止日期，用来去掉重复添加的数据
            List<Timestamp> starts = new ArrayList<>();
            List<Timestamp> ends = new ArrayList<>();

            for (TrCustomerSalesIndicators csIndicator : csIndicators) {
                customerSalesIndicatorService.csbrAddEntity(csIndicator);
                customerCodes.add(csIndicator.getCustomerCode());
                jobNumbers.add(csIndicator.getJobNumber());
                goodCodes.add(csIndicator.getGoodsCode());
                tenantGuid = csIndicator.getTenantGuid();
                starts.add(csIndicator.getStartDate());
                ends.add(csIndicator.getEndDate());
            }

            // 获取客户信息
            LambdaQueryWrapper<MfCustomer> customerQuery = Wrappers.lambdaQuery();
            customerQuery.in(MfCustomer::getCustomerCode, customerCodes);
            customerQuery.select(MfCustomer::getGuid, MfCustomer::getCustomerCode);
            Map<Object, MfCustomer> customers = mfCustomerService.csbrMap(customerQuery, MfCustomer::getCustomerCode);

            // 获取人员信息,组织GUID
            LambdaQueryWrapper<MfStaff> staffQuery = Wrappers.lambdaQuery();
            staffQuery.in(MfStaff::getJobNumber, jobNumbers);
            staffQuery.select(MfStaff::getGuid, MfStaff::getJobNumber, MfStaff::getOrganisationGuid);
            Map<Object, MfStaff> staffs = mfStaffService.csbrMap(staffQuery, MfStaff::getJobNumber);

            // 获取商品信息
            LambdaQueryWrapper<MfGoods> goodQuery = Wrappers.lambdaQuery();
            goodQuery.in(MfGoods::getGoodsCode, goodCodes);
            goodQuery.select(MfGoods::getGuid, MfGoods::getGoodsCode);
            Map<Object, MfGoods> goodsMap = mfGoodsService.csbrMap(goodQuery, MfGoods::getGoodsCode);

            // 以 企业GUID，商品编号 查找符合条件的数据，为已存在的数据
            LambdaQueryWrapper<TrCustomerSalesIndicators> indicatorQuery = Wrappers.lambdaQuery();
            indicatorQuery.eq(TrCustomerSalesIndicators::getTenantGuid, tenantGuid)
                    .in(TrCustomerSalesIndicators::getCustomerCode, customerCodes)
                    .in(TrCustomerSalesIndicators::getJobNumber, jobNumbers)
                    .in(TrCustomerSalesIndicators::getGoodsCode, goodCodes)
                    .in(TrCustomerSalesIndicators::getStartDate, starts)
                    .in(TrCustomerSalesIndicators::getEndDate, ends)
                    .select(TrCustomerSalesIndicators::getTenantGuid,
                            TrCustomerSalesIndicators::getCustomerCode,
                            TrCustomerSalesIndicators::getJobNumber,
                            TrCustomerSalesIndicators::getGoodsCode,
                            TrCustomerSalesIndicators::getStartDate,
                            TrCustomerSalesIndicators::getEndDate,
                            TrCustomerSalesIndicators::getGuid);
            List<TrCustomerSalesIndicators> existIndicators = customerSalesIndicatorService.list(indicatorQuery);

            // 填入需要的字段内容
            for (TrCustomerSalesIndicators csIndicator : csIndicators) {
                // 客户信息
                if (customers.containsKey(csIndicator.getCustomerCode())) {
                    csIndicator.setCustomerGuid(customers.get(csIndicator.getCustomerCode()).getGuid());
                }
                // 人员 组织
                if (staffs.containsKey(csIndicator.getJobNumber())) {
                    csIndicator.setStaffGuid(staffs.get(csIndicator.getJobNumber()).getGuid());
                    csIndicator.setOrganisationGuid(staffs.get(csIndicator.getJobNumber()).getOrganisationGuid());
                }
                // 商品信息
                if (goodsMap.containsKey(csIndicator.getGoodsCode())) {
                    csIndicator.setGoodsGuid(goodsMap.get(csIndicator.getGoodsCode()).getGuid());
                }

                // 所有已存在的数据，填入guid，会自动做更新操作
                for (TrCustomerSalesIndicators existIndicator : existIndicators) {
                    if (csIndicator.getTenantGuid().equals(existIndicator.getTenantGuid())
                            && csIndicator.getStartDate().equals(existIndicator.getStartDate())
                            && csIndicator.getEndDate().equals(existIndicator.getEndDate())
                            && csIndicator.getCustomerCode().equals(existIndicator.getCustomerCode())
                            && csIndicator.getJobNumber().equals(existIndicator.getJobNumber())
                            && csIndicator.getGoodsCode().equals(existIndicator.getGoodsCode())
                    ) {
                        csIndicator.setGuid(existIndicator.getGuid());
                        customerSalesIndicatorService.csbrUpdateEntity(csIndicator);
                        // 如果是更新的数据，就去掉创建人信息
                        csIndicator.setCreateUserId(null);
                        csIndicator.setCreateUserName(null);
                        break;
                    }
                }
            }

            customerSalesIndicatorService.saveOrUpdateBatch(csIndicators);
        } catch (Exception ex) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, ex.getMessage());
        }
        return CommonRes.success();
    }
    // endregion
}
