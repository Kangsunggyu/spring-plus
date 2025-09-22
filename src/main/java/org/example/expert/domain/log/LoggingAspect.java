package org.example.expert.domain.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.expert.domain.auth.dto.response.SigninResponse;
import org.example.expert.domain.auth.dto.response.SignupResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.service.UserService;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final UserService userService;
    private final LogCommon logCommon;

    @Pointcut("execution(* org.example.expert.domain.auth.service.AuthService.signup(..))")
    private void signup() {}

    @Pointcut("execution(* org.example.expert.domain.auth.service.AuthService.signin(..))")
    private void signin() {}

    @Pointcut("execution(* org.example.expert.domain.todo.service.TodoService.saveTodo(..))")
    private void saveTodo() {}

    @Pointcut("execution(* org.example.expert.domain.comment.service.CommentService.saveComment(..))")
    private void saveComment() {}

    @Pointcut("execution(public * org.example.expert.domain.manager.service.ManagerService.saveManager(..))")
    private void saveManager() {}

    @AfterReturning(pointcut = "signup()", returning = "result")
    public void signupLog(Object result) {
        if (result instanceof SignupResponse signupResponse) {
            Long userId = signupResponse.getUserId();
            User user = userService.getUserById(userId);

            String description = LogType.USER_SIGNUP.getDescription()
                    .replace("{1}", user.getNickname());

            logCommon.saveLog(LogType.USER_SIGNUP, user, null, description);
        }
    }

    @AfterReturning(pointcut = "signin()", returning = "result")
    public void signinLog(Object result) {
        if (result instanceof SigninResponse) {
            User user = logCommon.getCurrentUser();

            String description = LogType.USER_SIGNIN.getDescription()
                    .replace("{1}", user.getNickname());

            logCommon.saveLog(LogType.USER_SIGNIN, user, null, description);
        }
    }

    @AfterReturning(pointcut = "saveTodo()", returning = "result")
    public void saveTodoLog(Object result) {
        if (result instanceof TodoSaveResponse todoSaveResponse) {
            User user = logCommon.getCurrentUser();

            String description = LogType.TODO_CREATED.getDescription()
                    .replace("{1}", todoSaveResponse.getTitle());

            logCommon.saveLog(LogType.TODO_CREATED, user, todoSaveResponse.getId(), description);
        }
    }

    @AfterReturning(pointcut = "saveManager()", returning = "result")
    public void saveManagerLog(Object result) {
        if (result instanceof ManagerSaveResponse managerSaveResponse) {
            User user = logCommon.getCurrentUser();

            String description = LogType.REQUEST_MANAGER.getDescription()
                    .replace("{1}", managerSaveResponse.getUser().getNickname());

            logCommon.saveLog(LogType.REQUEST_MANAGER, user, managerSaveResponse.getTodoId(), description);
        }
    }
}
