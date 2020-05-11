package com.cn.service;


import org.springframework.stereotype.Component;

@Component
public class ProductClientFallback implements ProductClient{


     @Override
    public String getProduct() {

        return "服务熔断";

    }
}
