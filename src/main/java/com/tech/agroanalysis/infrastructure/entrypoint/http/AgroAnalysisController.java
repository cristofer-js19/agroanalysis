package com.tech.agroanalysis.infrastructure.entrypoint.http;

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
    public ResponseEntity<AgroAnalysisResponse> performAnalysis(@RequestBody AgroAnalysisRequest request) throws Exception {
        var input = AgroAnalysisMapper.toUseCaseInput(request);
        var output = generativeAiAnalysisUseCase.execute(input);
        var response = AgroAnalysisMapper.toApiResponse(output);

        return ResponseEntity.ok(response);
    }
}
