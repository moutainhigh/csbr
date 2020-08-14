package com.csbr.qingcloud.user.scm.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 订单显示对象
 * @author: yio
 * @create: 2020-08-12 11:59
 **/
@Data
@ApiModel("订单显示对象")
public class OrderVO {
    /** 系统唯一标识 */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /** 所属租户GUID */
    @ApiModelProperty("所属租户GUID")
    private String tenantGuid;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    private String orderNo;

    /** 制单时间 */
    @ApiModelProperty("制单时间")
    private Timestamp inputTime;

    /** 组织GUID */
    @ApiModelProperty("组织GUID")
    private String organisationGuid;

    /** 客户GUID */
    @ApiModelProperty("客户GUID")
    private String customerGuid;

    /** 客户编号 */
    @ApiModelProperty("客户编号")
    private String customerCode;

    /** 客户联系人 */
    @ApiModelProperty("客户联系人")
    private String customerName;

    /** 客户联系电话 */
    @ApiModelProperty("客户联系电话")
    private String contactTel;

    /** 客户所在省(编号) */
    @ApiModelProperty("客户所在省(编号)")
    private String province;

    /** 客户所在市(编号) */
    @ApiModelProperty("客户所在市(编号)")
    private String city;

    /** 客户所在区(编号) */
    @ApiModelProperty("客户所在区(编号)")
    private String district;

    /** 客户所在地(省/市/区 文本) */
    @ApiModelProperty("客户所在地(省/市/区 文本)")
    private String venue;

    /** 客户地址 */
    @ApiModelProperty("客户地址")
    private String address;

    /** 客户终端属性 */
    @ApiModelProperty("客户终端属性")
    private String terminalProperty;

    /** 品规数 */
    @ApiModelProperty("品规数")
    private Integer goodsSpecNum;

    /** 总数量 */
    @ApiModelProperty("总数量")
    private Double totalQty;

    /** 总金额 */
    @ApiModelProperty("总金额")
    private Double totalAmount;

    /** 明细条数 */
    @ApiModelProperty("明细条数")
    private Integer detailNum;

    /** 备注 */
    @ApiModelProperty("备注")
    private String memo;

    /** 创建人 */
    @ApiModelProperty("创建人")
    private String createUserId;

    /** 创建人姓名 */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /** 修改人 */
    @ApiModelProperty("修改人")
    private String updateUserId;

    /** 修改姓名 */
    @ApiModelProperty("修改姓名")
    private String updateUserName;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Timestamp updateTime;

    private List<OrderDetailVO> orderDetailVOs;

}
