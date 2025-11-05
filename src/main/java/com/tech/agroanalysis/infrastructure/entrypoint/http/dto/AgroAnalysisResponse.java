package com.tech.agroanalysis.infrastructure.entrypoint.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AgroAnalysisResponse(
        @JsonProperty("plant_type") String plantType,
        @JsonProperty("weather_analysis") String weatherAnalysis,
        @JsonProperty("soil_analysis") String soilAnalysis
) {}
