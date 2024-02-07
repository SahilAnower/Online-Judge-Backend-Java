package com.sahil.online.judge.controller;

import com.sahil.online.judge.dto.request.ProblemPostRequestDTO;
import com.sahil.online.judge.dto.response.ProblemNameResponseDTO;
import com.sahil.online.judge.dto.response.ProblemResponseDTO;
import com.sahil.online.judge.service.ProblemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemResponseDTO createProblem(@RequestBody @Valid ProblemPostRequestDTO problemPostRequestDTO) {
        return problemService.createProblem(problemPostRequestDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProblemNameResponseDTO> getProblems() {
        return problemService.getAllProblemsName();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProblemResponseDTO getProblem(@PathVariable Long id) {
        return problemService.getProblemById(id);
    }
}
