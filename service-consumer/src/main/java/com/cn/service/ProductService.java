package com.cn.service;


import com.cn.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "service-product", configuration = FeignConfiguration. class)
public interface ProductService {


    @GetMapping("/hi/hi")
    String getProduct();

}
