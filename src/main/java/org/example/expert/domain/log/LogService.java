package org.example.expert.domain.log;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {
    private final LogRepository logRepository;

    /**
     * 로그 조회 (읽기 전용 트랜잭션)
     */
    @Transactional(readOnly = true)
    public Page<LogResponse> findAll(
            LogType type,
            Long userId,
            Long todoId,
            LocalDateTime startDate,
            LocalDateTime endDate,
            Pageable pageable
    ) {
        Page<LogEntity> logs = logRepository.findAllByFilter(
                type, userId, todoId, startDate, endDate, pageable
        );
        return logs.map(LogResponse::of);
    }

    /**
     * 로그 저장 (비동기 처리)
     */
    @Async
    @Transactional
    public void saveAsync(LogEntity logEntity) {
        logRepository.save(logEntity);
    }
}
