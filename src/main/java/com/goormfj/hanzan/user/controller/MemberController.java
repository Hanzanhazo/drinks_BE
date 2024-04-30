package com.goormfj.hanzan.user.controller;

import com.goormfj.hanzan.user.dto.SignUpMemberRequest;
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
@RequestMapping("/api/users")
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

    // 관리자 페이지 확인
    @GetMapping("/admin")
    public String adminP() {
        return "admin";
    }

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
//        String userId = loginRequest.getUserId();
//        String password = loginRequest.getPassword();
//    }

}
