package org.example.expert.domain.manager.dto.response;

import lombok.Getter;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
public class ManagerSaveResponse {

    private final Long id;
    private final Long todoId;
    private final UserResponse user;

    public ManagerSaveResponse(Long id, Long todoId, UserResponse user) {
        this.id = id;
        this.todoId = todoId;
        this.user = user;
    }
}
