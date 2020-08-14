package com.csbr.qingcloud.user.scm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.csbr.cloud.common.util.CsbrBeanUtil;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.salesflow.SalesFlowQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.*;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrOrderDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlow;
import com.csbr.qingcloud.user.scm.mybatis.data.entity.TrSalesFlowDetail;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.pojo.SalesFlowWithDetailPOJO;
import com.csbr.qingcloud.user.scm.mybatis.data.mapper.so.TrSalesFlowSO;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSalesFlowDetailService;
import com.csbr.qingcloud.user.scm.mybatis.data.service.TrSalesFlowService;
import com.csbr.qingcloud.user.scm.service.SalesFlowService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: ms-user-scm-service
 * @description: 销售流向服务
 * @author: yio
 * @create: 2020-08-07 14:58
 **/
@Service
public class SalesFlowServiceImpl implements SalesFlowService {

    @Autowired
    private TrSalesFlowService trSalesFlowService;

    @Autowired
    private TrSalesFlowDetailService trSalesFlowDetailService;
    @Autowired
    private CsbrBeanUtil csbrBeanUtil;

    @Override
    public PageListVO<SalesFlowVO> getSalesFlow(SalesFlowQueryDTO dto) {
        PageListVO<SalesFlowWithDetailPOJO> lst =trSalesFlowService.getTrSalesFlowsWithDetail(csbrBeanUtil.convert(dto, TrSalesFlowSO.class));

        PageListVO<SalesFlowVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),SalesFlowVO.class));
        return pageListVO;


    }

    @Override
    public SalesFlowSummaryVO getSalesFlowSummary(SalesFlowQueryDTO dto) {
        QueryWrapper<TrSalesFlow> queryWrapper=trSalesFlowService.csbrBaseQueryWrapper(dto, TrSalesFlow.class);


        SalesFlowSummaryVO vo=new SalesFlowSummaryVO();
        TrSalesFlowSO so=csbrBeanUtil.convert(dto, TrSalesFlowSO.class);
        List<HashMap> lstmap=trSalesFlowService.getCustomerWithAmount(so);
        //获取客户数、总核算金额
        vo.setCustomerNum(lstmap.size());
        Double accountingAmount=lstmap.stream().collect(Collectors.summingDouble(e->(Double)e.get("summaryAccountingAmount")));
        vo.setSummaryAccountingAmount(BigDecimal.valueOf(accountingAmount));

        queryWrapper=trSalesFlowService.csbrBaseQueryWrapper(dto, TrSalesFlow.class);
        //获取产品数
        vo.setGoodsNum(trSalesFlowService.getGoodsNum(so));
        return vo;

    }


    /**
     * 获取销售与商品明细组合数据
     * @param dto
     * @return
     */
    @Override
    public PageListVO<SalesFlowGroupVO> getSalesFlowGroup(SalesFlowQueryDTO dto) {
        LambdaQueryWrapper<TrSalesFlow> queryWrapper= trSalesFlowService.csbrQueryWrapper(dto, TrSalesFlow.class);
        queryWrapper.orderByDesc(TrSalesFlow::getSalesDate);
        PageListVO<TrSalesFlow> lst = trSalesFlowService.csbrPageList(dto
                , trSalesFlowService.csbrQueryWrapper(dto, TrSalesFlow.class));
        PageListVO<SalesFlowGroupVO> pageListVO= csbrBeanUtil.convert(lst,PageListVO.class);
        pageListVO.setRecords(csbrBeanUtil.convert(pageListVO.getRecords(),SalesFlowGroupVO.class));
        //根据主表查询子表

        List<String> saleFlowGuids=pageListVO.getRecords().stream().map(SalesFlowGroupVO::getGuid).collect(Collectors.toList());
        LambdaQueryWrapper<TrSalesFlowDetail> detailQueryWrapper= Wrappers.lambdaQuery();
        detailQueryWrapper.in(TrSalesFlowDetail::getSalesFlowGuid,saleFlowGuids);
        List<TrSalesFlowDetail> detailLst=trSalesFlowDetailService.list(detailQueryWrapper);
        //以销售流量GUID为KEY转换成MAP
        Map<String,List<TrSalesFlowDetail>> map=detailLst.stream().collect(
                Collectors.groupingBy(TrSalesFlowDetail::getSalesFlowGuid,
                        Collectors.mapping(v->v,Collectors.toList())));

        for (SalesFlowGroupVO vo:pageListVO.getRecords()) {
            vo.setSalesFlowGoodsVOs(csbrBeanUtil.convert(map.get(vo.getGuid()), SalesFlowGoodsVO.class));
        }
        return pageListVO;


    }
}
