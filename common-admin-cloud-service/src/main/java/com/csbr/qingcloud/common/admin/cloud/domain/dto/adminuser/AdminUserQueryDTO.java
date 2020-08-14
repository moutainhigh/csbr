package com.csbr.qingcloud.common.admin.cloud.domain.dto.adminuser;

import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @program: ms-common-admin-cloud-service
 * @description: 平台用户查询参数
 * @author: yio
 * @create: 2020-07-20 12:16
 **/
@Data
@ApiModel(value = "平台用户查询参数")
public class AdminUserQueryDTO extends BasePageDTO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @LikeQuery
    private String logonUser;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @LikeQuery
    private String name;

    /** 业务状态(Y 有效；S：停用) */
    @ApiModelProperty("状态（Y 有效；S：停用；默认 Y）")
    @Pattern(regexp = "^Y|S$", message = "业务状态应该为Y、S中的值。")
    private String bizState;

    /** 手机号 */
    @ApiModelProperty("手机号")
    @LikeQuery
    private String mobileNo;

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 平台名称 */
    @ApiModelProperty("平台名称")
    @LikeQuery
    private String platformName;

    /** 开始创建时间 */
    @ApiModelProperty("开始创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "createTime")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "开始时间格式不正确。")
    private Date startTime;

    /** 结束创建时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "createTime")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    @Past(message = "结束时间格式不正确。")
    private Date endTime;

}
