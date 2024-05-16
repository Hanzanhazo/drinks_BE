package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.CustomUserDetails;
import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        // DB 조회
        Member data = memberRepository.findMemberByUserId(userId);

        if (data != null) {
            return new CustomUserDetails(data);
        }

        throw new UsernameNotFoundException(("유저 정보가 없습니다."));
    }

}
