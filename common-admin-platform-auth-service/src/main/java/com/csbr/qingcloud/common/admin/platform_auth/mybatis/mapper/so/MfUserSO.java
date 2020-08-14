package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;


/**
 * 用户查询实体.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Data
@MappedJdbcTypes(value = JdbcType.DATE,includeNullJdbcType = true)
@ApiModel(value = "用户查询实体")
public class MfUserSO extends BasePageDTO {

    /** 系统唯一标识 */
    private String guid;

    /** 身份证号码 */
    private String idCode;

    /** 是否初始账号(Y 是；N 否 默认 N) */
    private String isInit;

    /** 锁定(Y 是；N 否) */
    private String isLocked;

    /** 登录账号 */
    private String logonUser;

    /** 手机号 */
    private String mobileNo;

    /** 姓名 */
    private String name;

    /** 平台GUID */
    private String platformGuid;

    /** 注册码 */
    private String registrationCode;

    /** 业务状态(Y 有效；S 停用) */
    private String bizState;

}
