package com.maejjang.api.login.service.sns;

public interface SnsLoginService {

    String loginUrl();

    String login(String authCode);
}
