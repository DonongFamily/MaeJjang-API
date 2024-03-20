package com.maejjang.api.config.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;

public class FeignJsonSnakeConfiguration {

    /*@Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder(customObjectMapper());
    }*/

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder(customObjectMapper());
    }

    public ObjectMapper customObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        // Snake case로 설정
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }
}
