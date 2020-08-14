package com.csbr.qingcloud.user.scm.domain.dto.staff;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 人员新增参数
 * @author: yio
 * @create: 2020-07-31 13:58
 **/
@Data
@ApiModel("人员新增参数")
public class StaffAddDTO {
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 工号 */
    @ApiModelProperty("工号")
    private String jobNumber;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    @NotBlank(message = "登录账号不能为空。")
    private String logonUser;

    /** 姓名 */
    @ApiModelProperty("姓名")
    @NotBlank(message = "姓名不能为空。")
    private String staffName;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空。")
    private String pwd;

    /** 助记码 */
    @ApiModelProperty("助记码")
    private String helpCode;

    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    @Pattern(regexp = "^Y|S$", message = "状态应该为Y、S中的值。")
    private String bizState="Y";

    /** 手机号 */
    @NotBlank(message = "手机号不能为空。")
    private String mobileNo;

    /** 电子邮件 */
    @ApiModelProperty("电子邮件")
    private String email;


    /** 是否企业初始人员(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否企业初始人员(Y 是；N 否 默认 N)")
    @Pattern(regexp = "^Y|N$", message = "是否企业初始人员应该为Y、N中的值。")
    private String isTenantInit="N";

    /** 所属上级GUID */
    @ApiModelProperty("所属上级GUID")
    private String leaderGuid;

    /** 商户GUID */
    @ApiModelProperty("商户GUID")
    private String merchantGuid;

    /** 所属组织GUID */
    @ApiModelProperty("所属组织GUID")
    private String organisationGuid;

    /** 岗位列表GUID */
    @ApiModelProperty("岗位列表GUID")
    private List<String> positionGuids;

    /** 省份(编号) */
    @ApiModelProperty("省份(编号)")
    private String province;

    /** 城市(编号) */
    @ApiModelProperty("城市(编号)")
    private String city;

    /** 区/县/乡(编号) */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /** 地点(省/市/区 文本) */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /** 现居住地 */
    @ApiModelProperty("现居住地")
    private String address;

    /** 性别(M 男；F 女) */
    @ApiModelProperty("性别(M 男；F 女)")
    @Pattern(regexp = "^M|F$", message = "性别应该为M、F中的值。")
    private String sex;



    /** 人员类型(1 组织内成员；2 备案成员；默认 1) */
    @ApiModelProperty("人员类型(1 组织内成员；2 备案成员；默认 1)")
    @Pattern(regexp = "^1|2$", message = "人员类型为1、2中的值。")
    private String staffType="1";

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;




    /** 微信号 */
    private String weChatNo;

    /** 微信OPENID */
    private String weChatOpentId;

    /** 身份证号 */
    private  String idCode;

    /** 角色GUID */
    @ApiModelProperty("角色GUID")
    private List<String> roleGuids;
}
