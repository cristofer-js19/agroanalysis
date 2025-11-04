package com.tech.agroanalysis.infrastructure.entrypoint.http.mapper;

import com.tech.agroanalysis.application.dto.AgroAnalysisInput;
import com.tech.agroanalysis.application.dto.AgroAnalysisOutput;
import com.tech.agroanalysis.domain.model.GenerativeAiAnalysis;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisRequest;
import com.tech.agroanalysis.infrastructure.entrypoint.http.dto.AgroAnalysisResponse;

public class AgroAnalysisMapper {
    public static AgroAnalysisInput toUseCaseInput(AgroAnalysisRequest request) {
        return AgroAnalysisInput.builder()
                .latitude(request.latitude())
                .longitude(request.longitude())
                .username(request.username())
                .plantType(request.plantType())
                .build();
    }

    public static AgroAnalysisOutput toUseCaseOutput(GenerativeAiAnalysis analysis) {
        return AgroAnalysisOutput.builder()
                .soilAnalysis(analysis.soilAnalysis())
                .weatherAnalysis(analysis.weatherAnalysis())
                .signature(analysis.signature())
                .build();
    }

    public static AgroAnalysisResponse toApiResponse(AgroAnalysisOutput output) {
        return AgroAnalysisResponse.builder()
                .soilAnalysis(output.soilAnalysis())
                .weatherAnalysis(output.weatherAnalysis())
                .build();
    }
}
