package org.example.expert.domain.log;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.common.entity.Timestamped;
import org.example.expert.domain.user.entity.User;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogEntity extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LogType logType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private Long todoId;

    @Column(nullable = false)
    private String description;

    public static LogEntity of(LogType logType, User user, Long todoId, String description) {
        LogEntity log = new LogEntity();
        log.logType = logType;
        log.user = user;
        log.todoId = todoId;
        log.description = description;
        return log;
    }
}
