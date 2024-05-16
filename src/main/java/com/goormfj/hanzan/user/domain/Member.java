package com.goormfj.hanzan.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId; // 회원 입력 아이디

    @Column(nullable = false)
    private String password; // 해시 처리된 비밀번호

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private LocalDate localDate;
    private String phone_Number;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String introduction;
    private String profile_picture;

    @Embedded
    private Preferences preferences; // 취향

    @Enumerated(EnumType.STRING)
    private Role role;


    public Member(String userId, String password, Role role) {
        this.userId = userId;
        this.password = password;
        this.role = role;
    }

    // 일반 회원가입
    public Member(String name, String userId, String password, String email) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
    }

//    // 소셜 회원가입
//    public Member(String name, String userId, String email) {
//        this.name = name;
//        this.userId = userId;
//        this.email = email;
//        this.role = Role.USER;
//    }

    // 비밀번호 업데이트
    public void updatePassword(String password) {
        this.password = password;
    }

    // 소셜로그인 이메일, 닉네임 업데이트
    public void updateSocialLogin(String email, String name) {
        this.email = email;
        this.name = name;
    }

}