package com.sahil.online.judge.dto.response;

import com.sahil.online.judge.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemNameResponseDTO {
    private Long id;
    private String name;
    private Difficulty difficulty;
}
