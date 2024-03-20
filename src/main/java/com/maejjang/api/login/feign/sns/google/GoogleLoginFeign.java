package com.maejjang.api.login.feign.sns.google;

import com.maejjang.api.config.feign.FeignJsonSnakeConfiguration;
import com.maejjang.api.login.dto.sns.google.GoogleInfResponse;
import com.maejjang.api.login.dto.sns.google.GoogleRequest;
import com.maejjang.api.login.dto.sns.google.GoogleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "GoogleLoginFeign", url = "${sns.google.tokenUrl}", configuration = FeignJsonSnakeConfiguration.class)
public interface GoogleLoginFeign {

    @PostMapping("/token")
    GoogleResponse getToken(@RequestBody GoogleRequest googleRequest);
    @PostMapping("/tokeninfo")
    GoogleInfResponse getUserInfo(@RequestParam("id_token") String token);
}
