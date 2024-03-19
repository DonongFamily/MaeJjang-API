package com.maejjang.api.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Feign Client 요청 데이터 로그 Config */
@Configuration
@Slf4j
public class FeignClientLogConfiguration {

    @Bean
    public CustomRequestInterceptor customRequestInterceptor() {
        return new CustomRequestInterceptor();
    }

    private class CustomRequestInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            if (log.isDebugEnabled()) {
                log.debug("Feign Request: {}", template.toString());
            }
        }
    }
}