package com.sahil.online.judge.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "test_case")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase extends BaseEntity{
    private String input;
    private String output;
    @Column(name = "problem_id")
    private Long problemId;
}
