package com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 菜单查询实体.
 *
 * @author Huanglh
 * @since 2020-07-16
 */

@Data
@ApiModel(value = "菜单查询实体")
public class MfMenuSO extends BasePageDTO {

    /** 平台GUID */
    @ApiModelProperty("平台GUID")
    private String platformGuid;

    /** 应用端GUID */
    @ApiModelProperty("应用端GUID")
    private String appSideGuid;

    /** 产品GUID */
    @ApiModelProperty("产品GUID")
    private String productGuid;


    @ApiModelProperty("上级菜单Guid")
    /** 上级菜单Guid */
    private String parentGuid;
}
