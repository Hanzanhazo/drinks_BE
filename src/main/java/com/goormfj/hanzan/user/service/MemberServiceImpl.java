package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.FindUserIdRequest;
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
        // 아이디와 이메일 중복 검사
        isUserIdAvailable(signUpMemberRequest.getUserId());
        isEmailAvailable(signUpMemberRequest.getEmail());

        // DTO에서 Entity로 변환 및 비밀번호 암호화
        String name = signUpMemberRequest.getName();
        String userId = signUpMemberRequest.getUserId();
        String email = signUpMemberRequest.getEmail();
        String password = bCryptPasswordEncoder.encode(signUpMemberRequest.getPassword());

        // Member 엔티티 생성 및 저장
        Member member = new Member(name, userId, password, email);
        return memberRepository.save(member);
    }

    @Override
    public String findMemberId(FindUserIdRequest findUserIdRequest) {
        String name = findUserIdRequest.getName();
        String email = findUserIdRequest.getEmail();

        if (!memberRepository.existsByNameAndEmail(name, email)) {
            throw new IllegalArgumentException("이름과 이메일에 맞는 회원 정보가 없습니다.");
        }

        return memberRepository.findMemberByNameAndEmail(name, email).getUserId();
    }


//    @Override
//    public Member findMemberByEmail(String email) {
//        return memberRepository.findMemberByEmail(email);
//    }
//
//    @Override
//    public Member findMemberByUserId(String userId) {
//        return memberRepository.findMemberByUserId(userId);
//    }


    // 아이디 중복 검사
    private void isUserIdAvailable(String userId) {
        if (memberRepository.existsMemberByUserId(userId)) {
            throw new IllegalStateException("중복된 아이디 존재");
        }
    }

    // 이메일 중복 검사
    private void isEmailAvailable(String email) {
        if (memberRepository.existsMemberByEmail(email)) {
            throw new IllegalStateException("중복된 이메일 존재");
        }
    }
}
