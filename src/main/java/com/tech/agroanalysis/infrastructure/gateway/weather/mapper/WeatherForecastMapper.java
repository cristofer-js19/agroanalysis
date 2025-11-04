package com.tech.agroanalysis.infrastructure.gateway.weather.mapper;

import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;

public class WeatherForecastMapper {
    public static WeatherForecastProfile toDomain(WeatherForecastResponse response) {
        return WeatherForecastProfile.builder()
                .timeHistoryList(response.hourly().timeHistoryList())
                .temperatureHistoryList(response.hourly().temperatureHistoryList())
                .precipitationProbabilityHistoryList(response.hourly().precipitationProbabilityList())
                .rainfallMilimetersHistoryList(response.hourly().rainfallMilimeterList())
                .build();
    }
}
