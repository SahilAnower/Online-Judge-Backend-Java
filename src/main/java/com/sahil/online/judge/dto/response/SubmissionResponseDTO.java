package com.sahil.online.judge.dto.response;

import com.sahil.online.judge.entity.Status;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionResponseDTO {
    private Long id;
    private Long problemId;
    private Long userId;
    private Status status;
    private BigDecimal time;
    private BigDecimal memory;
    private String code;
    private String judgeToken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
