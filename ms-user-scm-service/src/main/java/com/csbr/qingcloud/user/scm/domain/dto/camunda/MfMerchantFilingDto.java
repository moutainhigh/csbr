package com.csbr.qingcloud.user.scm.domain.dto.camunda;

import com.csbr.qingcloud.user.scm.mybatis.entity.MfMerchantFiling;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangheng
 * @date 2020/8/6 12:12
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="商户备案对象")
@Data
public class MfMerchantFilingDto extends MfMerchantFiling {

    @ApiModelProperty(value="流程实例ID")
    private String processInstanceId;

    @ApiModelProperty(value="任务ID")
    private String taskId;
}
