package com.csbr.qingcloud.user.scm.domain.dto.goods;

import com.csbr.cloud.mybatis.annotations.LikeQuery;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 产品资料查询参数
 * @author: yio
 * @create: 2020-08-06 17:00
 **/
@Data
@ApiModel("产品资料查询参数")
public class GoodsQueryDTO extends BasePageDTO {
    /** 企业GUID */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    @LikeQuery
    private String goodsCode;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    @LikeQuery
    private String goodsName;


}
