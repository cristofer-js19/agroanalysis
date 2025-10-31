package com.tech.agroanalysis.application.dto;

import lombok.Builder;

@Builder
public record AgroAnalysisOutput(
        String analysisResult
) {}
