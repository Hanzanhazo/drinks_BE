package com.goormfj.hanzan.user.dto;

import com.goormfj.hanzan.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String userId;
    private String name;
    private String email;
    private Role role;


}

