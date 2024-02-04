package com.sahil.online.judge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submission")
public class Submission extends BaseEntity{
    @Column(name = "problem_id")
    private Long problemId;
    @Column(name = "user_id")
    private Long userId;
    private String status;
    private BigDecimal time;
    private BigDecimal memory;
    private String code;
    @Column(name = "judge_token")
    private String judgeToken;
}
