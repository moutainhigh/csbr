
package com.csbr.qingcloud.user.scm.mybatis.data.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.mybatis.entity.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * 商品资料实体.
 *
 * @author Huanglh
 * @since 2020-07-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class MfGoods extends BaseDO {
    
    /** 大包装条码 */
    private String bigBcd;

    /** 大包装含量 */
    private Double bigUnitQty;

    /** 大包装单位 */
    private String bigUnitStyle;

    /** 业务状态(Y 有效；S：停用) */
    private String bizState;

    /** 品牌 */
    private String brand;

    /** 化学名称 */
    private String chemicalName;

    /** 创建时间 */
    private Timestamp createTime;

    /** 创建人 */
    private String createUserId;

    /** 创建人姓名 */
    private String createUserName;

    /** 备用日期1 */
    private Timestamp date1;

    /** 备用日期2 */
    private Timestamp date2;

    /** 备用日期3 */
    private Timestamp date3;

    /** 剂型 */
    private String dosageForm;

    /** 通用名称 */
    private String genericName;

    /** 商品编码 */
    private String goodsCode;

    /** 商品名称 */
    private String goodsName;

    /** 规格 */
    private String goodsSpec;

    /** 毛重(KG/公斤) */
    private Double grossWeight;

    /** 系统唯一标识 */
    @TableId
    private String guid;

    /** 高(cm/厘米) */
    private Double height;

    /** 助记码 */
    private String helpCode;

    /** 是否删除(Y 是；N 否 默认 N) */
    @TableLogic
    private String isDeleted;

    /** 长(cm/厘米) */
    private Double length;

    /** 小包装条码 */
    private String litBcd;

    /** 中包装条码 */
    private String middleBcd;

    /** 中包装含量 */
    private Double middleUnitQty;

    /** 中包装单位 */
    private String middleUnitStyle;

    /** 小包装含量 */
    private Double minUnitQty;

    /** 小包装单位 */
    private String minUnitStyle;

    /** 净重(KG/公斤) */
    private Double netWeight;

    /** 备用数字1 */
    private Double num1;

    /** 备用数字2 */
    private Double num2;

    /** 备用数字3 */
    private Double num3;

    /** 单价 */
    private Double price;

    /** 生产厂商 */
    private String producer;

    /** 产地 */
    private String productionAddress;

    /** 批准文号/注册证号 */
    private String registKey;

    /** 备注 */
    private String remark;

    /** 所属租户GUID */
    private String tenantGuid;
    
    /** 计量单位 */
    private String unitStyle;

    /** 修改时间 */
    private Timestamp updateTime;

    /** 修改人 */
    private String updateUserId;

    /** 修改姓名 */
    private String updateUserName;

    /** 备用字符1 */
    private String varchar1;

    /** 备用字符2 */
    private String varchar2;

    /** 备用字符3 */
    private String varchar3;

    /** 备用字符4 */
    private String varchar4;

    /** 宽(cm/厘米) */
    private Double width;

}