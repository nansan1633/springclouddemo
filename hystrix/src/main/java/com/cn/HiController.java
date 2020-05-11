package com.cn;


import com.cn.service.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hi")
public class HiController {


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProductClient productClient;

    @Value(value = "${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi() {

        return "hi spring cloud " + port;

    }

    @HystrixCommand(fallbackMethod = "defaultCallHello",commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy", value = "THREAD")
    })
    @GetMapping("/callHello")
    //@HystrixCommand(fallbackMethod = "defaultCallHello")
    public String callHello() {
        String result = restTemplate.getForObject("http://service-product/hi/hi", String.class);
        return result;

    }

    @GetMapping("/callHello2")
    //@HystrixCommand(fallbackMethod = "defaultCallHello")
    public String callHello2() {
        String result = productClient.getProduct();
        return result;

    }


    public String defaultCallHello() {

        return "the method is fail !";
    }

}
