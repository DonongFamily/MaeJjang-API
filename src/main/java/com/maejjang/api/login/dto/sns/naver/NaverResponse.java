package com.maejjang.api.login.dto.sns.naver;

import lombok.Getter;

@Getter
public class NaverResponse {

    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private int expiresIn;
    private String error;
    private String errorDescription;
}
