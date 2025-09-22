package org.example.expert.domain.log;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("/logs")
    public ResponseEntity<Page<LogResponse>> getLog(
            @RequestParam(required = false) LogType type,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long todoId,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate,
            @PageableDefault Pageable pageable
    ) {
        return ResponseEntity.ok(logService.findAll(type, userId, todoId, startDate, endDate, pageable));
    }
}
