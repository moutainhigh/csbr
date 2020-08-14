package com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csbr.cloud.common.enums.UserError;
import com.csbr.cloud.common.exception.CsbrUserException;
import com.csbr.cloud.mybatis.entity.BasePageDTO;
import com.csbr.cloud.mybatis.entity.PageListVO;
import com.csbr.cloud.mybatis.service.impl.CsbrServiceImpl;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfAppSideRolePOJO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.pojo.MfUserRolePOJO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csbr.qingcloud.common.admin.platform_auth.mybatis.entity.MfUserRole;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.MfUserRoleMapper;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.mapper.so.MfUserRoleSO;
import com.csbr.qingcloud.common.admin.platform_auth.mybatis.service.MfUserRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色业务逻辑.
 *
 * @author Huanglh
 * @since 2020-07-27
 */

@Service
@Transactional
public class MfUserRoleServiceImpl extends CsbrServiceImpl<MfUserRoleMapper, MfUserRole> implements MfUserRoleService {

    /** 用户角色数据持久化对象 */
    @Autowired
    private MfUserRoleMapper mfUserRoleMapper;


    @Override
    public <D extends BasePageDTO> PageListVO getMapperPageList(D so) {
        Page<?> page = new Page<>(so.getPageIndex(), so.getPageSize());
        IPage<MfUserRole> iPage = mfUserRoleMapper.mapperPageMfUserRoles(page, so);
        return new PageListVO().build(iPage);
    }

    /**
     * 添加用户角色关系
     * @param tenantGuid
     * @param userGuid
     * @param roleGuids
     * @return
     */
    @Override
    public boolean addUserRole(String tenantGuid, String userGuid, List<String> roleGuids){
        //添加用户角色关系
        if(CollectionUtils.isNotEmpty(roleGuids))
        {
            List<MfUserRole> userRoles=new ArrayList<>();
            for (String roleguid:roleGuids) {
                MfUserRole userRole=new MfUserRole();
                userRole.setRoleGuid(roleguid);
                userRole.setUserGuid(userGuid);
                userRole.setTenantGuid(tenantGuid);
                this.csbrAddEntity(userRole);
                userRoles.add(userRole);
            }
            return this.saveBatch(userRoles);
        }
        return false;
    }
    /**
     * 获取用户角色关系详情
     * @param tenantGuid
     * @param userGuid
     * @return
     */
    @Override
    public List<MfAppSideRolePOJO> getUserRoleDetail(String tenantGuid, String userGuid, String appSideGuid){
        return mfUserRoleMapper.getUserRoleDetail(tenantGuid,userGuid,appSideGuid);

    }

    @Override
    public void delUserByUserRole(List<String> guids) {
        mfUserRoleMapper.delUserByUserRole(guids);
    }

    @Override
    public List<MfUserRolePOJO> getUserRoleNames(String tenantGuid, List<String> guids){
        if(StringUtils.isEmpty(tenantGuid)){
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "企业GUID关键字参数不能为空");
        }
        if (CollectionUtils.isEmpty(guids)) {
            throw new CsbrUserException(UserError.ACCOUNT_INPUT_ERROR, "用户GUID列表关键字参数不能为空");

        }
        return mfUserRoleMapper.getUserRoleNames(tenantGuid,guids);
    }

}