package com.csbr.cloud.zuul.server.filter.pre;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.common.util.JwtUtil;
import com.csbr.cloud.mybatis.entity.UserInfo;
import com.csbr.cloud.mybatis.interceptor.UserContextHolder;
import com.csbr.cloud.zuul.server.feign.AdminFeign;
import com.csbr.cloud.zuul.server.feign.UserInfoFeign;
import com.csbr.cloud.zuul.server.filter.AuthChecker;
import com.csbr.cloud.zuul.server.filter.WhiteListPool;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

/**
 * 请求路由之前被过滤 是否存在accessToken 存在就通过 不存在则过滤
 *
 */
public class PreFilter extends ZuulFilter {


    //日志
    private final static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Autowired
    private WhiteListPool pool;

//    @Resource
//    private RouteLocator routeLocator;

    @Autowired
    private AuthChecker authChecker;

    @Autowired
    private UserInfoFeign userInfoFeign;
    @Autowired
    private AdminFeign adminFeign;

    public static final int CODE_OK = 0;
    /**
     * 不存在该服务的mapping信息
     */
    public static final int CODE_NO_APP = -1;
    /**
     * 不存在的路径
     */
    public static final int CODE_404 = -2;
    /**
     * role不符
     */
    public static final int CODE_NO_ROLE = -3;
    /**
     * code不符
     */
    public static final int CODE_NO_CODE = -4;

    /**
     * 返回一个字符代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型。
     * pre:可以在请求被路由之前调用。
     * routing:在路由请求时候被调用。
     * post:在routing和error过滤器之后被调用。
     * error:处理请求时发生错误被调用。
     * @return
     */
    @Override
    public String filterType() {

//        logger.info("过滤前被调用!!!");

        return FilterConstants.PRE_TYPE;
    }

    /**
     * 通过int定义过滤器执行的顺序
     * 数字越小表示优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个boolean类型来判断该过滤器是否执行，从而该方法相当于是一个过滤器的开关。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 是否白名单
     */
    public boolean isWhiteList(String url){
        return pool.getValue().stream().anyMatch(url::contains);
    }

