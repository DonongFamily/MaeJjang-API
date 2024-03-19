package com.maejjang.api.login.feign.sns.google;

import com.maejjang.api.login.dto.sns.google.GoogleInfResponse;
import com.maejjang.api.login.dto.sns.google.GoogleRequest;
import com.maejjang.api.login.dto.sns.google.GoogleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "GoogleLoginFeign", url = "${sns.google.tokenUrl}")
public interface GoogleLoginFeign {

    @PostMapping("/token")
    GoogleResponse getToken(@RequestBody GoogleRequest googleRequest);
    @PostMapping("/tokenInfo")
    GoogleInfResponse getUserInfo(@RequestBody Map<String, String> token);
}
