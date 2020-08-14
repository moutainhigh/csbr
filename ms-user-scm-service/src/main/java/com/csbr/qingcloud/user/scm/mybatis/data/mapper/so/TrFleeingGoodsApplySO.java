package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 举证窜货申请查询实体.
 *
 * @author Huanglh
 * @since 2020-08-11
 */

@Data
@ApiModel(value = "举证窜货申请查询实体")
public class TrFleeingGoodsApplySO extends BasePageDTO {

    /** 审批编号 */
    @ApiModelProperty("审批编号")
    private String applyCode;

}
