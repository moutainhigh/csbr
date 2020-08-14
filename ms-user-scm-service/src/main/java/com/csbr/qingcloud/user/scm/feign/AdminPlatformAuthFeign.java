package com.csbr.qingcloud.user.scm.feign;

import com.csbr.cloud.common.config.FastCallFeignConfiguration;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.qingcloud.user.scm.domain.dto.staff.UserAddDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.UserRemoveDTO;
import com.csbr.qingcloud.user.scm.domain.dto.staff.UserRoleNameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @program: ms-user-scm-service
 * @description: 用户服务接口
 * @author: yio
 * @create: 2020-07-31 15:46
 **/
@FeignClient(value = "ms-common-admin-platform-auth-service", configuration = FastCallFeignConfiguration.class)
public interface AdminPlatformAuthFeign {

    /**
     * 提交业务上传递的人员用户信息
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/user/data/updateregcode-or-adduser", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes<String> updateRegCodeOrAddUser(@RequestBody @Valid UserAddDTO dto);

    /**
     * 通过移除人员移除用户
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/user/data/remove-staff-by-user", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes<String> removeUserByStaff(@RequestBody UserRemoveDTO dto);

    /**
     * 获取用户角色名map
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "/user/data/get-user-role-names", method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, List<String>> getUserRoleNames(@RequestBody UserRoleNameDTO dto);

    /**
     * 依据平台GUID，身份证，电话获取用户
     *
     * @param mobileNos
     * @param platGuid
     * @return
     */
    @RequestMapping(value = "/user/data/get-users-by-idcode-mobile-plat", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    CommonRes<Map<String, String>> getUsersByIdMobilePlat(@RequestParam List<String> mobileNos,
                                                          @RequestParam String platGuid);

    @RequestMapping(value = "/user/data/batch-add-user", method = RequestMethod.POST
            , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CommonRes<Map<String, String>> batchAddUser(@RequestBody List<UserAddDTO> dtos);
}
