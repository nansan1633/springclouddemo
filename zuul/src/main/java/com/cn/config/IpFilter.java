package com.cn.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


/**
 * zuul请求拦截处理
 */

@Component
public class IpFilter extends ZuulFilter {

    // IP黑名单列表
    private List<String> blackIpList = Arrays.asList("127.0.0.1");

    public IpFilter() {
        super();
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }



    /**
     * 过滤器的类型。可选值有：
     * pre - 前置过滤
     * route - 路由后过滤
     * error - 异常过滤
     * post - 远程服务调用后过滤
     */
    @Override
    public String filterType() {
        System.out.println("pre");
        return "route";
    }

    @Override
    public int filterOrder() {
        System.out.println("filterOrder");
        return 1;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();

        System.out.println("进入拦截器");
        String ip = IpUtils.getIpAddr(ctx.getRequest());
        System.out.println("ip = "+ip);
        // 在黑名单中禁用
        if (StringUtils.isNotBlank(ip) && blackIpList.contains(ip)) {

            System.out.println("禁用ip");

//            ctx.setSendZuulResponse(false);
//            ResponseData data = ResponseData.fail("非法请求 ", ResponseCode.NO_AUTH_CODE.getCode());
//            ctx.setResponseBody(JsonUtils.toJson(data));
//            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        return null;
    }
}