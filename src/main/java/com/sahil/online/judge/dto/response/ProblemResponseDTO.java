package com.sahil.online.judge.dto.response;

import com.sahil.online.judge.entity.LikeCount;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemResponseDTO {
    private String name;
    private String statement;
    private LikeCount likeDetails;
    private Long acceptedCount = 0L;
    private Long submissionCount = 0L;
    private BigDecimal acceptedPercentage = BigDecimal.valueOf(0);
    private BigDecimal timeLimit;
    private BigDecimal memoryLimit;
    private String startCode;
}
