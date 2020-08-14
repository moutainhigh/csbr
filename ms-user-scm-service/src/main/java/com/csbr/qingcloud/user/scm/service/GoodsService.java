package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.customer.CustomerUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.goods.GoodsUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.vo.GoodsVO;
import com.csbr.qingcloud.user.scm.domain.vo.PositionVO;

import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 产品资料服务
 * @author: yio
 * @create: 2020-08-06 17:20
 **/
public interface GoodsService {

    /**
     * 查询产品
     * @param dto
     * @return
     */
    PageListVO<GoodsVO> getGoods(GoodsQueryDTO dto);

    /**
     * 产品新增服务
     * @param dto
     * @return
     */
    CommonRes<Boolean> addGoods(GoodsAddDTO dto);


    /**
     * 修改产品
     * @param dto
     * @return
     */
    CommonRes<Boolean> updateGoods(GoodsUpdateDTO dto);

    /**
     * 移除产品
     * @param guids
     * @return
     */
    CommonRes<Boolean> removeGoods(List<String> guids);
}
