package com.sahil.online.judge.service;

import com.sahil.online.judge.dto.request.JudgeSubmissionPostRequestDTO;

public interface ThirdPartyService {
    String getLanguages();
    String createSubmission(JudgeSubmissionPostRequestDTO judgeSubmissionPostRequestDTO);
    void getSubmission();
}
