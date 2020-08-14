package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 人员查询结果对象
 * @author: yio
 * @create: 2020-08-03 16:07
 **/
@Data
@ApiModel("获取人员的返回值对象")
public class StaffVO {

    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 登录账号 */
    @ApiModelProperty("登录账号")
    private String logonUser;

    /** 姓名 */
    @ApiModelProperty("姓名")
    private String staffName;

    /** 手机号 */
    @ApiModelProperty("手机号")
    private String mobileNo;

    /** 人员类型(1 组织内成员；2 备案成员；默认 1) */
    @ApiModelProperty("人员类型(1 组织内成员；2 备案成员；默认 1)")
    private String staffType;

    /** 岗位名称列表*/
    @ApiModelProperty("岗位名称列")
    private List<String> positionNames;

    /** 角色名称列表 */
    @ApiModelProperty("角色名称列表")
    private List<String> roleNames;

    /** 业务状态(Y 有效；S：停用；默认 Y) */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    private String bizState;


    /** 是否企业初始人员(Y 是；N 否 默认 N) */
    @ApiModelProperty("是否企业初始人员(Y 是；N 否 默认 N)")
    private String isTenantInit;

    /** 工号 */
    @ApiModelProperty("工号")
    private String jobNumber;

    /** 所属上级GUID */
    @ApiModelProperty("所属上级GUID")
    private String leaderGuid;

    /** 商户GUID */
    @ApiModelProperty("商户GUID")
    private String merchantGuid;

    /** 所属组织GUID */
    @ApiModelProperty("所属组织GUID")
    private String organisationGuid;

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

    /** 现居住地址 */
    @ApiModelProperty("现居住地址")
    private String address;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 性别(M 男；F 女) */
    @ApiModelProperty("性别(M 男；F 女)")
    private String sex;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改时间 */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 用户Guid */
    @ApiModelProperty("用户Guid")
    private String userGuid;







}
