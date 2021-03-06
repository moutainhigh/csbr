package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 组织显示对象
 * @author: yio
 * @create: 2020-07-30 17:33
 **/
@Data
@ApiModel("商户显示对象")
public class OrganisationVO {

    /**
     * 系统唯一标识
     */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 企业GUID
     */
    @ApiModelProperty("企业GUID")
    private String tenantGuid;


    /**
     * 组织编码
     */
    @ApiModelProperty("组织编码")
    private String organisationCode;

    /**
     * 组织名称
     */
    @ApiModelProperty("组织名称")
    private String organisationName;

    /**
     * 省份(编号)
     */
    @ApiModelProperty("省份(编号)")
    private String province;

    /**
     * 城市(编号)
     */
    @ApiModelProperty("城市(编号)")
    private String city;

    /**
     * 区/县/乡(编号)
     */
    @ApiModelProperty("区/县/乡(编号)")
    private String district;

    /**
     * 地点(省/市/区 文本)
     */
    @ApiModelProperty("地点(省/市/区 文本)")
    private String venue;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 业务状态(Y 有效；S：停用；默认 Y)
     */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    private String bizState;



    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createUserId;

    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;


    /**
     * 是否删除(Y 是；N 否 默认 N)
     */
    @ApiModelProperty("是否删除(Y 是；N 否 默认 N)")
    private String isDeleted;


    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /**
     * 修改姓名
     */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /**
     * 上级组织GUID
     */
    @ApiModelProperty("上级组织GUID")
    private String parentGuid;

    /**
     * 序号
     */
    @ApiModelProperty("序号")
    private String orderNum;
}
