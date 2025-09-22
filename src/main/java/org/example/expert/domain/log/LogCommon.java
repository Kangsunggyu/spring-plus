package org.example.expert.domain.log;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogCommon {
    private final LogService logService;
    private final UserService userService;

    /**
     * 현재 로그인한 사용자 가져오기
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("인증된 사용자가 없습니다.");
        }
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return userService.getUserById(authUser.getId());
    }

    /**
     * 로그 저장 (비동기 처리)
     */
    public void saveLog(LogType logType, User user, Long todoId, String description) {
        LogEntity log = LogEntity.of(
                logType,
                user,
                todoId,
                description
        );
        // 기존 동기 저장 → 비동기 저장으로 변경
        logService.saveAsync(log);
    }
}
