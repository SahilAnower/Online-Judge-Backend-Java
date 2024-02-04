package com.sahil.online.judge.dao;

import com.sahil.online.judge.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
