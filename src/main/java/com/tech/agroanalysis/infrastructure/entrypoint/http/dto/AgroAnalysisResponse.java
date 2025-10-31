package com.tech.agroanalysis.infrastructure.entrypoint.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AgroAnalysisResponse(
        @JsonProperty("result") String analysisResult
) {}
