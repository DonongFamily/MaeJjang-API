package com.maejjang.api.login.service.sns.google;

import com.maejjang.api.login.dto.sns.google.GoogleInfResponse;
import com.maejjang.api.login.dto.sns.google.GoogleRequest;
import com.maejjang.api.login.dto.sns.google.GoogleResponse;
import com.maejjang.api.login.feign.sns.google.GoogleLoginFeign;
import com.maejjang.api.login.service.sns.SnsLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class GoogleLoginService implements SnsLoginService {

    @Value("${sns.google.client.id}")
    private String googleClientId;

    @Value("${sns.google.client.pw}")
    private String googleClientPw;

    @Value("${sns.google.redirectUrl}")
    private String redirectUrl;

    @Value("${sns.google.loginUrl}")
    private String loginUrl;

    @Value("${sns.google.scope}")
    private String scope;

    @Value("${sns.google.authorization-grant-type}")
    private String grantType;

    @Value("${sns.google.access_type}")
    private String accessType;

    private final GoogleLoginFeign googleLoginFeign;

    @Override
    public String loginUrl() {
        //todo: scope 문제 해결 -> ,로 되어있음 -> %20으로 변경해야함
        return UriComponentsBuilder
                .fromHttpUrl(loginUrl)
                .queryParam("client_id", googleClientId)
                .queryParam("redirect_uri", redirectUrl)
                .queryParam("response_type", "code")
                .queryParam("scope", scope)
                .queryParam("access_type", accessType)
                .toUriString();
    }

    @Override
    public String login(String authCode) {
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri(redirectUrl)
                .grantType(grantType).build();

        GoogleResponse googleResponse = googleLoginFeign.getToken(googleOAuthRequestParam);

        GoogleInfResponse tokenInfo = googleLoginFeign.getUserInfo(googleResponse.getIdToken());
        //todo: email 정보 DB에서 조회 후 있으면 로그인 처리, 없으면 회원가입처리

        return tokenInfo.getEmail();
    }
}
