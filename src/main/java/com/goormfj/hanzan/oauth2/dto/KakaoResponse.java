package com.goormfj.hanzan.oauth2.dto;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoResponse implements OAuth2Response{

    private final Map<String, Object> attributes;
    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        // 이메일 정보는 'kakao_account' 맵 안에 있습니다.
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null) {
            return (String) kakaoAccount.get("email");
        }
        return null; // 이메일 정보가 없는 경우
    }

    @Override
    public String getName() {
        // 닉네임은 'properties' 맵 안의 'nickname' 키에 저장되어 있습니다.
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        if (properties != null) {
            return (String) properties.get("nickname");
        }
        return null; // 닉네임 정보가 없는 경우
    }
}