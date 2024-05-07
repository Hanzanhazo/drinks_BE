package com.goormfj.hanzan.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserIdRequest {

    @NotBlank(message = "이름 은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일 은 필수 입력 값입니다.")
    private String email;


}
