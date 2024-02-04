package com.sahil.online.judge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeSubmissionPostRequestDTO {
    private String sourceCode;
    private Integer languageId;
    private String stdin;
    private String expectedOutput;
    private Boolean redirectStderrToStdout;
    private String compilerOptions;
    private String commandLineArguments;
    // request query
    private String base64Encoded;
    private String wait;
}
