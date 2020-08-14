package com.csbr.cloud.zuul.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.response.CommonRes;
import com.csbr.cloud.common.response.ResponseCode;
import com.csbr.cloud.common.util.JwtUtil;
import com.csbr.cloud.zuul.server.feign.AdminFeign;
import com.csbr.cloud.zuul.server.feign.UserFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RefreshScope//刷新配置
@RequestMapping("/csbr-zuul/user")//窄化请求地址
@Api(tags = {"网关服务"})
@Slf4j
public class VUserInfoController {

    /**
     * 注入用户相关
     */
    @Autowired
    private AdminFeign adminFeign;
    @Autowired
    private UserFeign userFeign;

    /**
     * 前端用户登录接口
     */
    @ApiOperation(value = "登录获取token", response = String.class, notes = "用户信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "logonUser", dataType = "String", value = "用户名"),
            @ApiImplicitParam(paramType = "query", required = true, name = "pwd", dataType = "String", value = "密码"),
            @ApiImplicitParam(paramType = "query", required = true, name = "validateCode", dataType = "String", value = "验证码"),
            @ApiImplicitParam(paramType = "query", required = true, name = "platformGuid", dataType = "String", value = "平台GUID"),
            @ApiImplicitParam(paramType = "query", required = true, name = "userStatusType", dataType = "String", value = "登陆身份类别(1 用户;2 管理员)"),
    })
    @PostMapping("/login")
    public CommonRes appLogin(
//              @RequestBody Map<String,Object> params,
            @RequestParam(value = "logonUser", required = true) String logonUser,
            @RequestParam(value = "pwd", required = true) String pwd,
            @RequestParam(value = "validateCode", required = true) String validateCode,
            @RequestParam(value = "platformGuid", required = true) String platformGuid,
            @RequestParam(value = "userStatusType", required = true) String userStatusType,
            HttpServletRequest request
    ) {
        try {
            /**
             * 登录获取 userid roleid
             */
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("logonUser", logonUser);
            jsonObject.put("pwd", pwd);
            jsonObject.put("validateCode", validateCode);
            jsonObject.put("platformGuid", platformGuid);
            // 根据 登陆身份类别 调用不同服务接口
            JSONObject appLogin;
            switch (userStatusType) {
                case "2":
                    appLogin = adminFeign.addAdminLogin(jsonObject);
                    break;
                default:
                    appLogin = userFeign.userLogin(jsonObject);
            }

            Map<String, Object> params = new HashMap<>();
            if (appLogin.getString("code").equals("00000")) {
                log.info("appLogin:" + appLogin);
                JSONObject data = appLogin.getJSONObject("data");
                if (!StringUtils.isEmpty(data)) {
                    Map<String, Object> claims = new HashMap<>();//存放用户信息(敏感信息不要放 比如密码)
                    claims.put("roleIds", data.getJSONArray("roleGuidList"));
                    claims.put("userId", data.getString("userGuid"));
                    claims.put("userName", data.getString("userName"));
                    // 平台和用户确定平台的具体人员(用户可以在多个平台上有人员的身份)
                    claims.put("platformGuid", platformGuid);
                    long ttlMillis = 1000 * 60 * 60 * 24 * 30;//过期时间(单位毫秒)
                    String token = JwtUtil.createJWT(claims, "csbr", "传世般若", ttlMillis, "");
                    params.put("token", token);
                    params.put("userId", "userId");
                    params.put("expiration", ttlMillis);
                }
                return CommonRes.ok(appLogin.getString("code"), appLogin.getString("msg"), params);
            }
            return CommonRes.ok(appLogin.getString("code"), appLogin.getString("msg"), null);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("login 异常!");
            return CommonRes.ok("B0001", "接口异常!", null);
        }
    }

    /**
     * 刷新token
     */
    @ApiOperation(value = "token延期", response = String.class, notes = "token延期", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", required = true, name = "token", dataType = "String", value = "快过期token"),
    })
    @PostMapping("/refreshToken")
    public CommonRes refreshToken(
            @RequestParam(value = "token", required = true) String token,
            HttpServletRequest request
    ) {
        try {
            String refreshToken = JwtUtil.refreshToken(token);
            if (!StringUtils.isEmpty(refreshToken)) {
                Map<String, Object> params = new HashMap<>();
                params.put("refreshToken", refreshToken);
                return CommonRes.ok(ResponseCode.Success.SUCCESS_CODE, "token延期成功!", params);
            } else {
                return CommonRes.ok(ResponseCode.Success.SUCCESS_CODE, "token已过期!", null);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("refreshToken 异常!");
            return CommonRes.ok("B0001", "接口异常!", null);
        }
    }

    /**
     * 登出 让客户端直接丢弃token
     */

}
