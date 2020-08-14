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
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffAddDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfGoods;
import com.csbr.qingcloud.user.scm.mybatis.data.service.MfGoodsService;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfPosition;
import com.csbr.qingcloud.user.scm.mybatis.entity.MfTenant;
import com.csbr.qingcloud.user.scm.mybatis.service.MfTenantService;
import com.csbr.qingcloud.user.scm.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 产品资料服务
 * @author: yio
 * @create: 2020-08-06 17:29
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private MfGoodsService mfGoodsService;

    @Autowired
    private MfTenantService mfTenantService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    private void checkDuplicate(String tenantGuid,String goodsCode,String guid){
        //查看编码与名称是否重复
        LambdaQueryWrapper<MfGoods> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfGoods::getTenantGuid,tenantGuid)
                .eq(MfGoods::getGoodsCode,goodsCode)
                .select(MfGoods::getGuid);
        MfGoods ent=mfGoodsService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
                throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("产品编码(%s)已存在。", goodsCode));

        }


    }

    private void checkExists(GoodsAddDTO dto){

        //检查tenantGuid是否存在
        mfTenantService.checkTenantExistsByGuid(dto.getTenantGuid());


    }
    /**
     * 获取产品资料
     * @param dto
     * @return
     */
    @Override
    public PageListVO<GoodsVO> getGoods(GoodsQueryDTO dto) {
        PageListVO<MfGoods> lst = mfGoodsService.csbrPageList(dto
                , mfGoodsService.csbrQueryWrapper(dto, MfGoods.class));
        PageListVO<GoodsVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),GoodsVO.class));
        return pageListVO;

    }

    /**
     * 产品新增服务
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addGoods(@Valid GoodsAddDTO dto) {
        //查看岗位名称是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getGoodsCode(),null);
        checkExists(dto);
        MfGoods goods = csbrBeanUtil.convert(dto, MfGoods.class);
        mfGoodsService.csbrAddEntity(goods);
        boolean flag = mfGoodsService.save(goods);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("商品(%s)新增失败。", dto.getGoodsName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改产品
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateGoods(GoodsUpdateDTO dto) {
        //查找更新的数据是否存在
        if(!mfGoodsService.isExistsData(Arrays.asList(dto.getGuid()), MfGoods.class)){
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("产品(%s)不存在，无法更新。", dto.getGoodsName()));
        }
        //查看产品是否重复
        checkDuplicate(dto.getTenantGuid(),dto.getGoodsCode(),dto.getGuid());

        checkExists(dto);

        //更新数据
        LambdaUpdateWrapper<MfGoods> updateWrapper = mfGoodsService.csbrUpdateWrapper(MfGoods.class);
        updateWrapper.eq(MfGoods::getGuid, dto.getGuid());

        MfGoods goods = csbrBeanUtil.convert(dto, MfGoods.class);
        boolean flag = mfGoodsService.update(goods, updateWrapper);


        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("产品(%s)更新失败。", dto.getGoodsName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除产品
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeGoods(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfGoodsService.isExistsData(guids, MfGoods.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfGoods> updateWrapper = mfGoodsService.csbrUpdateWrapper(MfGoods.class);
        updateWrapper.in(MfGoods::getGuid, guids);
        boolean flag =  mfGoodsService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

}
