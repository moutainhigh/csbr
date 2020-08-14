package com.csbr.cloud.hy.server.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhangheng
 * @date 2020/5/18 14:56
 */
@Data
public class StockDifferenceVo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 系统唯一标识
     */
    private String guid;

    /**
     * 所属仓库GUID
     */
    private String warehouseGuid;

    /**
     * 所属仓库名称
     */
    private String warehouseName;

    /**
     * 所属货主GUID
     */
    private String cargoOwnerGuid;

    /**
     * 所属货主名称
     */
    private String cargoOwnerName;

    /**
     * 商品GUID
     */
    private String goodsGuid;

    /**
     * 商品编码
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品规格
     */
    private String goodsSpec;

    /**
     * 包装单位
     */
    private String unit;

    /**
     * 包装数
     */
    private BigDecimal unitQty;

    /**
     * 注册证号/批准文号
     */
    private String registKey;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 批号
     */
    private String lot;

    /**
     * 生产日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date productionDate;

    /**
     * 有效期至
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date expireDate;

    /**
     * erp库存数量
     */
    private BigDecimal erpStockQty;

    /**
     * WMS库存数
     */
    private BigDecimal wmsStockQty;

}
