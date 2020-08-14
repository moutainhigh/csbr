package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.CustomerSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsSummaryVO;
import com.csbr.qingcloud.user.scm.domain.vo.CustomerSalesIndicatorsVO;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrCustomerSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrCustomerSalesIndicatorService;
import com.csbr.qingcloud.user.scm.service.CustomerSalesIndicatorsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 客户销售指标服务
 * @author: yio
 * @create: 2020-08-07 14:04
 **/
@Service
public class CustomerSalesIndicatorsServiceImpl implements CustomerSalesIndicatorsService {

    @Autowired
    private TrCustomerSalesIndicatorService trCustomerSalesIndicatorService;

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    /**
     * 获取客户销售指标分页列表
     * @param dto
     * @return
     */
    @Override
    public PageListVO<CustomerSalesIndicatorsVO> getCustomerSalesIndicators(CustomerSalesIndicatorsQueryDTO dto) {
        LambdaQueryWrapper<TrCustomerSalesIndicators> queryWrapper=trCustomerSalesIndicatorService.csbrQueryWrapper(dto, TrCustomerSalesIndicators.class);
        if(StringUtils.isNotEmpty(dto.getYearMonth())){
            //转换成每月10号
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(dto.getYearMonth()+"10");
                queryWrapper.le(TrCustomerSalesIndicators::getStartDate,date)
                        .ge(TrCustomerSalesIndicators::getEndDate,date);
                PageListVO<TrCustomerSalesIndicators> lst= trCustomerSalesIndicatorService.csbrPageList(dto,queryWrapper);

                PageListVO<CustomerSalesIndicatorsVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
                pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),CustomerSalesIndicatorsVO.class));
                return pageListVO;
            } catch (ParseException e) {
                e.printStackTrace();
                throw new CsbrSystemException(SystemError.DATA_GET_ERROR, String.format("日期格式转换异常（%s）。", e.getMessage()));

            }

        }
        return null;
    }

    /**
     * 获取客户销售指标合计
     * @param dto
     * @return
     */
    @Override
    public CustomerSalesIndicatorsSummaryVO getCustomerSalesIndicatorsSummary(CustomerSalesIndicatorsQueryDTO dto) {
        QueryWrapper<TrCustomerSalesIndicators> queryWrapper=trCustomerSalesIndicatorService.csbrBaseQueryWrapper(dto, TrCustomerSalesIndicators.class);

        if(StringUtils.isNotEmpty(dto.getYearMonth())){
            CustomerSalesIndicatorsSummaryVO vo=new CustomerSalesIndicatorsSummaryVO();
            //转换成每月10号
            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
            try {
                Date date=sdf.parse(dto.getYearMonth()+"10");
                queryWrapper.le("start_date",date)
                        .ge("end_date",date)
                        .groupBy("customer_guid")
                        .select("customer_guid,sum(sales_indicators) as IndicatorsTotal");
                List<Map<String, Object>> lstmap=trCustomerSalesIndicatorService.listMaps(queryWrapper);
                //获取客户数、总销售指标，计算平均销售指标
                vo.setCustomerNum(lstmap.size());
                Double summaryIndicators=lstmap.stream().collect(Collectors.summingDouble(e->(Double)e.get("IndicatorsTotal")));
                vo.setSummarySalesIndicators(BigDecimal.valueOf(summaryIndicators));
                vo.setAvgSalesIndicators(vo.getSummarySalesIndicators().divide(BigDecimal.valueOf(vo.getCustomerNum())));

                queryWrapper=trCustomerSalesIndicatorService.csbrBaseQueryWrapper(dto, TrCustomerSalesIndicators.class);
                //获取产品数
                queryWrapper.le("start_date",date)
                        .ge("end_date",date)
                        .select("count(DISTINCT(goods_guid)) as goodsNum");
                Map<String,Object> map=trCustomerSalesIndicatorService.getMap(queryWrapper);
                vo.setGoodsNum((Integer) map.get("goodsNum"));
                return vo;
            } catch (ParseException e) {
                e.printStackTrace();
                throw new CsbrSystemException(SystemError.DATA_GET_ERROR, String.format("日期格式转换异常（%s）。", e.getMessage()));

            }

        }
        return null;
    }
}
