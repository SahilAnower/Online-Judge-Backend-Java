package com.sahil.online.judge.dto.request;

import com.sahil.online.judge.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionGetRequestDTO {
    private Long problemId;
    private Long userId;
    private Status status;
    private String token;
}
