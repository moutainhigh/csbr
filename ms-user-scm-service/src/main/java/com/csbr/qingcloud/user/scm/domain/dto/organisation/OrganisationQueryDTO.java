package com.csbr.qingcloud.user.scm.domain.dto.organisation;

import com.baomidou.mybatisplus.annotation.TableId;
import com.csbr.cloud.mybatis.annotations.CompareQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.enums.CompareQueryEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @program: ms-user-scm-service
 * @description: 组织资料查询参数
 * @author: yio
 * @create: 2020-07-30 16:16
 **/
@Data
@ApiModel("组织资料查询参数")
public class OrganisationQueryDTO extends BasePageDTO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 组织编码 */
    @ApiModelProperty("组织编码")
    private String organisationCode;

    /** 组织名称 */
    @ApiModelProperty("组织名称")
    private String organisationName;

    /** 开始创建时间 */
    @ApiModelProperty("开始创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.GE,field = "createTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "开始时间格式不正确。")
    private Date startTime;

    /** 结束创建时间 */
    @ApiModelProperty("结束创建时间（yyyy-MM-dd HH:mm:ss）")
    @CompareQuery(sign = CompareQueryEnum.LE,field = "createTime")
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Past(message = "结束时间格式不正确。")
    private Date endTime;
}
