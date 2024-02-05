package com.sahil.online.judge.service;

import com.sahil.online.judge.dto.request.JudgeSubmissionGetRequestDTO;
import com.sahil.online.judge.dto.request.JudgeSubmissionPostRequestDTO;
import kong.unirest.json.JSONObject;

public interface ThirdPartyService {
    String getLanguages();
    JSONObject createSubmission(JudgeSubmissionPostRequestDTO judgeSubmissionPostRequestDTO);
    String getSubmission(JudgeSubmissionGetRequestDTO judgeSubmissionGetRequestDTO);
}
