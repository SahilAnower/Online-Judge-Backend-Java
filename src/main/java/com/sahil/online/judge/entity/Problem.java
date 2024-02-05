package com.sahil.online.judge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problem")
@Entity
@Builder
public class Problem extends BaseEntity{
    private String name;
    private String statement;
    @Transient
    private LikeCount likeDetails; // todo: to write query for this
    @Transient
    private Long acceptedCount;
    @Transient
    private Long submissionCount;
    @Transient
    private BigDecimal acceptedPercentage;
    @Column(name = "time_limit")
    private BigDecimal timeLimit;
    @Column(name = "memory_limit")
    private BigDecimal memoryLimit;
    @Column(name = "start_code")
    private String startCode;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
