package com.sahil.online.judge.service.impl;

import com.sahil.online.judge.dao.SubmissionRepository;
import com.sahil.online.judge.dto.request.JudgeSubmissionPostRequestDTO;
import com.sahil.online.judge.dto.request.SubmissionGetRequestDTO;
import com.sahil.online.judge.dto.request.SubmissionPostRequestDTO;
import com.sahil.online.judge.dto.response.SubmissionResponseDTO;
import com.sahil.online.judge.entity.Status;
import com.sahil.online.judge.entity.Submission;
import com.sahil.online.judge.jpa.specification.GenericSpecificationBuilder;
import com.sahil.online.judge.jpa.specification.SpecificationFactory;
import com.sahil.online.judge.mapper.GenericMapper;
import com.sahil.online.judge.service.SubmissionService;
import com.sahil.online.judge.service.ThirdPartyService;
import kong.unirest.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final ThirdPartyService thirdPartyService;
    private final SubmissionRepository submissionRepository;
    private final GenericMapper genericMapper;
    private final SpecificationFactory<Submission> submissionSpecificationFactory;
    @Override
    public SubmissionResponseDTO createSubmission(SubmissionPostRequestDTO submissionPostRequestDTO) {
        JudgeSubmissionPostRequestDTO judgeSubmissionPostRequestDTO = JudgeSubmissionPostRequestDTO.builder()
                .stdin(submissionPostRequestDTO.getStdin())
                .wait(submissionPostRequestDTO.getWait())
                .languageId(submissionPostRequestDTO.getLanguageId())
                .base64Encoded(submissionPostRequestDTO.getBase64Encoded())
                .sourceCode(submissionPostRequestDTO.getCode())
                .expectedOutput(submissionPostRequestDTO.getExpectedOutput())
                .build();
        // call 0judge api for post with wait "true"
        JSONObject jsonObject = thirdPartyService.createSubmission(judgeSubmissionPostRequestDTO);
        BigDecimal time = (BigDecimal) jsonObject.get("time");
        BigDecimal memory = (BigDecimal) jsonObject.get("memory");
        String token = jsonObject.getString("token");
        JSONObject statusObject = jsonObject.getJSONObject("status");
        Integer statusId = (Integer) statusObject.get("id");
        Status status = getStatusFromDescription(statusId);
        // save the extra details came along with postrequest to db
        Submission submission = Submission
                .builder()
                .problemId(submissionPostRequestDTO.getProblemId())
                .userId(submissionPostRequestDTO.getUserId())
                .status(status)
                .time(time)
                .memory(memory)
                .code(judgeSubmissionPostRequestDTO.getSourceCode())
                .judgeToken(token)
                .inputTestCase(submissionPostRequestDTO.getStdin())
                .expectedOutput(submissionPostRequestDTO.getExpectedOutput())
                .build();
        submissionRepository.save(submission);
        return genericMapper.toSubmissionResponseDTO(submission);
    }

    private Status getStatusFromDescription(Integer statusId) {
        return switch(statusId) {
            case 3 -> Status.ACCEPTED;
            case 4 -> Status.WRONG_ANSWER;
            case 5 -> Status.TIME_LIMIT_EXCEEDED;
            case 6 -> Status.COMPILATION_ERROR;
            case 7 -> Status.RUNTIME_ERROR_SIGSEGV;
            case 8 -> Status.RUNTIME_ERROR_SIGXFSZ;
            case 9 -> Status.RUNTIME_ERROR_SIGFPE;
            case 10 -> Status.RUNTIME_ERROR_SIGABRT;
            case 11 -> Status.RUNTIME_ERROR_NZEC;
            case 12 -> Status.RUNTIME_ERROR_OTHER;
            case 13 -> Status.INTERNAL_ERROR;
            case 14 -> Status.EXEC_FORMAT_ERROR;
            default -> {
                log.error("Unexpected value: " + statusId);
//                yield Status.ACCEPTED;
                throw new IllegalStateException("Unexpected value: " + statusId);
            }
        };
    }

    @Override
    public List<SubmissionResponseDTO> getSubmissions(SubmissionGetRequestDTO submissionGetRequestDTO) {
        List<Submission> submissions = submissionRepository.findAll(getSpecification(submissionGetRequestDTO));
        return submissions.stream().map(genericMapper::toSubmissionResponseDTO).toList();
    }

    private Specification<Submission> getSpecification (SubmissionGetRequestDTO submissionGetRequestDTO) {
        GenericSpecificationBuilder<Submission> builder = new GenericSpecificationBuilder<>();
        if (submissionGetRequestDTO.getProblemId() != null) {
            builder.with(submissionSpecificationFactory.isEqual("problemId", submissionGetRequestDTO.getProblemId()));
        }
        if (submissionGetRequestDTO.getToken() != null) {
            builder.with(submissionSpecificationFactory.isEqual("judgeToken", submissionGetRequestDTO.getToken()));
        }
        if (submissionGetRequestDTO.getStatus() != null) {
            builder.with(submissionSpecificationFactory.isEqual("status", submissionGetRequestDTO.getStatus()));
        }
        if (submissionGetRequestDTO.getUserId() != null) {
            builder.with(submissionSpecificationFactory.isEqual("userId", submissionGetRequestDTO.getUserId()));
        }
        return builder.build();
    }
}
