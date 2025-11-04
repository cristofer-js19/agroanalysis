package com.tech.agroanalysis.infrastructure.entrypoint.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AgroAnalysisResponse(
        @JsonProperty("soil_analysis") String soilAnalysis,
        @JsonProperty("weather_analysis") String weatherAnalysis
) {}
