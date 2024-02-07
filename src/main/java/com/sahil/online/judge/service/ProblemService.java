package com.sahil.online.judge.service;

import com.sahil.online.judge.dto.request.ProblemPostRequestDTO;
import com.sahil.online.judge.dto.response.ProblemNameResponseDTO;
import com.sahil.online.judge.dto.response.ProblemResponseDTO;

import java.util.List;

public interface ProblemService {
    ProblemResponseDTO createProblem(ProblemPostRequestDTO problemPostRequestDTO);
    ProblemResponseDTO getProblemById(Long id);
    List<ProblemNameResponseDTO> getAllProblemsName ();
}
