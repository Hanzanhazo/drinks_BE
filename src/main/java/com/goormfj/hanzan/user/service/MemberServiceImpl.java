package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.RegisterMemberRequest;
import com.goormfj.hanzan.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Member registerNewMember(RegisterMemberRequest registerMemberRequest) {
        //DTO에서 Entity로 변환
        String userId = registerMemberRequest.getUserId();
        String password = registerMemberRequest.getPassword();
        String email = registerMemberRequest.getEmail();

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
}
