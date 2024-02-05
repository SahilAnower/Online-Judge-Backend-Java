package com.sahil.online.judge.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionPostRequestDTO {
    @NotNull
    private Long problemId;
    @NotNull
    private Long userId;
    @NotNull
    private String code;
    @NotNull
    private Integer languageId;
    private String stdin = null;
    private String expectedOutput = null;
    private Boolean redirectStderrToStdout = null;
    private String base64Encoded = null;
    private String wait = "true";
}
