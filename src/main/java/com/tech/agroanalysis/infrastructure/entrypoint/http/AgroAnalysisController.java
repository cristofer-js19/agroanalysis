package com.tech.agroanalysis.infrastructure.entrypoint.http;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.application.port.in.GenerativeAiAnalysisUseCase;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisRequest;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisResponse;
import com.tech.agroanalysis.infrastructure.entrypoint.http.mapper.AgroAnalysisMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analysis")
@RequiredArgsConstructor
public class AgroAnalysisController {

    private final GenerativeAiAnalysisUseCase generativeAiAnalysisUseCase;

    @PostMapping
    public ResponseEntity<AgroAnalysisResponse> performAnalysis(@RequestBody AgroAnalysisRequest request) {
        AgroAnalysisInput input = AgroAnalysisMapper.toUseCaseInput(request);
        AgroAnalysisOutput output = generativeAiAnalysisUseCase.execute(input);
        AgroAnalysisResponse response = AgroAnalysisMapper.toApiResponse(output);

        return ResponseEntity.ok(response);
    }
}
