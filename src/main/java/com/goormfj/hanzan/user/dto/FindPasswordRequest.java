package com.goormfj.hanzan.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindPasswordRequest {

    @NotBlank(message = "이름 은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "아이디 는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "이메일 은 필수 입력 값입니다.")
    private String email;
}
