package com.cn.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(value = "service-product", configuration = FeignConfiguration.class,fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {

    @GetMapping("/hi/hi")
    String getProduct();
}
