package com.maejjang.api.config.feign;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class FeignUrlEncodedSnakeConfig {

    @Bean
    public Encoder feignFormEncoder() {
        return new SnakeCaseFormEncoder();
    }

    @Bean
    public Decoder feignFormDecoder() {
        return new SnakeCaseDecoder();
    }

    static class SnakeCaseFormEncoder extends FormEncoder {
        @Override
        public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
            // 새로운 객체를 Map으로 변환하면서 필드 이름을 snake_case로 변경
            Map<String, Object> snakeCaseMap = new HashMap<>();
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    // 필드 이름을 snake_case로 변환
                    String snakeCaseName = toSnakeCase(field.getName());
                    snakeCaseMap.put(snakeCaseName, field.get(object));
                } catch (IllegalAccessException e) {
                    throw new EncodeException("Error while encoding request: " + e.getMessage());
                }
            }
            super.encode(snakeCaseMap, MAP_STRING_WILDCARD, template);
        }

        // camelCase를 snake_case로 변환하는 메소드
        private String toSnakeCase(String camelCase) {
            return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
        }
    }

    static public class SnakeCaseDecoder implements Decoder {

        private final ObjectMapper mapper;

        public SnakeCaseDecoder() {
            // ObjectMapper 인스턴스를 생성하고, snake_case를 camelCase로 변환하도록 설정합니다.
            this.mapper = new ObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        @Override
        public Object decode(Response response, Type type) throws IOException {
            // 응답의 바디를 읽어와서 지정된 타입의 객체로 변환합니다.
            return mapper.readValue(response.body().asInputStream(), mapper.constructType(type));
        }
    }
}
