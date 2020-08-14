package com.csbr.qingcloud.common.admin.platform_auth.service;

import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductAddDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductQueryDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.dto.product.ProductUpdateDTO;
import com.csbr.qingcloud.common.admin.platform_auth.domain.vo.ProductVO;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-common-admin-platform-auth-service
 * @description: 产品服务
 * @author: yio
 * @create: 2020-07-21 11:07
 **/
public interface ProductService {
    /**
     * 新增产品
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> addProduct(@Valid ProductAddDTO dto);

    /**
     * 修改产品
     *
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateProduct(ProductUpdateDTO dto);

    /**
     * 删除产品
     *
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeProduct(List<String> guids);

    /**
     * 查询产品
     *
     * @param dto
     * @return
     */
    PageListVO<ProductVO> getProduct(ProductQueryDTO dto);


}
