package com.csbr.cloud.zuul.server.filter;

import com.csbr.cloud.zuul.server.annotation.Logical;
import com.csbr.cloud.zuul.server.filter.pre.PreFilter;
import com.csbr.cloud.zuul.server.utils.RouteLocatorUtil;
import com.csbr.cloud.zuul.server.entity.MethodAuthBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author zhangheng
 * @date 2020/7/1 12:44
 * 判断权限是否通过
 */
@Component
public class AuthChecker {

    //日志
    private final static Logger logger = LoggerFactory.getLogger(PreFilter.class);

    @Resource
    private ApplicationContext applicationContext;

    public AuthChecker(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

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
    /*
    //  [{
    //        //        "actions": [
    //        //            "POST"
    //        //        ],
    //        //        "codes": [],
    //        //        "codesLogical": null,
    //        //        "roles": [
    //        //            "typeSys"
    //        //        ],
    //        //        "rolesLogical": "AND",
    //        //        "urls": [
    //        //            "/menu/add","/menu/sub"
    //        //        ]
    //        //    }
    //        //    ]
    */

    public int check(HttpServletRequest serverHttpRequest, String userRole, Set<String> codes) {
        Set<String> set = new HashSet<>();
        set.add(userRole);
        return check(serverHttpRequest, set, codes);
    }

    public int check(HttpServletRequest serverHttpRequest, Set<String> userRoles, Set<String>
            userCodes){


        //类似于  /zuuldmp/core/test
        String requestPath = serverHttpRequest.getRequestURI();
        //解析出该请求，是请求的哪个后端服务（根据zuul的路由规则获取）
        String[] array = RouteLocatorUtil.parseAppNameAndPath(
                applicationContext.getBean(RouteLocator.class),
                requestPath);
        if (array[0] == null) {
            return CODE_NO_APP;
        }
        String path = array[1];
        logger.info("解析路径path:"+path);
        logger.info("解析路径array[0]:"+array[0]);

        //从认证中心获取所有的映射信息
//        List<MethodAuthBean> list = applicationContext.getBean(AuthInfoHolder.class).findByAppName(array[0]);
        List<MethodAuthBean> list = new ArrayList<>();
        if (list == null) {
            return CODE_NO_APP;
        }

        //数据库存放路径为/role/{id}形式时
        for (MethodAuthBean methodAuthBean : list) {
            Set<String> urls = methodAuthBean.getUrls();
            for (String url : urls) {
                //如 /role/{id}/abc/{a}
                //替换为/role/[1-9][0-9]*/abc/[1-9][0-9]*   将来好做正则匹配
                if (url.contains("{") && url.contains("}")) {
                    url = url.replaceAll("\\{[^}]*\\}", "[1-9][0-9]*");
                }
                methodAuthBean.getUrls().add(url);
            }
        }
//        String method = serverHttpRequest.getMethod().toUpperCase();

//        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
//        HttpServletRequest request = sra.getRequest();
//        // 从Header里获取api编码和role编码
//        NativeWebRequest webRequest = new ServletWebRequest(request);
//        Map<String, String> map = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE,
//                RequestAttributes.SCOPE_REQUEST);
//        String apiUrl = request.getMethod().toUpperCase() + ConnectorConstant.UNDERLINE + request.getServletPath();
//        // 获取所有api
//        Map<String, String> apis = authCacheService.allApi();
//        String apiCode = null;
//        if (apis != null) {
//            if (map == null || map.size() == 0) {
//                // 如果不存在path 变量则直接比较
//                apiCode = apis.entrySet().stream()
//                        .filter(entry -> entry.getValue().equals(apiUrl)).findAny()
//                        .map(Map.Entry::getKey).orElse(null);
//            } else {
//                apiCode = apis.entrySet().stream().filter(entry -> entry.getValue().contains("{"))
//                        .filter(entry -> {
//                            String path = entry.getValue().substring(entry.getValue().indexOf("{") + 1,
//                                    entry.getValue().indexOf("}"));
//                            if (map.containsKey(path)) {
//                                return entry.getValue().replaceAll("\\{" + path + "\\}",
//                                        map.get(path)).equals(apiUrl);
//                            } else {
//                                return false;
//                            }
//                        }).findAny().map(Map.Entry::getKey).orElse(null);
//            }
//        }

        String method = serverHttpRequest.getMethod();
        logger.info("解析method:"+method);
        //判断action和path的映射
        MethodAuthBean methodAuthBean = checkPathAndAction(path, method, list);
        if (methodAuthBean == null) {
            return CODE_404;
        }
        //判断role
        if (!checkRoleAndCode(methodAuthBean.getRoles(), methodAuthBean.getRolesLogical(), userRoles)) {
            return CODE_NO_ROLE;
        }
        //判断code
//        if (!checkRoleAndCode(methodAuthBean.getCodes(), methodAuthBean.getCodesLogical(), userCodes)) {
//            return CODE_NO_CODE;
//        }

        return CODE_OK;
    }

    /**
     * 找到匹配的path、method
     */
    private MethodAuthBean checkPathAndAction(String path, String method, List<MethodAuthBean> list) {
        for (MethodAuthBean methodAuthBean : list) {
            //判断url是否有匹配的
            for (String url : methodAuthBean.getUrls()) {
                boolean isMatch = Pattern.matches(url, path);
                if (isMatch) {
                    //判断method，为*的通配
                    Set<String> allowMethod = methodAuthBean.getActions();
                    if (allowMethod.contains("*") || allowMethod.contains(method)) {
                        return methodAuthBean;
                    }
                }

            }

        }
        return null;
    }

    private boolean checkRoleAndCode(Set<String> requireSet, Logical logical, Set<String> userSet) {
        //如果该方法不需要权限，直接算通过
        if (CollectionUtils.isEmpty(requireSet)) {
            return true;
        }
        if (Logical.AND == logical) {
            return userSet.containsAll(requireSet);
        } else {
            //是or时，只要包含一个需要的role即可
            for (String requireRole : requireSet) {
                if (userSet.contains(requireRole)) {
                    return true;
                }
            }
        }

        return false;
    }

}
