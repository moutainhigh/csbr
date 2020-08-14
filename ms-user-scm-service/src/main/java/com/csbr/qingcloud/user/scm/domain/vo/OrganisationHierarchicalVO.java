package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.entity.HierarchicalVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 组织层级显示
 * @author: yio
 * @create: 2020-07-30 17:40
 **/
@Data
@ApiModel("组织层级显示")
public class OrganisationHierarchicalVO extends HierarchicalVO {

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
     * 业务状态(Y 有效；S：停用；默认 Y)
     */
    @ApiModelProperty("业务状态(Y 有效；S：停用；默认 Y)")
    private String bizState;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Timestamp updateTime;

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
