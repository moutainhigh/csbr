package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.StaffQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerVO;
import com.csbr.qingcloud.user.scm.domain.vo.StaffVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 客户服务
 * @author: yio
 * @create: 2020-08-06 13:48
 **/
public interface CustomerService {

    /**
     * 查询人员
     *
     * @param dto
     * @return
     */
    PageListVO<CustomerVO> getCustomer(CustomerQueryDTO dto);

    /**
     * 客户新增服务
     * @param dto
     * @return
     */
    CommonRes<Boolean> addCustomer(CustomerAddDTO dto);


    /**
     * 修改客户
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateCustomer(CustomerUpdateDTO dto);

    /**
     * 移除客户
     * @param guids
     * @return
     */
     CommonRes<Boolean> removeCustomer(List<String> guids);
}
