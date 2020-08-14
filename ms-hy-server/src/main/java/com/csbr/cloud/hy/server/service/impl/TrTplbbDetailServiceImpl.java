package com.csbr.cloud.hy.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.csbr.cloud.hy.server.domain.vo.TrTplpoMainVo;
import com.csbr.cloud.hy.server.entity.MfGoodsEntity;
import com.csbr.cloud.hy.server.entity.TrTplbbDetailEntity;
import com.csbr.cloud.hy.server.mapper.TrTplbbDetailMapper;
import com.csbr.cloud.hy.server.mapper.TrTplpoMainMapper;
import com.csbr.cloud.hy.server.service.TrTplbbDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
@Service
@Transactional
public class TrTplbbDetailServiceImpl extends ServiceImpl<TrTplbbDetailMapper, TrTplbbDetailEntity> implements TrTplbbDetailService {

    @Autowired
    private TrTplbbDetailMapper trTplbbDetailMapper;

    @Autowired
    private TrTplpoMainMapper trTplpoMainMapper;

    @Override
    public TrTplpoMainVo getBBDetailSelect(JSONObject param) {
        Map map = new HashMap();
        map.put("guid",param.getString("guid"));
        map.put("bBillNo",param.getString("bBillNo"));
        Map poMainInfo = trTplpoMainMapper.getPoMainInfo(map);
        String json = JSON.toJSONString(poMainInfo, SerializerFeature.WriteMapNullValue);//map转String
        JSONObject jsonObject = JSON.parseObject(json);//String转json
        TrTplpoMainVo trTplpoMainVo = jsonObject.toJavaObject(jsonObject, TrTplpoMainVo.class);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        //行号
        if(StringUtils.isNotEmpty(param.getString("rowNo"))){
            queryWrapper.eq("row_no", param.getString("rowNo"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsGuid"))){
            queryWrapper.eq("goods_guid", param.getString("goodsGuid"));
        }
        if(StringUtils.isNotEmpty(param.getString("goodsCode"))){
            queryWrapper.eq("goods_code", param.getString("goodsCode"));
        }
        if(StringUtils.isNotEmpty(param.getString("lot"))){
            queryWrapper.eq("lot", param.getString("lot"));
        }
        List<TrTplbbDetailEntity> trTplbbDetails = trTplbbDetailMapper.selectList(queryWrapper);
        trTplpoMainVo.setTrTplbbDetail(trTplbbDetails);
//        TrTplpoMainVo trTplpoMainVo = new TrTplpoMainVo();
//        trTplpoMainVo.setMainPoGuid(jsonObject.getString("mainPoGuid"));
//        trTplpoMainVo.setTenantGuid(jsonObject.getString("tenantGuid"));
//        trTplpoMainVo.setCorpGuid(jsonObject.getString("corpGuid"));
//        trTplpoMainVo.setTplpoNo(jsonObject.getString("tplPoNo"));
//        trTplpoMainVo.setMainState(jsonObject.getString("mainState"));
//        trTplpoMainVo.setDeliverGuid(jsonObject.getString("deliverGuid"));
//        trTplpoMainVo.setDeliverName(jsonObject.getString("deliverName"));
        return trTplpoMainVo;
    }
}
