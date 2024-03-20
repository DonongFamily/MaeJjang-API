package com.maejjang.api.login.dto.sns.naver;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NaverRequest {
    private String grantType;
    private String clientId;
    private String clientSecret;
    private String code;
    private String state;
    private String refreshToken;
    private String accessToken;
    private String serviceProvider;
}
