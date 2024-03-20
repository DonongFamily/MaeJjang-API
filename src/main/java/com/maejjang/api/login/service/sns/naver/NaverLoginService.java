package com.maejjang.api.login.service.sns.naver;

import com.maejjang.api.login.dto.sns.naver.NaverInfResponse;
import com.maejjang.api.login.dto.sns.naver.NaverRequest;
import com.maejjang.api.login.dto.sns.naver.NaverResponse;
import com.maejjang.api.login.feign.sns.naver.NaverLoginFeign;
import com.maejjang.api.login.feign.sns.naver.NaverTokenFeign;
import com.maejjang.api.login.service.sns.SnsLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class NaverLoginService implements SnsLoginService {

    @Value("${sns.naver.client.id}")
    private String naverClientId;

    @Value("${sns.naver.client.pw}")
    private String naverClientPw;

    @Value("${sns.naver.redirectUrl}")
    private String redirectUrl;

    @Value("${sns.naver.loginUrl}")
    private String loginUrl;

    @Value("${sns.naver.authorization-grant-type}")
    private String grantType;

    private final NaverLoginFeign naverLoginFeign;
    private final NaverTokenFeign naverTokenFeign;

    @Override
    public String loginUrl() {
        return UriComponentsBuilder
                .fromHttpUrl(loginUrl)
                .queryParam("client_id", naverClientId)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("response_type", "code")
                .toUriString();
    }

    @Override
    public String login(String authCode) {
        NaverRequest naverOAuthRequestParam = NaverRequest
                .builder()
                .grantType(grantType)
                .clientId(naverClientId)
                .clientSecret(naverClientPw)
                .code(authCode)
                .build();
        NaverResponse naverResponse = naverTokenFeign.getToken(naverOAuthRequestParam);

        String accessToken = naverResponse.getTokenType() + " " + naverResponse.getAccessToken();
        NaverInfResponse userInfo = naverLoginFeign.getUserInfo(accessToken);
        //todo: email 정보 DB에서 조회 후 있으면 로그인 처리, 없으면 회원가입처리

        return userInfo.getResponse().getEmail();
    }
}
