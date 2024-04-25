package com.goormfj.hanzan.user.controller;

import com.goormfj.hanzan.user.dto.LoginRequest;
import com.goormfj.hanzan.user.dto.RegisterMemberRequest;
import com.goormfj.hanzan.user.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid RegisterMemberRequest registerMemberRequest) {
        log.info("Received signup request: {}", registerMemberRequest);
        try {
            memberService.registerNewMember(registerMemberRequest);
            return ResponseEntity.ok("회원 가입 성공");
        } catch (Exception e) {
            log.error("Error registering user: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 실패: " + e.getMessage());
        }
    }

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
//        String userId = loginRequest.getUserId();
//        String password = loginRequest.getPassword();
//    }

}
