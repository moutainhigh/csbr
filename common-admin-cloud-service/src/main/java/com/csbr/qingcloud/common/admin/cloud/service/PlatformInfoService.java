package com.csbr.qingcloud.common.admin.cloud.service;

import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformAddDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformQueryDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.dto.platform.PlatformUpdateDTO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.PlatformVO;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.qingcloud.common.admin.cloud.domain.vo.PlatfromDetailVO;


import javax.validation.Valid;
import java.util.List;

/**
 * @program: auth
 * @description: 平台资料信息
 * @author: yio
 * @create: 2020-07-09 10:29
 **/
public interface PlatformInfoService {

    /**
     * 新增平台资料
     * @param dto
     * @return
     */
    CommonRes<Boolean> addPlatformInfo(@Valid PlatformAddDTO dto);

    /**
     * 更新平台资料信息
     * @param dto
     * @return
     */
    CommonRes<Boolean> updatePlatformInfo(PlatformUpdateDTO dto);

    /**
     * 删除平台资料信息
     * @param guids
     * @return
     */
    CommonRes<Boolean> removePlatformInfo(List<String> guids);

    /**
     * 查询平台资料信息
     * @param dto
     * @return
     */
    PageListVO<PlatformVO> getPlatformInfo(PlatformQueryDTO dto);

    /**
     * 获取平台明细
     * @param guid
     * @return
     */
    PlatfromDetailVO getPlatformInfoDetail(String guid);

    CommonRes test();
}
