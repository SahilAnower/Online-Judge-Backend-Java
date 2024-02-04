package com.sahil.online.judge.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeSubmissionGetRequestDTO {
    private String token;
    private String base64Encoded;
}
