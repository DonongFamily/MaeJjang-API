package com.maejjang.api.login.service.sns;

import com.maejjang.api.login.dto.GoogleInfResponse;
import com.maejjang.api.login.dto.GoogleRequest;
import com.maejjang.api.login.dto.GoogleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class GoogleLoginService implements SnsLoginService{

    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
    @Value("${server.domain}")
    private String redirectUri;

    @Override
    public String loginUrl() {
        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
                + "&redirect_uri=" + redirectUri + "/api/v1/oauth2/google&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }

    @Override
    public String login(String authCode) {
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = GoogleRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientPw)
                .code(authCode)
                .redirectUri(redirectUri + "/api/v1/oauth2/google")
                .grantType("authorization_code").build();

        ResponseEntity<GoogleResponse> resultEntity =
                restTemplate.postForEntity("https://oauth2.googleapis.com/token", googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken = resultEntity.getBody().getId_token();

        Map<String, String> map = new HashMap<>();
        map.put("id_token", jwtToken);

        ResponseEntity<GoogleInfResponse> resultEntity2 =
                restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo", map, GoogleInfResponse.class);
        String email = resultEntity2.getBody().getEmail();

        return email;
    }
}