package com.cn.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {
    // 解决spring boot 2.0如下提示的问题
    // Unable to connect to Command Metric Stream
    @Bean
    public ServletRegistrationBean getHystrixBean() {
        ServletRegistrationBean hystrix = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        hystrix.setName("hystrixServlet");
        hystrix.setLoadOnStartup(1);
        return hystrix;
    }
}
