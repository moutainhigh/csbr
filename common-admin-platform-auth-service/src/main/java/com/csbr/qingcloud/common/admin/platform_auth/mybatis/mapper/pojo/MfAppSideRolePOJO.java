package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: common-admin-platform-auth-service
 * @description: 应用端对应的角色对象
 * @author: yio
 * @create: 2020-07-29 14:04
 **/
@Data
public class MfAppSideRolePOJO {

    /**
     * 应用端GUID
     */
    private String appSideGuid;

    /**
     * 应用端名称
     */
    private String appSideName;

    /**
     * 用户GUID
     */
    private String userGuid;
    /**
     * 角色GUID
     */
    private String roleGuid;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 序号
     */
    private Integer orderNum;

    /**
     * 业务状态(Y 有效；S：停用；)
     */
    private String bizState;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人
     */
    private String updateUserId;

    /**
     * 修改姓名
     */
    private String updateUserName;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 修改时间
     */
    private Timestamp updateTime;


}
