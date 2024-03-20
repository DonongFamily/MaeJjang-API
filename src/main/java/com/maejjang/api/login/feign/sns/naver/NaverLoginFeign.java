package com.maejjang.api.login.feign.sns.naver;

import com.maejjang.api.login.dto.sns.naver.NaverInfResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NaverLoginFeign", url = "${sns.naver.userInfoUrl}")
public interface NaverLoginFeign {
    @PostMapping
    NaverInfResponse getUserInfo(@RequestHeader("Authorization") String accessToken);
}
