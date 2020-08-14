package com.csbr.qingcloud.common.admin.cloud.mybatis.mapper.so;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.JDBCType;
import java.util.Date;


/**
 * 平台用户查询实体.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Data
@ApiModel(value = "平台用户查询实体")
@MappedJdbcTypes(value = JdbcType.DATE,includeNullJdbcType = true)
public class MfAdminUserSO extends BasePageDTO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String logonUser;

    /** 密码 */
    @ApiModelProperty("密码")
    private String pwd;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String name;

    /** 业务状态(Y 有效；N：无效) */
    @ApiModelProperty("状态（Y 有效；N：无效；默认 Y）")
    private String bizState;

    /** 手机号 */
    private String mobileNo;

    /** 平台GUID */
    private String platformGuid;

    /** 平台名称 */
    @ApiModelProperty("平台名称")
    private String platformName;

    /** 开始创建时间 */
    private Date startTime;

    /** 结束创建时间 */
    private Date endTime;

}
