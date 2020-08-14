package com.csbr.cloud.zuul.server.filter.post;

import com.alibaba.fastjson.JSONObject;
import com.csbr.cloud.zuul.server.utils.MultiPartFormDateToJson;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author zhangheng
 * @date 2020/7/15 17:08
 */
@Slf4j
@Component
public class LogRecodePostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;//要打印返回信息，必须得用"post"
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext context = RequestContext.getCurrentContext();
//        Boolean isSuccess = (boolean) context.get("isSuccess");
//        return isSuccess;
        final RequestContext ctx = RequestContext.getCurrentContext();
        // 当发生异常的时候,不是进行取值
        if (ctx.getThrowable() != null) {
            log.error("{}", ctx.getThrowable().fillInStackTrace());
            if (ctx.sendZuulResponse()) {
                return true;
            }else{
                return false;
            }
        }else {
            return true;
        }
    }

    @Override
    public Object run() {
        try {
            log.info("进入日志记录过滤器");
            RequestContext ctx = RequestContext.getCurrentContext();
            HttpServletRequest request = ctx.getRequest();

            InputStream in = request.getInputStream();
            String method = request.getMethod();
            String interfaceMethod = request.getServletPath();
            log.info("请求方法method={},url={}",method,interfaceMethod);
            String reqBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
//            int user = 0;
//            String invokeUser = "";
            if ("GET".equals(method.toUpperCase())) {
                Map<String, String[]> map = request.getParameterMap();
                // 打印请求url参数
                if (map != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    for (Map.Entry<String, String[]> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = printArray(entry.getValue());
                        sb.append("[" + key + "=" + value + "]");
//                        if ("user".equals(key)) {
//                            invokeUser = value;
//                        } else if ("userFlag".equals(key)) {
//                            user = Integer.parseInt(value);
//                        }
                    }
                    sb.append("}");
                    reqBody = sb.toString();
                    log.info("reqBody ={}" + reqBody);
                }
            } else if ("POST".equals(method.toUpperCase())) {

                //打印请求json参数
                if (reqBody != null) {
                    String conType = request.getHeader("content-type");
                    //post请求目前获取userFlag，user参数只支持multipart/form-data，application/json，对于其他方式不记录用户信息
                    if (conType.contains("multipart/form-data") || conType.contains("application/json")) {
                        if (conType.contains("multipart/form-data")) {
                            reqBody = MultiPartFormDateToJson.formDateToJson(reqBody);
                        }
                        //默认content-type传json-->application/json
//                        Object userObject;
//                        Object invokeUserObject;
//                        JSONObject jsonObject = JSONObject.parseObject(reqBody);
//                        userObject = jsonObject.get("userFlag");
//                        if (null != userObject) {
//                            user = Integer.parseInt(userObject.toString());
//                        } else {
//                            log.warn("当前请求缺少userFlag");
//                        }
//                        invokeUserObject = jsonObject.get("user");
//                        if (null != userObject) {
//                            invokeUser = invokeUserObject.toString();
//                        } else {
//                            log.warn("当前请求缺少user");
//                        }
                        log.info("reqBody:={}" + reqBody);
                    }
                }

            }


            // 打印response
            InputStream out = ctx.getResponseDataStream();
            String outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
//            boolean result = false;
//            if (outBody != null && "" != outBody) {
////                JSONObject jsonObject = JSONObject.parseObject(outBody);
////                Object dataFlagObject = jsonObject.get("dataFlag");
////                if (null != dataFlagObject) {
////                    int flag = Integer.parseInt(dataFlagObject.toString());
////                    if (flag == 1) {
////                        result = true;
////                    }
////                }
//                log.info("响应参数:={}" + outBody);
//            }
            //必须重新写入流//重要！！！
            ctx.setResponseBody(outBody);
//            InvokeLogModel logModel = new InvokeLogModel();
//            logModel.setUid(user);
//            logModel.setInvokeUser(invokeUser);
//            logModel.setInterfaceName(interfaceMethod);
//            logModel.setInterfaceMethod(method);
//            logModel.setInvokeStartTime(new Date());
//            logModel.setInvokeEndTime(null);
//            logModel.setRequestParam(reqBody);
//            logModel.setResponseResult(result);
//            logModel.setResponseBody(outBody);
//            invokeLogService.insertInvokerLog(logModel);

        } catch (IOException e) {
            log.error("LogRecode IO异常", e);
        }

        return null;
    }

    String printArray(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
