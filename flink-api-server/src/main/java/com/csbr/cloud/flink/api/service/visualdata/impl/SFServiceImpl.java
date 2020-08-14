package com.csbr.cloud.flink.api.service.visualdata.impl;

import com.alibaba.fastjson.JSON;
import com.csbr.cloud.flink.api.model.vo.visualdata.*;
import com.csbr.cloud.flink.api.mybatis.basedata.entity.PDeliverySum;
import com.csbr.cloud.flink.api.mybatis.basedata.entity.PStorageSum;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDetailsDTO;
import com.csbr.cloud.flink.api.mybatis.basedata.model.DeliveryDistributionDTO;
import com.csbr.cloud.flink.api.mybatis.basedata.model.WarehouseDetailsDTO;
import com.csbr.cloud.flink.api.mybatis.basedata.service.*;
import com.csbr.cloud.flink.api.service.visualdata.SFService;
import com.csbr.cloud.flink.api.util.BoardDataTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: flink-api-service
 * @description: 四方大屏数据服务
 * @author: Huanglh
 * @create: 2020-01-16 13:52
 **/
@Service
public class SFServiceImpl implements SFService {
    @Autowired
    private PStorageSumService pStorageSumService;
    @Autowired
    private PDeliverySumService pDeliverySumService;
    @Autowired
    private PStorageDeliveryRegionSumService pStorageDeliveryRegionSumService;
    @Autowired
    private CusDeliverySumService cusDeliverySumService;
    @Autowired
    private CusStorageSumService cusStorageSumService;

    /**
     * 依据传入的大屏数据类型返回对应的json字符串数据
     *
     * @param boardDataType
     * @return
     */
    @Override
    public String getJson(String boardDataType) {
        String res = null;
        BoardDataTypeEnum typeEnum = BoardDataTypeEnum.valueOf(boardDataType.toUpperCase());

        switch (typeEnum) {
            case SF_DELIVERYDETAILS:
                res = getDeliveryDetailsData();
                break;
            case SF_WAREHOUSEDETAILS:
                res = getWarehouseDetailsData();
                break;
            case SF_TRANSPORTCAPACITY:
                res = getSFTransportCapacityData();
                break;
            case SF_WAREHOUSECAPACITY:
                res = getSFWareHouseCapacityData();
                break;
            case SF_DELIVERYDISTRIBUTION:
                res = getDeliveryDistributionData();
                break;
            default:
                // 抛出找不到大屏数据类型枚举的错误
                throw new IllegalArgumentException("board data type enum not found!");
        }
        return res;
    }

    /**
     * 平台仓储能力
     *
     * @return
     */
    private String getSFWareHouseCapacityData() {
        SFWareHouseCapacityVO vo = new SFWareHouseCapacityVO();

        PStorageSum data = pStorageSumService.getNewest(null);

        BeanUtils.copyProperties(data, vo);

        return JSON.toJSONString(vo);
    }

    /**
     * 平台配送能力
     *
     * @return
     */
    private String getSFTransportCapacityData() {
        SFTransportCapacityVO vo = new SFTransportCapacityVO();

        PDeliverySum data = pDeliverySumService.getNewest(null);

        BeanUtils.copyProperties(data, vo);

        return JSON.toJSONString(vo);
    }

    /**
     * 平台仓库运力分布
     *
     * @return
     */
    private String getDeliveryDistributionData() {
        SFDeliveryDistributionVO vo = new SFDeliveryDistributionVO();
        List<DeliveryDistributionDTO> datas = pStorageDeliveryRegionSumService.getDeliveryDistribution();

        List<Map<String, String>> ylfb = new ArrayList<>();
        List<Map<String, String>> ck = new ArrayList<>();

        for (DeliveryDistributionDTO dto : datas) {
            Map<String, String> ylfbItem = new HashMap<>();
            Map<String, String> ckItem = new HashMap<>();

            ylfbItem.put("name", dto.getProvince());
            ylfbItem.put("value", dto.getTotleDelivery().toString());

            ckItem.put("_id", dto.getProvince());
            ckItem.put("qty", dto.getTotalCount().toString());
        }

        vo.setYlfb(ylfb);
        vo.setCk(ck);

        return JSON.toJSONString(vo);
    }

    /**
     * 运力明细
     *
     * @return
     */
    private String getDeliveryDetailsData() {
        List<SFDeliveryDetailsVO> vos = new ArrayList<>();
        List<DeliveryDetailsDTO> datas = cusDeliverySumService.getDeliveryDetails();

        for (DeliveryDetailsDTO dto : datas) {
            SFDeliveryDetailsVO vo = new SFDeliveryDetailsVO();
            BeanUtils.copyProperties(dto, vo);

            vos.add(vo);
        }

        return JSON.toJSONString(vos);
    }

    /**
     * 分仓明细
     *
     * @return
     */
    private String getWarehouseDetailsData() {
        List<SFWarehouseDetailsVO> vos = new ArrayList<>();
        List<WarehouseDetailsDTO> datas = cusStorageSumService.getWarehouseDetails();

        for (WarehouseDetailsDTO dto : datas) {
            SFWarehouseDetailsVO vo = new SFWarehouseDetailsVO();
            BeanUtils.copyProperties(dto, vo);

            vos.add(vo);
        }

        return JSON.toJSONString(vos);
    }
}
