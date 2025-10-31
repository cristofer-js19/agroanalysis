package com.tech.agroanalysis.infrastructure.gateway.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record WeatherForecastRequest(
        Integer latitude,
        Integer longitude,
        String hourly,
        @JsonProperty("past_days") Integer pastDays
) {}
