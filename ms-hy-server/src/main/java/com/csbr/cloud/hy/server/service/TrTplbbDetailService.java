package com.csbr.cloud.hy.server.service;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.hy.server.domain.vo.TrTplpoMainVo;
import com.csbr.cloud.hy.server.entity.TrTplbbDetailEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author L-ZP
 * @since 2020-04-27
 */
public interface TrTplbbDetailService extends IService<TrTplbbDetailEntity> {

    //订单明细表查询
    TrTplpoMainVo getBBDetailSelect(JSONObject param);

}
