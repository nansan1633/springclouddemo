package com.cn.config;

import com.netflix.zuul.context.RequestContext;

import org.bouncycastle.asn1.ocsp.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


/**
 * zuul 服务容错降级出来
 */
@Component
public class ServiceConsumerFallbackProvider implements  FallbackProvider{
    private Logger log = LoggerFactory.getLogger(ServiceConsumerFallbackProvider.class);
    @Override
    public String getRoute() {
        return "*";
    }
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }
            @Override
            public void close() {
            }
            @Override
            public InputStream getBody() throws IOException {
                if (cause != null) {
                    log.error("", cause.getCause());
                }
                RequestContext ctx = RequestContext.getCurrentContext();
               // ResponseData data = ResponseData.  //fail("服务器内部错误 ", "500");
                ResponseVo data = ResponseVo.fail("内存错误");
                return new ByteArrayInputStream(JsonUtils.toString(data).getBytes());
                //return null;
            }
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
