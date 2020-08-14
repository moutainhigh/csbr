package com.csbr.cloud.zuul.server.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ErrorFilter extends SendErrorFilter {


    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
//        try{
//            RequestContext ctx = RequestContext.getCurrentContext();
//            //直接复用异常处理类
//            Throwable throwable = ctx.getThrowable();
//            ExceptionHolder exceptionHolder = findZuulException(throwable);
//            Exception exception = null;
//            if(throwable instanceof Exception){
//                exception = (Exception)throwable.getCause();
//            }
//            log.info("异常信息:{}", throwable);
//            Result result;
//            if(exception!=null){
//                result = Result.getFailedResultFromException(exception);
//            }else{
//                result = Result.getFailedResult(exceptionHolder.getErrorCause());
//            }
//            HttpServletResponse response = ctx.getResponse();
//            response.setContentType("application/json;charset=UTF-8");
//            response.getWriter().write(JSON.toJSONString(result));
//        }catch (Exception ex) {
//            ReflectionUtils.rethrowRuntimeException(ex);
//        }
        return "hi,  i am  a errorFilter";
    }

}
