package com.csbr.qingcloud.user.scm.mybatis.mapper.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 保存在rides的人员信息
 * @author: yio
 * @create: 2020-08-05 14:48
 **/
@Data
public class RedisStaffPOJO implements Serializable {

    /**
     * 员工GUID
     */
    private String staffGuid;
    /**
     * 组织GUID
     */
    private String organisationGuid;
    /**
     * 组织编码
     */
    private String organisationCode;
    /**
     * 组织名称
     */
    private String organisationName;

    /**
     * 子组织GUID列表
     */
    private List<String> childOranisationGuids;
}
