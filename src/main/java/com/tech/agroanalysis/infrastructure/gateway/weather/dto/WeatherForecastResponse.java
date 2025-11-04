package com.tech.agroanalysis.infrastructure.gateway.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record WeatherForecastResponse(
        String latitude,
        String longitude,
        @JsonProperty("generationtime_ms") String generationTime,
        @JsonProperty("utc_offset_seconds") Integer utcOffset,
        String timezone,
        @JsonProperty("timezone_abbreviation") String timezoneAbbreviation,
        Double elevation,
        @JsonProperty("hourly_units") WeatherForecastHourlyUnitsResponse hourlyUnits,
        WeatherForecastHourlyResponse hourly
) {
    public record WeatherForecastHourlyUnitsResponse(
            String time,
            @JsonProperty("temperature_2m") String temperature,
            @JsonProperty("precipitation_probability") String precipitationProbability,
            @JsonProperty("rain") String rainfallMeasurement
    ) {}
    public record WeatherForecastHourlyResponse(
            @JsonProperty("time") List<String> timeHistoryList,
            @JsonProperty("temperature_2m") List<Double> temperatureHistoryList,
            @JsonProperty("precipitation_probability") List<Integer> precipitationProbabilityList
    ) {}
}
