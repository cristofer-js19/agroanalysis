package com.tech.agroanalysis.domain.model;

import lombok.Builder;

@Builder
public record GenerativeAiAnalysis(
        String soilAnalysis,
        String weatherAnalysis,
        String signature
) {}
