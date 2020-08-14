package com.csbr.qingcloud.user.scm.mybatis.data.mapper.so;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import com.csbr.cloud.mybatis.entity.BasePageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 商品资料查询实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@ApiModel(value = "商品资料查询实体")
public class MfGoodsSO extends BasePageDTO {

    /** 商品编码 */
    @ApiModelProperty("商品编码")
    private String goodsCode;

    /** 商品名称 */
    @ApiModelProperty("商品名称")
    private String goodsName;

}
