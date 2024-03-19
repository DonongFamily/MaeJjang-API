package com.maejjang.api.login.dto.sns.naver;

import lombok.Getter;

@Getter
public class NaverInfResponse {
    private String resultcode;
    private String message;
    private NaverUserInfo response;
}
