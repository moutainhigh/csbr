package com.csbr.qingcloud.user.scm.domain.dto.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @program: ms-user-scm-service
 * @description: 商品新增参数
 * @author: yio
 * @create: 2020-08-10 17:45
 **/
@Data
@ApiModel("商品新增参数")
public class GoodsAddDTO {

    /**
     * 企业GUID
     */
    @ApiModelProperty("企业GUID")
    @NotBlank(message = "企业GUID不能为空。")
    private String tenantGuid;

    /**
     * 商品编码
     */
    @ApiModelProperty("商品编码")
    @NotBlank(message = "商品编码不能为空。")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    @NotBlank(message = "商品名称不能为空。")
    private String goodsName;


    /**
     * 剂型
     */
    @ApiModelProperty("剂型")
    private String dosageForm;

    /**
     * 规格
     */
    @ApiModelProperty("规格")
    private String goodsSpec;



    /**
     * 计量单位
     */
    @ApiModelProperty("计量单位")
    private String unitStyle;

    /**
     * 大包装含量
     */
    @ApiModelProperty("大包装含量")
    private Double bigUnitQty;

    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private Double price;

    /**
     * 生产厂商
     */
    @ApiModelProperty("生产厂商")
    private String producer;

    /**
     * 产地
     */
    @ApiModelProperty("产地")
    private String productionAddress;

    /**
     * 批准文号/注册证号
     */
    private String registKey;

    /**
     * 备注
     */
    private String remark;



}