package com.csbr.qingcloud.common.admin.cloud.mybatis.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 平台资料查询实体.
 *
 * @author Huanglh
 * @since 2020-07-15
 */

@Data
@ApiModel(value = "平台资料查询实体")
public class MfPlatformSO extends BasePageDTO {

    /** 名称 */
    @ApiModelProperty("名称")
    private String platformName;

}
