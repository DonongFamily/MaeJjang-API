package com.maejjang.api.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignMapleHeaderConfiguration {

    @Value("${maple.api-key}")
    private String mapleApiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("x-nxopen-api-key", mapleApiKey);
    }
}
