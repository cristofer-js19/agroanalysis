package com.tech.agroanalysis.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public record WeatherForecastProfile(
        String generationTime,
        Integer utcOffset,
        String timezone,
        String timezoneAbbreviation,
        Double elevation,
        WeatherForecastHourlyUnitsProfile hourlyUnits,
        WeatherForecastHourlyProfile hourly
) {
    @Builder
    public record WeatherForecastHourlyUnitsProfile(
            String time,
            String temperature,
            String precipitationProbability,
            String rainfallMeasurement
    ) {}

    @Builder
    public record WeatherForecastHourlyProfile(
            List<String> timeHistoryList,
            List<Double> temperatureHistoryList,
            List<Integer> precipitationProbabilityHistoryList
    ) {}
}

