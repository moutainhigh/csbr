package com.csbr.qingcloud.common.admin.platform_auth.service.impl;

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
import com.csbr.cloud.mybatis.util.ValidatorUtil;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.ProductVO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfProduct;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfProductService;
import com.csbr.qingcloud.common.admin.platform_auth.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 应用端服务实现
 * @author: yio
 * @create: 2020-07-21 10:09
 **/
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private MfProductService mfProductService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Autowired
    private ValidatorUtil validatorUtil;

    private void checkDuplicate(String productName,String guid)
    {
        //查看产品是否重复
        LambdaQueryWrapper<MfProduct> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(MfProduct::getProductName, productName)
                .select(MfProduct::getGuid);
        MfProduct ent=mfProductService.getOne(queryWrapper);
        if (ent!=null && (StringUtils.isEmpty(guid) || !ent.getGuid().equals(guid))) {
            throw new CsbrUserException(UserError.ACCOUNT_EXIST, String.format("产品(%s)已存在。", productName));
        }

    }

    /**
     * 新增产品
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> addProduct(@Valid ProductAddDTO dto) {

        //非空验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.OBJECT_IS_NULL, msg);
        }
        //查看账号是否重复
        checkDuplicate(dto.getProductName(),null);
        MfProduct product = csbrBeanUtil.convert(dto, MfProduct.class);
        mfProductService.csbrAddEntity(product);
        boolean flag = mfProductService.save(product);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_ADD_ERROR, String.format("产品(%s)新增失败。", dto.getProductName()));
        }
        return CommonRes.success(flag);
    }

    /**
     * 修改产品
     *
     * @param dto
     * @return
     */
    @Override
    public CommonRes<Boolean> updateProduct(ProductUpdateDTO dto) {
        //格式验证
        String msg = validatorUtil.validateRetrunAll(dto);
        if (StringUtils.isNotEmpty(msg)) {
            throw new CsbrUserException(UserError.FIELD_VERIFY_FAIL, msg);
        }

        //查找更新的数据是否存在

        if (!mfProductService.isExistsData(Arrays.asList(dto.getGuid()), MfProduct.class)) {
            throw new CsbrUserException(UserError.ACCOUNT_NOT_EXIST, String.format("产品(%s)不存在，无法更新。", dto.getProductName()));
        }

        checkDuplicate(dto.getProductName(),dto.getGuid());
        //更新数据
        LambdaUpdateWrapper<MfProduct> updateWrapper = mfProductService.csbrUpdateWrapper(MfProduct.class);
        updateWrapper.eq(MfProduct::getGuid, dto.getGuid());

        MfProduct product = csbrBeanUtil.convert(dto, MfProduct.class);
        boolean flag = mfProductService.update(product, updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_UPDATE_ERROR, String.format("产品(%s)更新失败。", dto.getProductName()));
        }

        return CommonRes.success(flag);
    }

    /**
     * 移除产品
     *
     * @param guids
     * @return
     */
    @Override
    public CommonRes<Boolean> removeProduct(List<String> guids) {
        //查找更新的数据是否存在
        if (!mfProductService.isExistsData(guids, MfProduct.class)) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "没有需要删除的数据。");
        }
        //删除数据
        LambdaUpdateWrapper<MfProduct> updateWrapper = mfProductService.csbrUpdateWrapper(MfProduct.class);
        updateWrapper.in(MfProduct::getGuid, guids);
        boolean flag = mfProductService.remove(updateWrapper);
        if (!flag) {
            throw new CsbrSystemException(SystemError.DATA_DEL_ERROR, "删除失败");
        }

        return CommonRes.success(flag);
    }

    /**
     * 获取产品
     *
     * @param dto
     * @return
     */
    @Override
    public PageListVO<ProductVO> getProduct(ProductQueryDTO dto) {

        PageListVO<MfProduct> lst = mfProductService.csbrPageList(dto
                , mfProductService.csbrQueryWrapper(dto, MfProduct.class));
        return csbrBeanUtil.convert(lst, PageListVO.class);

    }


}
