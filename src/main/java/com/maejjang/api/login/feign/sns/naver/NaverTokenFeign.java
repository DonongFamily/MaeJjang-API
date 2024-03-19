package com.maejjang.api.login.feign.sns.naver;

import com.maejjang.api.config.feign.FeignSnakeConfig;
import com.maejjang.api.login.dto.sns.naver.NaverRequest;
import com.maejjang.api.login.dto.sns.naver.NaverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NaverTokenFeign", url = "${sns.naver.tokenUrl}")
public interface NaverTokenFeign {
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    NaverResponse getToken(@RequestBody NaverRequest naverRequest);
}
