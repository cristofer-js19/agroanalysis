package com.tech.agroanalysis.domain.model;

import lombok.Builder;

@Builder
public record GenerativeAiAnalysis(
        String plantType,
        String weatherAnalysis,
        String soilAnalysis
) {}
