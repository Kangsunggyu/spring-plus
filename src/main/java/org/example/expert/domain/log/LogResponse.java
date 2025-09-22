package org.example.expert.domain.log;

import org.example.expert.domain.user.dto.response.UserResponse;

import java.time.LocalDateTime;

public record LogResponse(
        Long id,
        LogType type,
        Long todoId,
        LocalDateTime createdAt,
        String description,
        UserResponse user
) {
    public static LogResponse of(LogEntity log) {
        return new LogResponse(
                log.getId(),
                log.getLogType(),
                log.getTodoId(),
                log.getCreatedAt(),
                log.getDescription(),
                UserResponse.from(log.getUser())
        );
    }
}
