package com.sahil.online.judge.dao;

import com.sahil.online.judge.dto.response.ProblemNameResponseDTO;
import com.sahil.online.judge.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    @Query(value = "select count(id) from submission where problem_id = ?1 and status = 'ACCEPTED'", nativeQuery = true)
    Long getAcceptedCount(Long problemId);
    @Query(value = "select count(id) from submission where problem_id = ?1", nativeQuery = true)
    Long getSubmissionCount(Long problemId);
    @Query(value = "select id, name, difficulty from problem order by id", nativeQuery = true)
    List<ProblemNameResponseDTO> getAllProblemsBrief();
}
