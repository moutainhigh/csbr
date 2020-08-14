package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: ms-user-scm-service
 * @description: 产品显示对象
 * @author: yio
 * @create: 2020-08-06 17:07
 **/
@Data
@ApiModel("产品显示对象")
public class GoodsVO {

    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 计量单位 */
    private String unitStyle;

    /** 生产厂商 */
    private String producer;

    /** 大包装含量 */
    private Double bigUnitQty;


    /** 单价 */
    private Double price;
}
