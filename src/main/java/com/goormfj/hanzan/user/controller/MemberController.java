package com.goormfj.hanzan.user.controller;

import com.goormfj.hanzan.user.dto.*;
import com.goormfj.hanzan.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpMemberRequest signUpMemberRequest) {
        log.info("회원 가입 요청: {}", signUpMemberRequest);

        try {
            memberService.registerNewMember(signUpMemberRequest);
            return ResponseEntity.ok("회원 가입 성공");
        } catch (Exception e) {
            log.error("사용자 등록 오류: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 실패: " + e.getMessage());
        }
    }

    @PostMapping("/signup/check-userid")
    public ResponseEntity<String> checkUserId(@RequestBody CheckUserIdRequest checkUserIdRequest)  {

            boolean isExists = memberService.checkUserId(checkUserIdRequest);
            if (!isExists) {
                return ResponseEntity.ok().body("사용 가능한 아이디 입니다.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("사용 중인 아이디 입니다.");
            }
    }

    // 아이디 찾기
    @PostMapping("/find-userId")
    public ResponseEntity<FindUserIdResponse> findMemberId(@RequestBody @Valid FindUserIdRequest findUserIdRequest) {
        log.info("아이디 찾기 요청: {}", findUserIdRequest);
        try {
            String userId = memberService.findMemberId(findUserIdRequest);
            if (userId != null) {
                log.info("아이디 찾기 성공: {}", userId);
                return ResponseEntity.ok(new FindUserIdResponse(userId));
            } else {
                log.warn("아이디 찾기 실패, 사용자 정보 없음: {}", findUserIdRequest);
                return ResponseEntity.badRequest().body(new FindUserIdResponse(null, "사용자 정보를 찾을 수 없습니다."));
            }

        } catch (Exception e) {
            log.error("아이디 찾기 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FindUserIdResponse(null, "서버 내부 오류가 발생했습니다."));
        }

    }


    // 비밀번호 찾기 - 회원 확인
    @PostMapping("/find-password")
    public ResponseEntity<String> findMemberPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        log.info("비밀번호 찾기 요청: {}", findPasswordRequest);
        memberService.findMemberPassword(findPasswordRequest);

        return ResponseEntity.ok("");
    }

    // 비밀번호 찾기 - 비밀번호 재설정
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid FindPasswordRequest findPasswordRequest) {
        return ResponseEntity.ok("");
    }
}
