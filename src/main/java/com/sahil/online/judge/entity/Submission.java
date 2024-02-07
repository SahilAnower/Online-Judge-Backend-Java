package com.sahil.online.judge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "submission")
@Builder
public class Submission extends BaseEntity{
    @Column(name = "problem_id")
    private Long problemId;
    @Column(name = "user_id")
    private Long userId;
    @Enumerated(EnumType.STRING)
    private Status status;
    private BigDecimal time;
    private BigDecimal memory;
    private String code;
    @Column(name = "judge_token")
    private String judgeToken;
    @Column(name = "input_test_case")
    private String inputTestCase;
    @Column(name = "expected_output")
    private String expectedOutput;
}
