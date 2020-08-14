package com.csbr.qingcloud.user.scm.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import com.csbr.qingcloud.user.scm.domain.dto.camunda.CsbrCommonProcessRequest;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.merchant.MerchantUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantDetailVO;
import com.csbr.qingcloud.user.scm.domain.vo.MerchantVO;
import com.csbr.qingcloud.user.scm.feign.CamundaFeign;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchant;
import com.csbr.qingcloud.user.scm.mybatis.service.MfMerchantService;
import com.csbr.qingcloud.user.scm.service.MerchantService;
import com.csbr.qingcloud.user.scm.util.ApprovalStateEnum;
import com.csbr.qingcloud.user.scm.util.EnterpriseCateEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 商户服务
 * @author: yio
 * @create: 2020-07-30 11:32
 **/
@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MfMerchantService mfMerchantService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private CamundaFeign camundaFeign;

    private void checkDuplicate(String merchantName,String guid){
        //查看商户名称是否重复
        LambdaQueryWrapper<MfMerchant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfMerchant::getMerchantName, merchantName)
                .select(MfMerchant::getGuid);
        MfMerchant merchant=mfMerchantService.getOne(queryWrapper);
        if (merchant!=null && (StringUtils.isEmpty(guid) || !merchant.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("商户(%s)已存在。", merchantName));
        }

    }
    @Override
    public CommonRes<Boolean> addMerchant(@Valid MerchantAddDTO dto) {
        //查看商户名称是否重复
        checkDuplicate(dto.getMerchantName(),null);
        MfMerchant merchant = csbrBeanUtil.convert(dto, MfMerchant.class,true);
        CsbrCommonProcessRequest csbrCommonProcessRequest = new CsbrCommonProcessRequest();
        //商户注册初始化
        csbrCommonProcessRequest.setProcessDefKey("Merchant_Registration_Approval_Process_1");
        JSONObject jsonObject = camundaFeign.initProcess(csbrCommonProcessRequest);
        log.info(jsonObject.toJSONString());
        if(jsonObject != null && jsonObject.get("code").equals("00000")){
            JSONArray data = jsonObject.getJSONArray("data");
            if(data != null && data.size() > 0){
                //获取任务id
                JSONObject o = (JSONObject) data.get(0);
                String taskId = o.getString("id");
                //流程id
                String processInstanceId = o.getString("processInstanceId");
                merchant.setProcDefId(processInstanceId);
                merchant.setTaskId(taskId);
                //流程定义id
//                String processDefinitionId = o.getString("processDefinitionId");
            }

        }
        merchant.setEnterpriseCate(EnterpriseCateEnum.INDIVIDUAL_MERCHANTS.getValue());
        mfMerchantService.csbrAddEntity(merchant);
        boolean flag = mfMerchantService.save(merchant);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("商户(%s)新增失败。", dto.getMerchantName()));
        }
        return CommonRes.success(flag);
    }

    @Override
    public CommonRes<Boolean> updateMerchant(MerchantUpdateDTO dto) {
        //查找更新的数据是否存在
        MfMerchant merchant=mfMerchantService.getById(dto.getGuid());
        if (merchant==null) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("商户(%s)不存在，无法更新。", dto.getMerchantName()));
        }

        //查看商户名称是否重复
        checkDuplicate(dto.getMerchantName(),dto.getGuid());

         //提交后的商户无法修改
        if(!ApprovalStateEnum.WAIT_SUBMIT.getValue().equals(merchant.getApprovalState())){
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, String.format("商户(%s)已提交，无法更新。", dto.getMerchantName()));

        }
        //更新数据
        LambdaUpdateWrapper<MfMerchant> updateWrapper = mfMerchantService.csbrUpdateWrapper(MfMerchant.class);
        updateWrapper.eq(MfMerchant::getGuid, dto.getGuid());

        MfMerchant newMerchant = csbrBeanUtil.convert(dto, MfMerchant.class,true);
        newMerchant.setUpdateTime(merchant.getUpdateTime());
        boolean flag = mfMerchantService.update(merchant, updateWrapper);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("商户(%s)更新失败，可能已在其他终端更新，请重试。", dto.getMerchantName()));
        }

        return CommonRes.success(flag);
    }

    @Override
    public CommonRes<Boolean> removeMerchant(List<String> guids) {
        //查找更新的数据是否存在
        LambdaQueryWrapper<MfMerchant> wrapper=Wrappers.lambdaQuery();
        wrapper.in(MfMerchant::getGuid,guids);
        wrapper.eq(MfMerchant::getApprovalState,ApprovalStateEnum.WAIT_SUBMIT.getValue());
        List<MfMerchant> lst=mfMerchantService.list(wrapper);
        if (CollectionUtils.isEmpty(lst)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据,可能数据已提交或已删除。");
        }
        //删除数据
        MfMerchant delMerchant=null;
        LambdaUpdateWrapper<MfMerchant> updateWrapper=null;
        boolean flag;
        for (MfMerchant ent:lst ) {
            delMerchant=new MfMerchant();
            delMerchant.setIsDeleted("Y");
            delMerchant.setUpdateTime(ent.getUpdateTime());
            updateWrapper=mfMerchantService.csbrUpdateWrapper(MfMerchant.class);
            updateWrapper.eq(MfMerchant::getGuid,ent.getGuid());
            flag =mfMerchantService.update(delMerchant,updateWrapper);
            if (!flag) {
                throw new CsbrSystemException(SystemError.DATA_DEL_ERROR,  String.format("商户(%s)删除失败,可能数据已在其他终端更新，请重试。",ent.getMerchantName()));
            }
        }


        return CommonRes.success();
    }

    @Override
    public PageListVO<MerchantVO> getMerchant(MerchantQueryDTO dto) {
        LambdaQueryWrapper<MfMerchant> wrapper= mfMerchantService.csbrQueryWrapper(dto, MfMerchant.class);
        wrapper.select(MfMerchant::getGuid,
                MfMerchant::getMerchantName,
                MfMerchant::getAbbreviation,
                MfMerchant::getManager,
                MfMerchant::getBizState,
                MfMerchant::getContactTel,
                MfMerchant::getProvince,
                MfMerchant::getCity,
                MfMerchant::getDistrict,
                MfMerchant::getVenue,
                MfMerchant::getIsFreeMan,
                MfMerchant::getCoOperativesGuid,
                MfMerchant::getCoOperativesName,
                MfMerchant::getCreateTime,
                MfMerchant::getCreateUserName);
        PageListVO<MfMerchant> lst = mfMerchantService.csbrPageList(dto
                , wrapper);
        PageListVO<MerchantVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),MerchantVO.class));
        return pageListVO;

    }

    @Override
    public MerchantDetailVO getMerchantDetail(String guid) {
        if (StringUtils.isEmpty(guid)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "商户关键字不能为空");

        }

        MfMerchant merchant=mfMerchantService.getById(guid);
        MerchantDetailVO vo=null;
        if(merchant!=null){
            vo=csbrBeanUtil.convert(merchant,MerchantDetailVO.class);
        }
        else
        {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "商户数据不存在。");
        }
        return vo;
    }
}
