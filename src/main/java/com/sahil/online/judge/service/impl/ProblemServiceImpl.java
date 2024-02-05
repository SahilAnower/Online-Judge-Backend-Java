package com.sahil.online.judge.service.impl;

import com.sahil.online.judge.dao.ProblemRepository;
import com.sahil.online.judge.dto.request.ProblemPostRequestDTO;
import com.sahil.online.judge.dto.response.ProblemNameResponseDTO;
import com.sahil.online.judge.dto.response.ProblemResponseDTO;
import com.sahil.online.judge.entity.Problem;
import com.sahil.online.judge.mapper.GenericMapper;
import com.sahil.online.judge.service.ProblemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final GenericMapper genericMapper;
    @Override
    public ProblemResponseDTO createProblem(ProblemPostRequestDTO problemPostRequestDTO) {
        Problem problem = Problem
                .builder()
                .name(problemPostRequestDTO.getName())
                .statement(problemPostRequestDTO.getStatement())
                .timeLimit(problemPostRequestDTO.getTimeLimit())
                .memoryLimit(problemPostRequestDTO.getMemoryLimit())
                .startCode(problemPostRequestDTO.getStartCode())
                .build();
        problemRepository.save(problem);
        return ProblemResponseDTO
                .builder()
                .name(problem.getName())
                .statement(problem.getStatement())
                .timeLimit(problem.getTimeLimit())
                .memoryLimit(problem.getMemoryLimit())
                .startCode(problem.getStartCode())
                .build();
    }

    @Override
    public ProblemResponseDTO getProblemById(Long id) {
        Problem problem = problemRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Problem not found with this id!"));
        // acceptedCount
        Long acceptedCount = problemRepository.getAcceptedCount(id);
        // submissionCount
        Long submissionCount = problemRepository.getSubmissionCount(id);
        // acceptedPercentage
        BigDecimal acceptedPercentage = BigDecimal.valueOf(((double) acceptedCount / submissionCount) * 100.0);
        problem.setAcceptedCount(acceptedCount);
        problem.setSubmissionCount(submissionCount);
        problem.setAcceptedPercentage(acceptedPercentage);
        return genericMapper.toProblemResponseDTO(problem);
    }

    @Override
    public List<ProblemNameResponseDTO> getAllProblemsName() {
        return problemRepository.getAllProblemsBrief();
    }
}
