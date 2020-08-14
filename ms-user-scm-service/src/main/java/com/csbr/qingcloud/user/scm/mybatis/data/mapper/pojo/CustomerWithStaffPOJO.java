package com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo;

import com.csbr.qingcloud.user.scm.mybatis.data.entity.MfCustomer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: ms-user-scm-service
 * @description: 客户包含人员资料
 * @author: yio
 * @create: 2020-08-06 15:54
 **/
@Data
public class CustomerWithStaffPOJO  extends MfCustomer {
    /** 所属人员工号 */
    private String jobNumber;

    /** 所属人员姓名 */
    private String staffName;

}
