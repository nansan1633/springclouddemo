package com.cn.controller;


import com.cn.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @Resource
    private RestTemplate restTemplate;

    @Resource
    private ProductService productService;

    @GetMapping("/getProduct")
    public String getProductString() {
        //String str =  restTemplate.getForObject("http://localhost:8083/hi/hi", String.class);
        String str1 =  restTemplate.getForObject("http://service-product/hi/hi", String.class);
        return str1;
    }

    @GetMapping("/getProduct2")
    public String getProductString2() {
        //String str =  restTemplate.getForObject("http://localhost:8083/hi/hi", String.class);
        String str1 =  productService.getProduct()+ " this is 2";
        return str1;
    }
}
