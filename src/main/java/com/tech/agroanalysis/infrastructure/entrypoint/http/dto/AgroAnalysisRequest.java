package com.tech.agroanalysis.infrastructure.entrypoint.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AgroAnalysisRequest(
        String latitude,
        String longitude,
        String username,
        @JsonProperty("plant_type") String plantType
) {}
