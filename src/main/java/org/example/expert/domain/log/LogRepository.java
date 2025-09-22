package org.example.expert.domain.log;

import org.example.expert.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<LogEntity, Long> {
    List<LogEntity> user(User user);

    @Query("SELECT l FROM LogEntity l " +
            "WHERE (:type IS NULL OR l.logType = :type) " +
            "AND (:userId IS NULL OR l.user.id = :userId) " +
            "AND (:todoId IS NULL OR l.todoId = :todoId) " +
            "AND (:startDate IS NULL OR l.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR l.createdAt <= :endDate) " +
            "ORDER BY l.createdAt DESC"
    )
    Page<LogEntity> findAllByFilter(@Param("type") LogType type,
                                    @Param("userId") Long userId,
                                    @Param("todoId") Long todoId,
                                    @Param("startDate") LocalDateTime startDate,
                                    @Param("endDate") LocalDateTime endDate,
                                    Pageable pageable);
}