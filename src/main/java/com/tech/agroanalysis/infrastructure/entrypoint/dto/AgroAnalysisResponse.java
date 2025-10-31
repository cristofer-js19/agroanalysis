package com.tech.agroanalysis.infrastructure.entrypoint.dto;

import lombok.Builder;

@Builder
public record AgroAnalysisResponse(
        String analysisResult
) {}
