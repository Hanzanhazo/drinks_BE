package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.SignUpMemberRequest;
import com.goormfj.hanzan.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public Member registerNewMember(SignUpMemberRequest signUpMemberRequest) {
        // DTO에서 Entity로 변환 및 비밀번호 암호화
        String userId = signUpMemberRequest.getUserId();
        String email = signUpMemberRequest.getEmail();

        // 아이디와 이메일 중복 검사
        isUserIdAvailable(signUpMemberRequest.getUserId());
        isEmailAvailable(signUpMemberRequest.getEmail());

        // 비밀번호 일치 검사
        checkPasswordMatch(signUpMemberRequest.getPassword(), signUpMemberRequest.getPasswordCheck());
        String password = bCryptPasswordEncoder.encode(signUpMemberRequest.getPassword());

        // Member 엔티티 생성 및 저장
        Member member = new Member(userId, password, email);
        return memberRepository.save(member);
    }


    @Override
    public Member findMemberByEmail(String email) {
        return null;
    }

    @Override
    public Member findMemberByUserId(String userId) {
        return memberRepository.findMemberByUserId(userId);
    }

    // 아이디 중복 검사
    private void isUserIdAvailable(String userId) {
        if (memberRepository.existsMemberByUserId(userId)) {
            throw new IllegalStateException("중복된 아이디 존재");
        }
    }

    // 패스워드 확인 일치
    private void checkPasswordMatch(String password, String passwordCheck) {
        if (!password.equals(passwordCheck)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }

    // 이메일 중복 검사
    private void isEmailAvailable(String email) {
        if (memberRepository.existsMemberByEmail(email)) {
            throw new IllegalStateException("중복된 이메일 존재");
        }
    }
}
