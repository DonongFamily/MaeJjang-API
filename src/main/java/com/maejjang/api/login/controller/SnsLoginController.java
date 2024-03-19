package com.maejjang.api.login.controller;

import com.maejjang.api.login.service.sns.SnsLoginFactory;
import com.maejjang.api.login.service.sns.SnsLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin("*")
@RequestMapping("/sns")
@RequiredArgsConstructor
public class SnsLoginController {

    private final SnsLoginFactory snsLoginFactory;

    /**
     * 단순 로그인 URL 호출, 화면단에서 바로 처리 가능한지 협의해봐야함
     */
    @PostMapping("/api/v1/oauth2/{type}")
    public String loginUrl(@PathVariable String type){
        SnsLoginService snsLoginService = snsLoginFactory.getSnsLoginService(type);
        return snsLoginService.loginUrl();
    }

    @GetMapping("/api/v1/oauth2/{type}")
    public String login(@PathVariable String type,
                        @RequestParam("code") String authCode) {
        SnsLoginService snsLoginService = snsLoginFactory.getSnsLoginService(type);
        return snsLoginService.login(authCode);
    }
}
