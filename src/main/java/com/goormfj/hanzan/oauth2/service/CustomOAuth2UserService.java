package com.goormfj.hanzan.oauth2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormfj.hanzan.oauth2.dto.CustomOAuth2User;
import com.goormfj.hanzan.oauth2.dto.KakaoResponse;
import com.goormfj.hanzan.oauth2.dto.OAuth2Response;
import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.domain.Role;
import com.goormfj.hanzan.user.dto.MemberDTO;
import com.goormfj.hanzan.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("oAuth2User = " + oAuth2User);

        System.out.println(oAuth2User);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        OAuth2Response oAuth2Response = null;

        System.out.println("test: " + oAuth2User.getAttributes());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {
            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String userId = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        String email = oAuth2Response.getEmail();
        String name = oAuth2Response.getName();

        Member existData = memberRepository.findMemberByEmail(email);

        if (existData == null) {

            Member member = new Member(name, userId, "", email);

            memberRepository.save(member);

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setName(name);
            memberDTO.setEmail(email);
            memberDTO.setUserId(userId);
            memberDTO.setRole(Role.USER);

            return new CustomOAuth2User(memberDTO);
        }
        else {

            existData.updateSocialLogin(email, name);

            memberRepository.save(existData);

            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setName(existData.getName());
            memberDTO.setEmail(existData.getEmail());
            memberDTO.setUserId(existData.getUserId());
            memberDTO.setRole(existData.getRole());

            return new CustomOAuth2User(memberDTO);
        }

    }


}
