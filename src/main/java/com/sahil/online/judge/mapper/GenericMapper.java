package com.sahil.online.judge.mapper;

import com.sahil.online.judge.dto.response.ProblemResponseDTO;
import com.sahil.online.judge.dto.response.SubmissionResponseDTO;
import com.sahil.online.judge.entity.Problem;
import com.sahil.online.judge.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GenericMapper {
    SubmissionResponseDTO toSubmissionResponseDTO(Submission submission);
    ProblemResponseDTO toProblemResponseDTO(Problem problem);
}
