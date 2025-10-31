package com.tech.agroanalysis.application.dto;

import lombok.Builder;

@Builder
public record AgroAnalysisInput(
        String polygon,
        String latitude,
        String longitude
) {}
