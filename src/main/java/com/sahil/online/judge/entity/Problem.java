package com.sahil.online.judge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problem")
@Entity
public class Problem extends BaseEntity{
    private String name;
    private String statement;
    @Transient
    private LikeCount likeDetails;
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
}
