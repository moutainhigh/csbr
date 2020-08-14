package com.csbr.qingcloud.user.scm.service;

import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.organisation.OrganisationUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionQueryDTO;
import com.csbr.qingcloud.user.scm.domain.dto.position.PositionUpdateDTO;
import com.csbr.qingcloud.user.scm.domain.vo.OrganisationVO;
import com.csbr.qingcloud.user.scm.domain.vo.PositionVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @program: ms-user-scm-service
 * @description: 岗位服务
 * @author: yio
 * @create: 2020-07-31 11:51
 **/
public interface PositionService {

    /**
     * 新增岗位
     * @param dto
     * @return
     */
    CommonRes<Boolean> addPosition(@Valid PositionAddDTO dto);

    /**
     * 修改岗位
     * @param dto
     * @return
     */
    CommonRes<Boolean> updatePosition(PositionUpdateDTO dto);

    /**
     * 删除岗位
     * @param guids
     * @return
     */
    CommonRes<Boolean> removePosition(List<String> guids);

    /**
     * 查询岗位
     * @param dto
     * @return
     */
    PageListVO<PositionVO> getPosition(PositionQueryDTO dto);

}
