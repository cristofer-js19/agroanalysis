package com.tech.agroanalysis.infrastructure.gateway.timeseries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record TimeSeriesResponse(
        @JsonProperty("listaSerie") List<Double> timeSeriesList,
        @JsonProperty("listaDatas") List<String> dateList
) {}
