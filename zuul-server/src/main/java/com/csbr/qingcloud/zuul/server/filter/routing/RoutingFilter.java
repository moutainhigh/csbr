package com.csbr.qingcloud.zuul.server.filter.routing;

import com.netflix.zuul.ZuulFilter;


public class RoutingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "routing";
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
        System.out.println("--------------hi,  i am  a routingFilter");
        return "hi,  i am  a routingFilter";
    }

}
