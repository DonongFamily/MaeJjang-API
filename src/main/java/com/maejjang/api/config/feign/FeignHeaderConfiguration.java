package com.maejjang.api.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignHeaderConfiguration {

    @Value("${maple.api-key}")
    private String mapleApiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("x-nxopen-api-key", mapleApiKey);
    }
}
