package org.example.expert.domain.auth.dto.response;

import lombok.Getter;

@Getter
public class SignupResponse {

    private final Long userId;
    private final String bearerToken;

    public SignupResponse(Long userId, String bearerToken) {
        this.userId = userId;
        this.bearerToken = bearerToken;
    }
}
