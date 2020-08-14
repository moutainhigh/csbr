package com.csbr.qingcloud.user.scm.domain.dto.staff;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 人员查询参数
 * @author: yio
 * @create: 2020-07-31 14:00
 **/
@Data
@ApiModel("人员查询参数")
public class StaffQueryDTO extends BasePageDTO {

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @LikeQuery
    private String logonUser;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @LikeQuery
    private String staffName;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String mobileNo;

    /** 人员类型(1 组织内成员；2 备案成员；默认 1) */
    @ApiModelProperty("人员类型(1 组织内成员；2 备案成员；默认 1)")
    @Pattern(regexp = "^1|2$", message = "人员类型为1、2中的值。")
    private String staffType;


    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState;




}
