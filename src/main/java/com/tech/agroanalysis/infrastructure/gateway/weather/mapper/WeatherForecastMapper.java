package com.tech.agroanalysis.infrastructure.gateway.weather.mapper;

import com.tech.agroanalysis.domain.model.WeatherForecastProfile;
import com.tech.agroanalysis.infrastructure.gateway.weather.dto.WeatherForecastResponse;

public class WeatherForecastMapper {
    public static WeatherForecastProfile toDomain(WeatherForecastResponse response) {
        return WeatherForecastProfile.builder()
                .generationTime(response.generationTime())
                .utcOffset(response.utcOffset())
                .timezone(response.timezone())
                .timezoneAbbreviation(response.timezoneAbbreviation())
                .elevation(response.elevation())
                .hourlyUnits(WeatherForecastProfile.WeatherForecastHourlyUnitsProfile.builder()
                        .time(response.hourlyUnits().time())
                        .temperature(response.hourlyUnits().temperature())
                        .precipitationProbability(response.hourlyUnits().precipitationProbability())
                        .rainfallMeasurement(response.hourlyUnits().rainfallMeasurement())
                        .build())
                .hourly(WeatherForecastProfile.WeatherForecastHourlyProfile.builder()
                        .timeHistoryList(response.hourly().timeHistoryList())
                        .temperatureHistoryList(response.hourly().temperatureHistoryList())
                        .precipitationProbabilityHistoryList(response.hourly().precipitationProbabilityList())
                        .build())
                .build();
    }
}
