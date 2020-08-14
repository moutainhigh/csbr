package com.csbr.qingcloud.user.scm.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 商户显示对象
 * @author: yio
 * @create: 2020-07-30 10:35
 **/
@Data
@ApiModel("商户显示对象")
public class MerchantVO {

    /**
     * 系统唯一标识
     */
    @ApiModelProperty("系统唯一标识")
    private String guid;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String merchantName;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String abbreviation;

    /**
     * 经营者
     */
    @ApiModelProperty("经营者")
    private String manager;


    /**
     * 业务状态(Y 有效；S：停用)
     */
    @ApiModelProperty("业务状态(Y 有效；S：停用)")
    private String bizState = "Y";


    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String contactTel;

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
     * 是否自由人(Y 是；N 否 默认 Y)
     */
    @ApiModelProperty("是否自由人(Y 是；N 否 默认 Y)")
    private String isFreeMan;

    /**
     * 合作企业GUID
     */
    @ApiModelProperty("合作企业GUID")
    private String coOperativesGuid;

    /**
     * 合作企业名称
     */
    @ApiModelProperty("合作企业名称")
    private String coOperativesName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

}