    /**
     * 过滤器的具体逻辑
     * @return
     */
    @Override
    public Object run() {
        logger.info("过滤前被调用!!!");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        logger.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        if(isWhiteList(request.getRequestURI().toString())){
            logger.info("接口:"+request.getRequestURI().toString()+"在白名单内,不做验证!");
            return null;
        }
        String authHeader = request.getHeader("authorization");
        if(!StringUtils.isEmpty(authHeader)) {
            String token = authHeader.replace("Bearer ", "");
            Claims claims = JwtUtil.getClaims(token);
            if(claims!=null){
                try {
                    logger.info("token is ok" + claims.toString());
                    //校验role
                    String userId = claims.get("userId") + "";
                    String userName = claims.get("userName") + "";
                    String roleId = claims.get("roleIds") + "";
                    String platformGuit = String.valueOf(claims.get("platformGuid"));
                    //用户信息共享处理
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserId(userId);
                    userInfo.setUserName(userName);
                    userInfo.setPlatformGuid(platformGuit);
                    String userJson = JSON.toJSONString(userInfo);
                    ctx.addZuulRequestHeader("X-USERINFO", String.valueOf(URLEncoder.encode(userJson,"UTF-8")));
                    String userType = (String) claims.get("userType");
                    //从认证中心读取角色菜单权限
//                  String codes = userInfoFeign.findCodesByRole(roleId);
                    String codes = "";
//                  Set<String> userCodes = FastJsonUtils.toBean(codes, Set.class);
                    Set<String> userCodes = null;
                    //类似于  /zuuldmp/core/test
//                    String requestPath = request.getRequestURI();
                    //获取请求的method
//                    String method = request.getMethod().toUpperCase();
                    //获取所有路由信息，找到该请求对应的appName
//                    List<Route> routeList = routeLocator.getRoutes();
                    //Route{id='one', fullPath='/zuuldmp/auth/**', path='/**', location='auth', prefix='/zuuldmp/auth',
//                    String appName = null;
//                    String path = null;
//                    for (Route route : routeList) {
//                        if (requestPath.startsWith(route.getPrefix())) {
//                            //取到该请求对应的微服务名字
//                            appName = route.getLocation();
//                            path = requestPath.replace(route.getPrefix(), "");
//                        }
//                    }
//                    if (appName == null) {
//                        ctx.setSendZuulResponse(false);
//                        ctx.setResponseStatusCode(404);
//                        try {
//                            ServletOutputStream outputStream = response.getOutputStream();
//                            outputStream.write(getJsonTokenIsNo(response).toString().getBytes("utf-8"));
//                            outputStream.flush();
//                            outputStream.close();
//                        }catch (Exception e){
//
//                        }
//                    }

                    //取到该用户的role、permission
                    //访问  auth 服务的 GET  /project/my 接口
//                    int code = authChecker.check(request,userCodes,null);
//                    switch (code) {
//                        //不存在的服务
//                        case CODE_NO_APP:
//                            try {
//                                ctx.setSendZuulResponse(false);
//                                ServletOutputStream outputStream = response.getOutputStream();
//                                outputStream.write(noLoginException(response,code, "不存在的服务").toString().getBytes("utf-8"));
//                                outputStream.flush();
//                                outputStream.close();
//                            }catch (Exception e){
//
//                            }
//                        case CODE_404:
//                            //无此接口或GET POST方法不对
//                            try {
//                                ctx.setSendZuulResponse(false);
//                                ServletOutputStream outputStream = response.getOutputStream();
//                                outputStream.write(noLoginException(response,code, "无此接口或GET POST方法不对").toString().getBytes("utf-8"));
//                                outputStream.flush();
//                                outputStream.close();
//                            }catch (Exception e){
//
//                            }
//                        case CODE_NO_ROLE:
//                            //用户无该接口所需role
//                            try {
//                                ctx.setSendZuulResponse(false);
//                                ServletOutputStream outputStream = response.getOutputStream();
//                                outputStream.write(noLoginException(response,code, "用户无该接口所需role").toString().getBytes("utf-8"));
//                                outputStream.flush();
//                                outputStream.close();
//                            }catch (Exception e){
//
//                            }
//                        case CODE_NO_CODE:
//                            //用户无此接口权限
//                            try {
//                                ctx.setSendZuulResponse(false);
//                                ServletOutputStream outputStream = response.getOutputStream();
//                                outputStream.write(noLoginException(response,code, "用户无该接口所需权限").toString().getBytes("utf-8"));
//                                outputStream.flush();
//                                outputStream.close();
//                            }catch (Exception e){
//
//                            }
//                        case CODE_OK:
//                            ctx.addZuulRequestHeader("userId", userId);
////                            ctx.addZuulRequestHeader("userType", userType);
//                            ctx.addZuulRequestHeader("roleId", roleId);
//                        default:
//                            break;
//                    }
                    return null;

                }catch (ExpiredJwtException expiredJwtEx){
                    logger.error("token : {} 过期", token);
                    //不对请求进行路由
                    //设置不过滤该请求。并且返回错误码
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(402);
                    try {
                        ServletOutputStream outputStream = response.getOutputStream();
                        outputStream.write(getJsonTokenIsNo(response).toString().getBytes("utf-8"));
                        outputStream.flush();
                        outputStream.close();
                    }catch (Exception e){

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }else{
//                logger.warn("accessToken无效");
                logger.error("token : {} 无效", token);
                //设置不过滤该请求。并且返回错误码
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(401);
                try {
                    ServletOutputStream outputStream = response.getOutputStream();
                    outputStream.write(getJsonTokenIsNo(response).toString().getBytes("utf-8"));
                    outputStream.flush();
                    outputStream.close();
                }catch (Exception e){

                }
                return null;
            }
        }else{
            logger.warn("token is empty");
            //设置不过滤该请求。并且返回错误码
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(getJsonTokenIsEmpty(response).toString().getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){

            }
            return null;
        }
    }

    /**
     * token为空
     * @return
     */
    @ResponseBody
    public String getJsonTokenIsEmpty(HttpServletResponse response){
        JSONObject jsonDate = new JSONObject();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        jsonDate.put("status",response.getStatus());
        jsonDate.put("msg","token is empty!");
        return jsonDate.toString();
    }

    /**
     * 验证通过
     * @param response
     * @param claims
     * @return
     */
    @ResponseBody
    public String getJsonTokenIsOk(HttpServletResponse response, Claims claims){
        JSONObject jsonDate = new JSONObject();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        jsonDate.put("status",response.getStatus());
        jsonDate.put("msg","token is ok!");
        jsonDate.put("data",claims);
        return jsonDate.toString();
    }

    /**
     * 验证不通过过期返回400
     */
    @ResponseBody
    public String getJsonTokenIsNo(HttpServletResponse response){
        JSONObject jsonDate = new JSONObject();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        jsonDate.put("status",response.getStatus());
        jsonDate.put("msg","token无效!");
        return jsonDate.toString();
    }

    @ResponseBody
    public String noLoginException(HttpServletResponse response,int code,String msg){
        JSONObject jsonDate = new JSONObject();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        jsonDate.put("status",code);
        jsonDate.put("msg",msg);
        return jsonDate.toString();
    }
}
