package com.sahil.online.judge.controller;

import com.sahil.online.judge.dto.request.SubmissionGetRequestDTO;
import com.sahil.online.judge.dto.request.SubmissionPostRequestDTO;
import com.sahil.online.judge.dto.response.SubmissionResponseDTO;
import com.sahil.online.judge.service.SubmissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springdoc.core.annotations.ParameterObject;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    private final SubmissionService submissionService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubmissionResponseDTO createSubmission(@RequestBody @Valid SubmissionPostRequestDTO submissionPostRequestDTO) {
        return submissionService.createSubmission(submissionPostRequestDTO);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubmissionResponseDTO> getSubmissions (@ParameterObject SubmissionGetRequestDTO submissionGetRequestDTO) {
//        SubmissionGetRequestDTO submissionGetRequestDTO = new SubmissionGetRequestDTO();
        return submissionService.getSubmissions(submissionGetRequestDTO);
    }
}
