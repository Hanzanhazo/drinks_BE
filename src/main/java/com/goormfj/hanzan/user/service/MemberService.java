package com.goormfj.hanzan.user.service;

import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.dto.*;

public interface MemberService {

    Member registerNewMember(SignUpMemberRequest signUpMemberRequest);

    String findMemberId(FindUserIdRequest findUserIdRequest);

    Boolean checkUserId(CheckUserIdRequest checkUserIdRequest);

    Member validateUserForPasswordReset(FindPasswordRequest findPasswordRequest);

    Member reValidateUserForPasswordReset(ResetPasswordRequest resetPasswordRequest);

    Boolean updatePassword(Member member, ResetPasswordRequest resetPasswordRequest);



}
