package com.csbr.cloud.hy.server.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.csbr.cloud.hy.server.entity.TrPlanSalesDetailEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangheng
 * @date 2020/5/13 13:31
 */
@Data
@ApiModel(value = "计划销量信息实体")
public class TrPlanSalesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统唯一标识
     */
    @ApiModelProperty("系统唯一标识")
    private String guid;

    /**
     * 所属租户GUID
     */
    @ApiModelProperty("所属租户GUID")
    private String tenantGuid;

    /**
     * 物流区域GUID
     */
    @ApiModelProperty("物流区域GUID")
    private String logAreaGuid;

    /**
     * 仓库GUID
     */
    @ApiModelProperty("仓库GUID")
    private String wareHouseGuid;

    /**
     * 销售部门GUID
     */
    @ApiModelProperty("销售部门GUID")
    private String deptGuid;

    /**
     * 销售部门名称
     */
    @ApiModelProperty("销售部门名称")
    private String deptName;

    /**
     * 计划年度
     */
    @ApiModelProperty("计划年度")
    private String planYear;

    /**
     * 状态
     K 保存；P 发布；E 作废；X 过期
     */
    @TableLogic
    @ApiModelProperty("状态 K 保存；P 发布；E 作废；X 过期")
    private String status;

    /**
     * 来源
     [
     {
     "Platform": "平台(01.医链云 02.药链云 03.四方云)",
     "PlatformType": "1.共有云 2.私有云",
     "ServiceObjects": "平台服务对象（传世、医药、七曜、新里程、哈药）",
     "DataSources": "WMS、TMS、SAP",
     "Guid": "平台唯一码"
     }
     ]
     */
    @ApiModelProperty("来源")
    private String applySource;

    /**
     * 备用字符1
     */
    private String varchar1;

    /**
     * 备用字符2
     */
    private String varchar2;

    /**
     * 备用字符3
     */
    private String varchar3;

    /**
     * 备用数字1
     */
    private BigDecimal num1;

    /**
     * 备用数字2
     */
    private BigDecimal num2;

    /**
     * 备用数字3
     */
    private BigDecimal num3;

    /**
     * 备用日期1
     */
    private Date date1;

    /**
     * 备用日期2
     */
    private Date date2;

    /**
     * 备用日期3
     */
    private Date date3;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date updateTime;

    /**
     * 明细表
     */
    private List<TrPlanSalesDetailEntity> trPlanSalesDetails;
}
