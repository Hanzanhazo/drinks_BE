package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.SignUpMemberRequest;

public interface MemberService {

    Member registerNewMember(SignUpMemberRequest signUpMemberRequest);

    Member findMemberByEmail(String email);

    Member findMemberByUserId(String userId);

}
