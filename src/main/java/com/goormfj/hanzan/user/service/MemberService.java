package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.RegisterMemberRequest;

public interface MemberService {

    Member registerNewMember(RegisterMemberRequest registerMemberRequest);

    Member findMemberByEmail(String email);

    Member findMemberByUserId(String userId);

}
