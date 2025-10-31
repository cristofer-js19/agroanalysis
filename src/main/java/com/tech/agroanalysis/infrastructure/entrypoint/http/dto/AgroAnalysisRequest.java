package com.tech.agroanalysis.infrastructure.entrypoint.http.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AgroAnalysisRequest(
        @JsonProperty("poligono") String polygon,
        String latitude,
        String longitude
) {}
