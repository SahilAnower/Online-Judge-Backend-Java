package com.sahil.online.judge.service.impl;

import com.sahil.online.judge.dto.request.JudgeSubmissionPostRequestDTO;
import com.sahil.online.judge.service.ThirdPartyService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThirdPartyServiceImpl implements ThirdPartyService {
    @Value("${judge.api.key}")
    private String apiKey;
    @Value("${judge.api.host}")
    private String apiHost;
    private final String judgeBaseUrl = "https://judge0-ce.p.rapidapi.com";
    @Override
    public String getLanguages() {
        try {
            HttpResponse<String> response = Unirest.get(judgeBaseUrl + "/languages")
                    .header("X-RapidAPI-Host", apiHost)
                    .header("X-RapidAPI-Key", apiKey)
                    .header("Content-Type", "application/json")
                    .asString();
            if (response.isSuccess()) {
                return response.getBody();
            }
        }catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return null;
    }

    @Override
    public String createSubmission(JudgeSubmissionPostRequestDTO judgeSubmissionPostRequestDTO) {
        try {
            JSONObject jsonObject = getJsonObject(judgeSubmissionPostRequestDTO);
            StringBuilder queryParams = new StringBuilder();
            queryParams.append("?base64_encoded=").append(judgeSubmissionPostRequestDTO.getBase64Encoded())
                    .append("&wait=").append(judgeSubmissionPostRequestDTO.getWait());
            HttpResponse<String> response = Unirest.post(judgeBaseUrl + "/submissions" + queryParams)
                    .header("X-RapidAPI-Host", apiHost)
                    .header("X-RapidAPI-Key", apiKey)
                    .header("Content-Type", "application/json")
                    .header("content-type", "application/json")
                    .body(jsonObject)
                    .asString();
            if (response.isSuccess()) {
                return response.getBody();
            }
        }catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return null;
    }

    private static JSONObject getJsonObject(JudgeSubmissionPostRequestDTO judgeSubmissionPostRequestDTO) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("source_code", judgeSubmissionPostRequestDTO.getSourceCode());
        if (judgeSubmissionPostRequestDTO.getStdin() != null) {
            jsonObject.put("stdin", judgeSubmissionPostRequestDTO.getStdin());
        }
        if (judgeSubmissionPostRequestDTO.getCommandLineArguments() != null) {
            jsonObject.put("command_line_arguments", judgeSubmissionPostRequestDTO.getCommandLineArguments());
        }
        if (judgeSubmissionPostRequestDTO.getRedirectStderrToStdout() != null) {
            jsonObject.put("redirect_stderr_to_stdout", judgeSubmissionPostRequestDTO.getRedirectStderrToStdout());
        }
        if (judgeSubmissionPostRequestDTO.getExpectedOutput() != null) {
            jsonObject.put("expected_output", judgeSubmissionPostRequestDTO.getExpectedOutput());
        }
        if (judgeSubmissionPostRequestDTO.getLanguageId() != null) {
            jsonObject.put("language_id", judgeSubmissionPostRequestDTO.getLanguageId());
        }
        if (judgeSubmissionPostRequestDTO.getCompilerOptions() != null) {
            jsonObject.put("compiler_options", judgeSubmissionPostRequestDTO.getCompilerOptions());
        }
        return jsonObject;
    }

    @Override
    public void getSubmission() {

    }
}
