package com.tech.agroanalysis.infrastructure.entrypoint.http.mapper;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisRequest;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisResponse;

public class AgroAnalysisMapper {
    public static AgroAnalysisInput toUseCaseInput(AgroAnalysisRequest request) {
        return AgroAnalysisInput.builder()
                .polygon(request.polygon())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .build();
    }

    public static AgroAnalysisResponse toApiResponse(AgroAnalysisOutput output) {
        return AgroAnalysisResponse.builder()
                .analysisResult(output.analysisResult())
                .build();
    }
}
