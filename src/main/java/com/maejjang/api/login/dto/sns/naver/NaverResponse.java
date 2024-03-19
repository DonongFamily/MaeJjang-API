package com.maejjang.api.login.dto.sns.naver;

import lombok.Getter;

@Getter
public class NaverResponse {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
}
