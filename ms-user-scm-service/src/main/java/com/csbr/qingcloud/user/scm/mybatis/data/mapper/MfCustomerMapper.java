package com.csbr.qingcloud.user.scm.mybatis.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.CustomerWithStaffPOJO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.MfCustomerSO;

/**
 * 客户资料实体持久化接口.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Component
@Mapper
@Repository
public interface MfCustomerMapper extends BaseMapper<MfCustomer> {
    /**
     * 分页查询客户资料信息.
     *
     * @param so 查询条件
     *
     * @return 客户资料查询结果
     */
    <D extends BasePageDTO> IPage<MfCustomer> mapperPageMfCustomers(Page<?> page, D so);

    <D extends BasePageDTO> IPage<CustomerWithStaffPOJO> getCustomersWithStaff(Page<?> page,MfCustomerSO so);
}
