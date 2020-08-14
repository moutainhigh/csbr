package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.hy.server.domain.dto.TrTplpoMainDto;
import com.csbr.cloud.hy.server.domain.vo.TrTplpoMainVo;
import com.csbr.cloud.hy.server.entity.TrTplpoMainEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangheng
 * @since 2020-04-27
 */
public interface TrTplpoMainService extends IService<TrTplpoMainEntity> {

    /**
     * 新增物流订单
     */

    void postPoMainInsert(TrTplpoMainDto trTplpoMainDto);

    /**
     * 查询物流订单主表
     * @return
     */
    PageInfo<Map<String, Object>> getPoMainSelect(JSONObject obj);

    /**
     * 订单节点监控
     */
    Map<String, Object> getOrderMonitoring(JSONObject param);

    /**
     * 提货超时、到站/签收超时订单
     */
    PageInfo<Map<String, Object>> getTimeoutOrderSelect(JSONObject param);
}
