package com.goormfj.hanzan.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindUserIdResponse {
    private String userId;
    private String message;

    public FindUserIdResponse(String userId) {
        this.userId = userId;
    }

    public FindUserIdResponse(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
