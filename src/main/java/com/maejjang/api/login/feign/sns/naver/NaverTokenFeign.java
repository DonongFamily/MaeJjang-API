package com.maejjang.api.login.feign.sns.naver;

import com.maejjang.api.config.feign.FeignUrlEncodedSnakeConfig;
import com.maejjang.api.login.dto.sns.naver.NaverRequest;
import com.maejjang.api.login.dto.sns.naver.NaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NaverTokenFeign", url = "${sns.naver.tokenUrl}", configuration = FeignUrlEncodedSnakeConfig.class)
public interface NaverTokenFeign {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    NaverResponse getToken(@RequestBody NaverRequest naverRequest);
}
