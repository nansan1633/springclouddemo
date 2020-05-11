package com.cn.service;



import feign.hystrix.FallbackFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductClientFallbackFactory  implements FallbackFactory<ProductClient> {

    private Logger logger = LoggerFactory.getLogger(ProductClientFallbackFactory.class);

    public ProductClient create(Throwable cause) {
        logger.error("UserRemoteClient回退：", cause);
        return new ProductClient() {
            @Override
            public String getProduct() {
                return "fail 服务退回";
            }
        };
    }
}
