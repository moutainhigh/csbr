package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.enums.SystemError;
import com.csbr.cloud.common.exception.CsbrSystemException;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesflow.SalesFlowQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.salesindicators.StaffSalesIndicatorsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.*;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlowDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicators;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrStaffSalesIndicatorsDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.StaffSalesIndicatorsWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrStaffSalesIndicatorSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrStaffSalesIndicatorService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrStaffSalesIndicatorsDetailService;
import com.csbr.qingcloud.user.scm.service.StaffSalesIndicatorsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 人员销售指标服务
 * @author: yio
 * @create: 2020-08-06 18:20
 **/
@Service
public class StaffSalesIndicatorsServiceImpl implements StaffSalesIndicatorsService {

    @Autowired
    private CsbrBeanUtil csbrBeanUtil;
    @Autowired
    private TrStaffSalesIndicatorService trStaffSalesIndicatorService;

    @Autowired
    private TrStaffSalesIndicatorsDetailService trStaffSalesIndicatorsDetailService;
    public Date getDateByYearMonth(String yearMonth)
    {
        //转换成每月10号
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        try {
            Date date=sdf.parse(yearMonth+"10");
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CsbrSystemException(SystemError.DATA_GET_ERROR, String.format("日期格式转换异常（%s）。", e.getMessage()));

        }
    }
    @Override
    public PageListVO<StaffSalesIndicatorsVO> getStaffSalesIndicators(StaffSalesIndicatorsQueryDTO dto) {

        TrStaffSalesIndicatorSO so=csbrBeanUtil.convert(dto, TrStaffSalesIndicatorSO.class);
        if(StringUtils.isNotEmpty(dto.getYearMonth())){

            Date date=getDateByYearMonth(dto.getYearMonth());

            so.setDate(new java.sql.Date(date.getTime()));

        }

        PageListVO<StaffSalesIndicatorsWithDetailPOJO> lst=  trStaffSalesIndicatorService.getStaffSalesIndicatorsWithDetail(so);
        PageListVO<StaffSalesIndicatorsVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),StaffSalesIndicatorsVO.class));
        return pageListVO;
    }

    /**
     * 获取销售指标合计
     * @param dto
     * @return
     */
    @Override
    public StaffSalesIndicatorsSummaryVO getStaffSalesIndicatorsSummary(StaffSalesIndicatorsQueryDTO dto) {

        TrStaffSalesIndicatorSO so=csbrBeanUtil.convert(dto, TrStaffSalesIndicatorSO.class);
        StaffSalesIndicatorsSummaryVO vo=new StaffSalesIndicatorsSummaryVO();
        if(StringUtils.isNotEmpty(dto.getYearMonth())){

             Date date=getDateByYearMonth(dto.getYearMonth());
             so.setDate(new java.sql.Date(date.getTime()));

        }
        List<HashMap> lstmap=trStaffSalesIndicatorService.getStaffWithIndicators(so);
        //获取人员数、总销售指标，计算人均销售指标
        vo.setPeopleNum(lstmap.size());
        Double summaryIndicators=lstmap.stream().collect(Collectors.summingDouble(e->(Double)e.get("summarySalesIndicators")));
        vo.setSummarySalesIndicators(BigDecimal.valueOf(summaryIndicators));
        vo.setPerCapitaSalesIndicators(vo.getSummarySalesIndicators().divide(BigDecimal.valueOf(vo.getPeopleNum())));
        vo.setGoodsNum(trStaffSalesIndicatorService.getGoodsNum(so));
        return vo;
    }

    /**
     * 获取人员指标与明细组合数据
     * @param dto
     * @return
     */
    @Override
    public PageListVO<StaffSalesIndicatorsGroupVO> getStaffSalesIndicatorsGroup(StaffSalesIndicatorsQueryDTO dto) {
        LambdaQueryWrapper<TrStaffSalesIndicators> queryWrapper= trStaffSalesIndicatorService.csbrQueryWrapper(dto, TrStaffSalesIndicators.class);
        queryWrapper.orderByDesc(TrStaffSalesIndicators::getStartDate,TrStaffSalesIndicators::getEndDate);
        PageListVO<TrStaffSalesIndicators> lst = trStaffSalesIndicatorService.csbrPageList(dto
                , trStaffSalesIndicatorService.csbrQueryWrapper(dto, TrStaffSalesIndicators.class));
        PageListVO<StaffSalesIndicatorsGroupVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),StaffSalesIndicatorsGroupVO.class));
        //根据主表查询子表

        List<String> staffSalesIndicatorsGuids=pageListVO.getRecords().stream().map(StaffSalesIndicatorsGroupVO::getGuid).collect(Collectors.toList());
        LambdaQueryWrapper<TrStaffSalesIndicatorsDetail> detailQueryWrapper= Wrappers.lambdaQuery();
        detailQueryWrapper.in(TrStaffSalesIndicatorsDetail::getStaffSalesIndicatorsGuid,staffSalesIndicatorsGuids);
        List<TrStaffSalesIndicatorsDetail> detailLst=trStaffSalesIndicatorsDetailService.list(detailQueryWrapper);
        //以销售流量GUID为KEY转换成MAP
        Map<String,List<TrStaffSalesIndicatorsDetail>> map=detailLst.stream().collect(
                Collectors.groupingBy(TrStaffSalesIndicatorsDetail::getStaffSalesIndicatorsGuid,
                        Collectors.mapping(v->v,Collectors.toList())));

        for (StaffSalesIndicatorsGroupVO vo:pageListVO.getRecords()) {
            vo.setStaffSalesIndicatorsDetailVOs(csbrBeanUtil.convert(map.get(vo.getGuid()), StaffSalesIndicatorsDetailVO.class));
        }
        return pageListVO;


    }
}
