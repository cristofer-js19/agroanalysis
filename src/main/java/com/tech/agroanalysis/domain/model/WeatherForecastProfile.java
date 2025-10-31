package com.tech.agroanalysis.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public record WeatherForecastProfile(
        List<String> timeHistoryList,
        List<Double> temperatureHistoryList,
        List<Integer> precipitationProbabilityHistoryList,
        List<Double> rainfallMilimetersHistoryList
) {}
