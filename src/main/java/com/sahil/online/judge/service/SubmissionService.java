package com.sahil.online.judge.service;

import com.sahil.online.judge.dto.request.SubmissionGetRequestDTO;
import com.sahil.online.judge.dto.request.SubmissionPostRequestDTO;
import com.sahil.online.judge.dto.response.SubmissionResponseDTO;

import java.util.List;

public interface SubmissionService {
    SubmissionResponseDTO createSubmission(SubmissionPostRequestDTO submissionPostRequestDTO);

    List<SubmissionResponseDTO> getSubmissions (SubmissionGetRequestDTO submissionGetRequestDTO);
}
