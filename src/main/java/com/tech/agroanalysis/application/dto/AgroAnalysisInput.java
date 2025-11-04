package com.tech.agroanalysis.application.dto;

import lombok.Builder;

@Builder
public record AgroAnalysisInput(
        String latitude,
        String longitude,
        String username,
        String plantType
) {}
