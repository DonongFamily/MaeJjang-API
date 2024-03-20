package com.maejjang.api.login.service.sns;

import com.maejjang.api.login.service.sns.google.GoogleLoginService;
import com.maejjang.api.login.service.sns.naver.NaverLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SnsLoginFactory {

    private final GoogleLoginService googleLoginService;
    private final NaverLoginService naverLoginService;

    public SnsLoginService getSnsLoginService(SnsType type) {
        switch (type){
            case GOOGLE:
                return googleLoginService;
            case NAVER:
                return naverLoginService;
            default:
                throw new IllegalArgumentException("SnsFactory Service 생성 에러");
        }
    }
}
